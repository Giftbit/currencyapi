### Unfreeze Card [POST /cards/{cardId}/unfreeze]
Unfreeze a frozen Card, re-enabling the creation of transactions.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required): {{userSuppliedId}}
   
    + Body 
    
            {
              "userSuppliedId":"unfreeze-1" 
            }
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {
              "transaction":{
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
              }
            }
