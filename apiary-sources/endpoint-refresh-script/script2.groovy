import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import sun.net.www.protocol.https.HttpsURLConnectionImpl


File requests = new File("requests.json")
List<File> filesToProcess = new File("endpoints").listFiles()

def json = fetchTestDataFromFile(requests)
String userJwt = "eyJ2ZXIiOjMsInZhdiI6MSwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJnIjp7Imd1aSI6InVzZXItNTAyMmZjY2Y4Mjc2NDdlZTljZmI2M2I3NzlkNjIxOTMtVEVTVCIsImdtaSI6InVzZXItNTAyMmZjY2Y4Mjc2NDdlZTljZmI2M2I3NzlkNjIxOTMtVEVTVCIsInRtaSI6InVzZXItNTAyMmZjY2Y4Mjc2NDdlZTljZmI2M2I3NzlkNjIxOTMtVEVTVCJ9LCJhdWQiOiJBUElfS0VZIiwiaXNzIjoiU0VSVklDRVNfVjEiLCJpYXQiOjE1MTY4MjI0MzIuOTU2LCJqdGkiOiJiYWRnZS01NGYyNjVlZTMyYzI0Mjg3YTk3ZDhmNDlmOWYyOGY5MSIsInBhcmVudEp0aSI6ImJhZGdlLWQ5OTY5YzZmZGZjYjRmODM5M2EwMTA5MjllMmYwOGVmIiwic2NvcGVzIjpbXSwicm9sZXMiOlsiYWNjb3VudE1hbmFnZXIiLCJjb250YWN0TWFuYWdlciIsImN1c3RvbWVyU2VydmljZU1hbmFnZXIiLCJjdXN0b21lclNlcnZpY2VSZXByZXNlbnRhdGl2ZSIsInBvaW50T2ZTYWxlIiwicHJvZ3JhbU1hbmFnZXIiLCJwcm9tb3RlciIsInJlcG9ydGVyIiwic2VjdXJpdHlNYW5hZ2VyIiwidGVhbUFkbWluIiwid2ViUG9ydGFsIl19.CRFk2CzzgKTRJFX_QOuOFzyDGmscLEoB9OLwy1jKuHw"

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
            call.response = makeRequestAgainstLightrail(call)
        } else {
            call.response = [status: 424]
        }
        calls.put(key, call)
    }
    callsToDo = callsToDo.findAll { it.value.response.status != 200 }
}


for (file in filesToProcess) {
    // todo - make sure all replacements work
    String fileText = file.text
    fileText = checkForReplacements(fileText, calls, true) as String
    if (fileText.contains("RESPONSE_REPLACEMENT")) {
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
        List<String> responseReplacements = input.findAll(/\{RESPONSE_REPLACEMENT\:(.*?)\}/)
        for (replacement in responseReplacements) {
            println "found something to replace! " + replacement
            String responseKeyString = replacement
            responseKeyString = responseKeyString.replace("{RESPONSE_REPLACEMENT:", "").replace("}", "")
            List<String> responseKeys = responseKeyString.split("\\.")
            def value = responses
            for (key in responseKeys) {
//                println "Narrowing in on replacementValue ${value}"
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

def makeRequestAgainstLightrail(Map request) {
    println "Making request: ${request}"
    URLConnection connection = new URL("https://www.lightraildev.net/v1" + request.endpoint).openConnection();
    connection.setDoOutput(true)
    connection.setRequestProperty("Content-Type", "application/json")
    connection.setRequestProperty("Authorization", "Bearer eyJ2ZXIiOjMsInZhdiI6MSwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJnIjp7Imd1aSI6InVzZXItNTAyMmZjY2Y4Mjc2NDdlZTljZmI2M2I3NzlkNjIxOTMtVEVTVCIsImdtaSI6InVzZXItNTAyMmZjY2Y4Mjc2NDdlZTljZmI2M2I3NzlkNjIxOTMtVEVTVCIsInRtaSI6InVzZXItNTAyMmZjY2Y4Mjc2NDdlZTljZmI2M2I3NzlkNjIxOTMtVEVTVCJ9LCJhdWQiOiJBUElfS0VZIiwiaXNzIjoiU0VSVklDRVNfVjEiLCJpYXQiOjE1MTY4MjI0MzIuOTU2LCJqdGkiOiJiYWRnZS01NGYyNjVlZTMyYzI0Mjg3YTk3ZDhmNDlmOWYyOGY5MSIsInBhcmVudEp0aSI6ImJhZGdlLWQ5OTY5YzZmZGZjYjRmODM5M2EwMTA5MjllMmYwOGVmIiwic2NvcGVzIjpbXSwicm9sZXMiOlsiYWNjb3VudE1hbmFnZXIiLCJjb250YWN0TWFuYWdlciIsImN1c3RvbWVyU2VydmljZU1hbmFnZXIiLCJjdXN0b21lclNlcnZpY2VSZXByZXNlbnRhdGl2ZSIsInBvaW50T2ZTYWxlIiwicHJvZ3JhbU1hbmFnZXIiLCJwcm9tb3RlciIsInJlcG9ydGVyIiwic2VjdXJpdHlNYW5hZ2VyIiwidGVhbUFkbWluIiwid2ViUG9ydGFsIl19.CRFk2CzzgKTRJFX_QOuOFzyDGmscLEoB9OLwy1jKuHw")
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