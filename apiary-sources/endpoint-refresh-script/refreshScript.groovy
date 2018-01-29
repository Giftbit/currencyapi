import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import sun.net.www.protocol.https.HttpsURLConnectionImpl

File requests = new File("requests.json")
List<File> filesToProcess = new File("endpoints").listFiles()

def json = fetchTestDataFromFile(requests)
// Use a new LIVE mode API key from lightraildev.
String userJwt = "eyJ2ZXIiOjMsInZhdiI6MSwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJnIjp7Imd1aSI6InVzZXItNDY0NjE5NzA4NmFmNDcxZmE5MjY1ZmQzZDE1NDZmZmEiLCJnbWkiOiJ1c2VyLTQ2NDYxOTcwODZhZjQ3MWZhOTI2NWZkM2QxNTQ2ZmZhIiwidG1pIjoidXNlci00NjQ2MTk3MDg2YWY0NzFmYTkyNjVmZDNkMTU0NmZmYSJ9LCJhdWQiOiJBUElfS0VZIiwiaXNzIjoiU0VSVklDRVNfVjEiLCJpYXQiOjE1MTcyNTc1NjUuNjcyLCJqdGkiOiJiYWRnZS0xYzQ5N2MxMTYzNmE0OWEzOGRlNTIxMmMxMzU5MmYzZSIsInBhcmVudEp0aSI6ImJhZGdlLWJkODQ0NzM5ZGI2NzQwMmI5MGExMGI4NTI4NzJkYTVmIiwic2NvcGVzIjpbXSwicm9sZXMiOlsiYWNjb3VudE1hbmFnZXIiLCJjb250YWN0TWFuYWdlciIsImN1c3RvbWVyU2VydmljZU1hbmFnZXIiLCJjdXN0b21lclNlcnZpY2VSZXByZXNlbnRhdGl2ZSIsInBvaW50T2ZTYWxlIiwicHJvZ3JhbU1hbmFnZXIiLCJwcm9tb3RlciIsInJlcG9ydGVyIiwic2VjdXJpdHlNYW5hZ2VyIiwidGVhbUFkbWluIiwid2ViUG9ydGFsIl19.l-QyxsdmwG3977mo4cBmaJiF-6YHaSq5LqqiAj1JjCw"

Map calls = [:]
for (Map call in json.calls) {
    if (!call.callId) {
        throw new Exception("No callId. Invalid input ${call}.")
    }
    if (call.get(call.callId)) {
        throw new Exception("Invalid inout. Duplicate callId ${call.callId}")
    }
    if (!call.endpoint) {
        throw new Exception("Invalid inpout. Call ${call}.")
    }
    calls.put(call.callId, call)
}

def callsToDo = calls.entrySet()

while (callsToDo) {
    println "CallsTodo.size() == ${callsToDo.size()}."
    for (entry in callsToDo) {
        String key = entry.key
        Map call = entry.value as Map

        call.attempts = call.attempts ? call.attempts + 1 : 1
        if (call.attempts > 10) {
            println "Error: ${call.toString()} exceeded 10 retry attempts."
            throw new Exception("${call.toString()} exceeded number of attempts.")
        }
        if (!call.finishedReplacement) {
            try {
                call = checkForReplacements(call, calls) as Map
                call.finishedReplacement = true
            } catch (TestDataCallDependencyException e) {
                call.finishedReplacement = false
            }
        }

        if (call.finishedReplacement) {
            call.response = makeRequestAgainstLightrail(call, userJwt)
        } else {
            call.response = [status: 424]
        }
        calls.put(key, call)
    }
    callsToDo = callsToDo.findAll { it.value.response.status != 200 }
}

for (file in filesToProcess) {
    String fileText = file.text
    fileText = checkForReplacements(fileText, calls, true) as String
    if (fileText.contains("REQUEST_REPLACEMENT")) {
        throw new Exception("File withname ${file.name} contains unreplaced text!!!")
    }
    def outputFile = new File("../endpoints/${file.name}")
    outputFile.write(fileText)
}

static def checkForReplacements(def input, Map responses, boolean replaceAsJson = false) throws TestDataCallDependencyException {
    if (input instanceof Map) {
        for (entry in input.entrySet()) {
            entry.value = checkForReplacements(entry.value, responses)
        }
        return input
    } else if (input instanceof String) {
        List<String> responseReplacements = input.findAll(/\{REQUEST_REPLACEMENT\:(.*?)\}/)
        for (replacement in responseReplacements) {
            println "found something to replace! " + replacement
            String responseKeyString = replacement
            responseKeyString = responseKeyString.replace("{REQUEST_REPLACEMENT:", "").replace("}", "")
            List<String> responseKeys = responseKeyString.split("\\.")
            def value = responses
            for (key in responseKeys) {
                value = value.get(key)
                if (value == null) {
                    throw new TestDataCallDependencyException("Dependency does not exist yet. replacement: ${replacement}. key ${key}")
                }
            }
            if (replaceAsJson) {
                value = JsonOutput.toJson(value)
            }
            println "replacement value: " + value
            input = input.replace(replacement, value as String)
        }
        return input
    } else {
        println "this is happening"
        return input
    }
}

static def fetchTestDataFromFile(File file) {
    def slurper = new JsonSlurper()
    return slurper.parse(file)
}

def makeRequestAgainstLightrail(Map request, String userJwt) {
    println "Making request: ${request}"
    URLConnection connection = new URL("https://www.lightraildev.net/v1" + request.endpoint).openConnection();
    connection.setDoOutput(true)
    connection.setRequestProperty("Content-Type", "application/json")
    connection.setRequestProperty("Authorization", "Bearer ${userJwt}")
    if (request.method == "PATCH") {
        connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        connection.setRequestMethod("POST");
    } else {
        connection.setRequestMethod(request.method)
    }

    if (request.method == "POST" || request.method == "PATCH") {
        String message = JsonOutput.toJson(request.body)
        println "requestBody" + message
        connection.getOutputStream().write(message.getBytes("UTF-8"));
    }
    return getResponse(connection)
}

Map getResponse(HttpsURLConnectionImpl result) {
    def response = [status: result.getResponseCode()]
    println response.status
    if (response.status == 200) {
        response.body = new JsonSlurper().parse(result.getInputStream().getText().toCharArray())
        println response.body
    } else if (response.status == 400 || response.status == 409) {
        throw new Exception("Requested failed against api.")
    }
    return response
}

class TestDataCallDependencyException extends Exception {

    TestDataCallDependencyException() {
        super()
    }

    TestDataCallDependencyException(String message) {
        super(message)
    }
}