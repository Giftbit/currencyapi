### Freeze Card [POST /cards/{cardId}/freeze]
Freeze a Card, preventing all transactions until unfrozen. 

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
    + Body 
    
            {
                "userSuppliedId":"freeze-1"
            }
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {
              "transaction":{
                "value": -500,
                "userSuppliedId": "freeze-1",
                "dateCreated": "2018-01-15T23:16:37.955Z",
                "transactionType": "FREEZE",
                "transactionAccessMethod": "CARDID",
                "valueAvailableAfterTransaction": 0,
                "giftbitUserId": "user-b3dc5abb7a754911a68bc0e96cb8f028",
                "cardId": "card-6bd0dee5efe047de9c2e3b11d11be97e",
                "currency": "USD",
                "cardType": "ACCOUNT_CARD",
                "transactionBreakdown":[
                  {
                    "value": -500,
                    "valueAvailableAfterTransaction": 0,
                    "valueStoreId": "value-a9cc0df45bd04fb692f9f0bcba4c1bd3"
                  }
                ],
                "transactionId": "transaction-4fe35a77bebc492e94559afebf74c6b2"
              }
            }       

