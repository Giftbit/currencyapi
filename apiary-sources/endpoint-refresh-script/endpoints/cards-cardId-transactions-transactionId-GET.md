### Show Transaction Based on Card ID [GET /cards/{cardId}/transactions/{transactionId}]
Retrieve a particular Transaction by its ID based on the `cardId`.

---

+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + transactionId (string, required) - {{transaction.transactionId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            { 
                "transaction": {
                    "transactionId":"transaction-62xx70",
                    "value":-500,
                    "userSuppliedId":"example2",
                    "dateCreated":"2017-07-31T18:38:02.449Z",
                    "transactionType":"DRAWDOWN",
                    "transactionAccessMethod":"CARDID",
                    "valueAvailableAfterTransaction":1500,
                    "giftbitUserId":"user-1dxx32",
                    "cardId":"card-76xxab",
                    "currency":"USD",
                    "metadata": {
                      "cart": {
                        "total": 25335
                      }
                    }
                }
            }

