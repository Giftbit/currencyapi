# Endpoint Refresh Script
The point of this script is to be able to programatically update the response bodies from various api endpoints.

## Steps to run the script
1. Create a new account on lightraildev.net.
2. Switch to LIVE mode and generate an API key.
3. Paste the API key into the top of refreshScript.groovy.
4. Run the script. If in intelliJ simply right click on it. From command line: `groovy refreshScript`
5. Go to the ping output file and change the email to user@example.com. Specifically this file is `/Lightrail-API-Docs/endpoints/ping-GET.md`.
6. Remove the API key since if it's committed it could be used to retrieve your email.
7. Commit changes.  

## How it works
The script refreshScript.groovy processes the requests in requests.json. It uses a variable replacement strategy to replace variables in requests.json which allows it to make requests that use the output of other requests. 

The script also loads the files in endpoints folder within this directory and iterates through checking for any replacements they require. Specifically the script loads the file in `/Lightrail-API-Docs/endpoint-refresh-script/endpoints` and outputs the replaced version to `/Lightrail-API-Docs/endpoints/`.

Then when compile documents for apiary command is run, found in `/Lightrail-API-Docs/apiary-sources/README.md` the files in `/Lightrail-API-Docs/endpoints/` are copied into the apiary.apib file which is used for the Apiary documentation.  

### Format of requests in request.json
Example request:
```json
{
    "callId": "refundTransactionAccountCard",
    "method": "POST",
    "endpoint": "/cards/{REQUEST_REPLACEMENT:createAccountCard.response.body.card.cardId}/transactions/{REQUEST_REPLACEMENT:capturePendingTransactionAccountCard.response.body.transaction.transactionId}/refund",
    "body": {
        "userSuppliedId":"{REQUEST_REPLACEMENT:capturePendingTransactionAccountCard.response.body.transaction.transactionId}-refund"
    }
}
```

Every request has:
- callId (required and must be unique)
- method (required. POST, GET, PATCH, ...)
- endpoint (required. the script assumes the base path will be https://www.lightraildev.net/v1)
- body (optional, depends on method type)

The script iterates through requests. Before attempting the request it checks if there is a replacement required for the request. Once all replacements are complete within a request it can make the request, otherwise it skips the requests and moves onto other requests. It stores the entire response in a new response field of the json object. ie  
```json
{
    "callId": "refundTransactionAccountCard",
    "method": "POST",
    "endpoint": "/cards/card-abs3earszdfw3af/transactions/transation-123awers3a2rszf3/refund",
    "body": {
        "userSuppliedId":"transation-123awers3a2rszf3-refund"
    },
    "response": {
        "status": 200,
        "body": {
          "transaction": {
            "transactionId": "transaction-657rftuhd5s4r3wzad",
            "...": "..."
          }
        }
    }
}
```

#### How replacements work
{REQUEST_REPLACEMENT:callId.index.into.properties.of.json.object}

Examples:
```
"{REQUEST_REPLACEMENT":refundTransactionAccountCard.method}" -> "POST"
"{REQUEST_REPLACEMENT":refundTransactionAccountCard.response.body.transaction.transactionId}" -> "transaction-657rftuhd5s4r3wzad"
```

