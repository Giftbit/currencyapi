
### Simulate a Transaction and Balance-Check Based on Card ID [POST /cards/{cardId}/transactions/dryRun]
<a name="post-transaction-by-cardid-dryRun-anchor"></a>
Simulates creating a transaction against a Card based on its `cardId` and returns the would-be Transaction. Note that the returned Transaction object does not have a `transactionId` because it is merely a simulation and not an actual Transaction.

The `nsf` attribute determines the behaviour of this endpoint in the case of insufficient funds. If `nsf` is set to `true` (which is the default), it will return an error indicating insufficient funds. 
If `nsf` is set to `false`, the response will return a hypothetical drawdown Transaction object with the maximum value the Card can pay, i.e. its available balance. 

It is particularly important to provide the full context of the Transaction, i.e. its metadata, if your system uses Redemption Rules, since in that case the 
effective balance of a Card varies depending on the metadata of the Transaction.

---

+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + value (number) - {{transaction.value}}
        + currency (required) - {{currency}}
        + nsf (boolean, optional) - whether to return an error in case of insufficient funds or return a best-effort Transaction.
        + metadata (Metadata, optional) - {{transaction.metadata}}
        + userSuppliedId (string, required) - {{userSuppliedId}}

    + Body 
    
            {
              "userSuppliedId":"order-s3xx30",
              "value":-5500,
              "currency":"USD",
              "nsf": false,
              "metadata": {
                 "cart": {
                    "total": 25335,
                    "items": [
                      {
                        "id": "B000F34ZKS", 
                        "quantity": 1,
                        "unit_price": 20695,
                        "tags": ["gear", "outdoor", "clearance"]
                      },
                      {
                        "id": "B009L1MF7A", 
                        "quantity": 2,
                        "unit_price": 2320,
                        "tags": ["apparel", "outdoor"]
                      }
                    ]
                 }
              }
            }
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {
              "transaction":{
                "value":-250,
                "userSuppliedId":"order-s3xx30",
                "dateCreated":null,
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
                "transactionId":null,
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
              }
            }

