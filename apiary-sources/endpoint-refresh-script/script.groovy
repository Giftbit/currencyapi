import groovy.json.JsonOutput
import groovy.json.JsonSlurper

// Basic Groovy Script
Console console=System.console();
//def name=console.readLine("Please enter an API key from the dev environment in LIVE mode. This should be a new account and in LIVE mode so that there is no additional data:")
//println "Welcome to Groovy, $name!"

File folder = new File("endpoints")
List<File> filesToProcess = folder.listFiles()
Map store = [:]

while (filesToProcess) {

    List<File> finishedFiles = []

    for (file in filesToProcess) {
        String text = file.text
        if (text.contains("<refresh-script>") && text.contains("</refresh-script>")) {
            String requestString = text.substring(text.indexOf("<refresh-script>") + 16, text.indexOf("</refresh-script>"))
            Map request = new JsonSlurper().parse(requestString.toCharArray()) as Map

            String res = makeRequestAgainstLightrail(request)
            text = text.replaceAll(/<refresh-script>[\s\S]*<\/refresh-script>/, request.body ? JsonOutput.toJson(request.body) : "")
            text = text.replaceAll("<replace-response/>", res)
            def outputFile = new File("../endpoints/${file.name}")
            outputFile.write(text)
            finishedFiles.add(file)
        } else {
            def outputFile = new File("../endpoints/${file.name}")
            outputFile.write(file.text)
            finishedFiles.add(file)
        }
    }

    filesToProcess.removeAll(finishedFiles)
}

def makeRequestAgainstLightrail(Map request) {
    URLConnection httpRequest = getRequestForLightrailAPI(request.endpoint as String)
    httpRequest.setRequestMethod(request.method)
    if (request.method == "POST" || request.method == "PATCH") {
        String message = JsonOutput.toJson(request.body)
        httpRequest.getOutputStream().write(message.getBytes("UTF-8"));
    }
    return getResponse(httpRequest)
}

def getResponse(def result) {
    def response = result.getResponseCode();
    println("response code" + response);
    switch (response) {
        case 200:
            String res = result.getInputStream().getText()
            print res
            return res
            break
        case 409:
            throw new Exception("Refresh script request resulted in a 409. This is likely because a new dev api key wasn't provided. Please make a new dev account and provide the API key to the script.")
            break
        default:
            throw new Exception("Unexpected response code returned.")
    }
}

static URLConnection getRequestForLightrailAPI(String endpoint) {
    def genericRequest = new URL("https://www.lightraildev.net/v1/" + endpoint).openConnection();
    genericRequest.setDoOutput(true)
    genericRequest.setRequestProperty("Content-Type", "application/json")
    genericRequest.setRequestProperty("Authorization", "Bearer eyJ2ZXIiOjMsInZhdiI6MSwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJnIjp7Imd1aSI6InVzZXItNTAyMmZjY2Y4Mjc2NDdlZTljZmI2M2I3NzlkNjIxOTMtVEVTVCIsImdtaSI6InVzZXItNTAyMmZjY2Y4Mjc2NDdlZTljZmI2M2I3NzlkNjIxOTMtVEVTVCIsInRtaSI6InVzZXItNTAyMmZjY2Y4Mjc2NDdlZTljZmI2M2I3NzlkNjIxOTMtVEVTVCJ9LCJhdWQiOiJBUElfS0VZIiwiaXNzIjoiU0VSVklDRVNfVjEiLCJpYXQiOjE1MTY4MjI0MzIuOTU2LCJqdGkiOiJiYWRnZS01NGYyNjVlZTMyYzI0Mjg3YTk3ZDhmNDlmOWYyOGY5MSIsInBhcmVudEp0aSI6ImJhZGdlLWQ5OTY5YzZmZGZjYjRmODM5M2EwMTA5MjllMmYwOGVmIiwic2NvcGVzIjpbXSwicm9sZXMiOlsiYWNjb3VudE1hbmFnZXIiLCJjb250YWN0TWFuYWdlciIsImN1c3RvbWVyU2VydmljZU1hbmFnZXIiLCJjdXN0b21lclNlcnZpY2VSZXByZXNlbnRhdGl2ZSIsInBvaW50T2ZTYWxlIiwicHJvZ3JhbU1hbmFnZXIiLCJwcm9tb3RlciIsInJlcG9ydGVyIiwic2VjdXJpdHlNYW5hZ2VyIiwidGVhbUFkbWluIiwid2ViUG9ydGFsIl19.CRFk2CzzgKTRJFX_QOuOFzyDGmscLEoB9OLwy1jKuHw")
    return genericRequest
}

/*
EXAMPLES

   <refresh-script>
                {
                    "method": "POST",
                    "body": {
                                "userSuppliedId": "example-contact-1",
                                "email": "alice@example.com"
                            },
                    "endpoint":"contacts"
                }
            </refresh-script>

            <replace-response/>
 */

