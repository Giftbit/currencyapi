### List Card Transactions Based on Card ID [GET /cards/{cardId}/transactions{?userSuppliedId}{?transactionType}{?limit}{?offset}]
Retrieve a paginated list of a Card's Transactions based on the Card ID.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + userSuppliedId (string, optional) - return the transaction with the specific `userSuppliedId`. Note that since `userSuppliedId`s are unique, this guarantees a unique transaction in the response if it exists.
    + transactionType (string, optional) - return only transactions of a specific type.
    + limit (number, optional) - {{pagination.limit}}
    + offset (number, optional) - {{pagination.offset}}
    
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + transactions (array[Transaction])
        + pagination (Pagination)

    + Body
            
            {
              "transactions":[
                {
                  "value":-250,
                  "userSuppliedId":"order-s3xx30",
                  "dateCreated":"2018-01-15T23:22:27.185Z",
                  "transactionType":"DRAWDOWN",
                  "transactionAccessMethod":"CARDID",
                  "valueAvailableAfterTransaction":250,
                  "giftbitUserId":"user-b3dc5abb7a754911a68bc0e96cb8f028",
                  "cardId":"card-6bd0dee5efe047de9c2e3b11d11be97e",
                  "currency":"USD",
                  "cardType":"ACCOUNT_CARD",
                  "transactionBreakdown":[
                    {
                      "value":-250,
                      "valueAvailableAfterTransaction":250,
                      "valueStoreId":"value-a9cc0df45bd04fb692f9f0bcba4c1bd3"
                    }
                  ],
                  "transactionId":"transaction-a6266e574c574dff905493f04bc0b818",
                  "metadata":{
                    "cart":{
                      "total":25335,
                      "items":[
                        {
                          "quantity":1,
                          "id":"B000F34ZKS",
                          "unit_price":150,
                          "tags":[
                            "gear",
                            "outdoor",
                            "clearance"
                          ]
                        },
                        {
                          "quantity":2,
                          "id":"B009L1MF7A",
                          "unit_price":100,
                          "tags":[
                            "apparel",
                            "outdoor"
                          ]
                        }
                      ]
                    }
                  }
                },
                {
                  "value":500,
                  "userSuppliedId":"unfreeze-1",
                  "dateCreated":"2018-01-15T23:17:35.863Z",
                  "transactionType":"UNFREEZE",
                  "transactionAccessMethod":"CARDID",
                  "valueAvailableAfterTransaction":500,
                  "giftbitUserId":"user-b3dc5abb7a754911a68bc0e96cb8f028",
                  "cardId":"card-6bd0dee5efe047de9c2e3b11d11be97e",
                  "currency":"USD",
                  "cardType":"ACCOUNT_CARD",
                  "transactionBreakdown":[
                    {
                      "value":500,
                      "valueAvailableAfterTransaction":500,
                      "valueStoreId":"value-a9cc0df45bd04fb692f9f0bcba4c1bd3"
                    }
                  ],
                  "transactionId":"transaction-500aadddee694782b5086367eaf351af"
                },
                {
                  "value":-500,
                  "userSuppliedId":"freeze-1",
                  "dateCreated":"2018-01-15T23:16:37.955Z",
                  "transactionType":"FREEZE",
                  "transactionAccessMethod":"CARDID",
                  "valueAvailableAfterTransaction":0,
                  "giftbitUserId":"user-b3dc5abb7a754911a68bc0e96cb8f028",
                  "cardId":"card-6bd0dee5efe047de9c2e3b11d11be97e",
                  "currency":"USD",
                  "cardType":"ACCOUNT_CARD",
                  "transactionBreakdown":[
                    {
                      "value":-500,
                      "valueAvailableAfterTransaction":0,
                      "valueStoreId":"value-a9cc0df45bd04fb692f9f0bcba4c1bd3"
                    }
                  ],
                  "transactionId":"transaction-4fe35a77bebc492e94559afebf74c6b2"
                },
                {
                  "value":500,
                  "userSuppliedId":"alice-account-usd",
                  "dateCreated":"2018-01-15T22:52:19.129Z",
                  "transactionType":"INITIAL_VALUE",
                  "transactionAccessMethod":"CARDID",
                  "valueAvailableAfterTransaction":500,
                  "giftbitUserId":"user-b3dc5abb7a754911a68bc0e96cb8f028",
                  "cardId":"card-6bd0dee5efe047de9c2e3b11d11be97e",
                  "currency":"USD",
                  "cardType":"ACCOUNT_CARD",
                  "transactionBreakdown":[
                    {
                      "value":500,
                      "valueAvailableAfterTransaction":500,
                      "valueStoreId":"value-a9cc0df45bd04fb692f9f0bcba4c1bd3"
                    }
                  ],
                  "transactionId":"transaction-24e197166f6744bba2b9fb34fff03320"
                }
              ],
              "pagination":{
                "count":4,
                "limit":100,
                "maxLimit":1000,
                "offset":0,
                "totalCount":4
              }
            }