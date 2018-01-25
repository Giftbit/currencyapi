### Activate Card [POST /cards/{cardId}/activate]
If a card has been created as inactive, you can use this endpoint to activate.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}} The Card must have been created with `"inactive":true`.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        
    + Body 
    
            {
              "userSuppliedId":"activate-1"
            }
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body
    
            {
              "transaction":{
                "transactionId":"transaction-7axx2f",
                "value":500,
                "userSuppliedId":"activate-1",
                "dateCreated":"2017-07-28T22:12:19.924Z",
                "transactionType":"ACTIVATE",
                "transactionAccessMethod":"CARDID",
                "valueAvailableAfterTransaction":500,
                "giftbitUserId":"user-1dfxx32",
                "cardId":"card-8bxxa9",
                "currency":"USD",
                "codeLastFour":"99SY"
              }
            }