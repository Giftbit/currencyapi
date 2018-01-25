### Capture Pending Transaction [POST /cards/{cardId}/transactions/{transactionId}/capture]
Capture a pending Transaction, thereby collecting the value withheld by the pending Transaction.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + transactionId (string, required) - The `transactionId` of the pending Transaction.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        + metadata (Metadata, optional) - {{transaction.metadata}}

    
    + Body 
        
            {
              "userSuppliedId":"transaction-90xxf6-capture",
              "metadata": {
                  "cart": {
                    "total": 25335
                  }
              }
            }

+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {
              "transaction":{
                "transactionId":"transaction-38xx52",
                "value":-50,
                "userSuppliedId":"transaction-90xxf6-capture",
                "dateCreated":"2017-07-31T18:50:25.357Z",
                "transactionType":"DRAWDOWN",
                "transactionAccessMethod":"CARDID",
                "valueAvailableAfterTransaction":1349,
                "giftbitUserId":"user-1dxx32",
                "cardId":"card-76xxab",
                "currency":"USD",
                "parentTransactionId":"transaction-90xxf6",
                "metadata":{
                    "cart": {
                        "total": 25335
                    },
                    "giftbit_initial_transaction_id":"transaction-90xxf6"
                }
              }
            }

