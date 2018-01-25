### List Card Transactions Based on Gift Code [GET /codes/{fullcode}/transactions{?pin}{?userSuppliedId}{?transactionType}{?limit}{?offset}]
Retrieve a paginated list of a Card's Transactions based on the Gift Card's `fullcode`.

---
+ Parameters
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.
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
                  "transactionId":"transaction-e0xxf5",
                  "value":-599,
                  "userSuppliedId":"tx10",
                  "dateCreated":"2017-07-28T21:21:10.009Z",
                  "transactionType":"DRAWDOWN",
                  "transactionAccessMethod":"CARDID",
                  "valueAvailableAfterTransaction":1401,
                  "giftbitUserId":"user-1dxx32",
                  "cardId":"card-76xxab",
                  "currency":"USD",
                  "metadata":{
                    "giftbit_override_dateCreated":"2017-07-28T21:21:10.009Z"
                  }
                },
                {
                  "transactionId":"transaction-00xxa5",
                  "value":2000,
                  "userSuppliedId":"anonymous-giftcard10",
                  "dateCreated":"2017-07-13T21:21:00.601Z",
                  "transactionType":"INITIAL_VALUE",
                  "transactionAccessMethod":"CARDID",
                  "valueAvailableAfterTransaction":2000,
                  "giftbitUserId":"user-1dxx32",
                  "cardId":"card-76xxab",
                  "currency":"USD",                  
                }
              ],
              "pagination":{
                "count":2,
                "limit":100,
                "maxLimit":1000,
                "offset":0,
                "totalCount":2
              }
            }

