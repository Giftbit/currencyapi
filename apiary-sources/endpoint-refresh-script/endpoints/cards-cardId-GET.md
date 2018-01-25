### Retrieve Card [GET /cards/{cardId}]
Retrieve the Card object by its `cardId`. 

---
+ Parameters 
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

+ Response 200

    + Attributes 
        + card (Card)

    + Body
        
            {
              "card":{
                "cardId": "card-6aa566c9e25f45f889b475a079675a89",
                "userSuppliedId": "giftcard-d37e",
                "contactId": null,
                "dateCreated": "2018-01-15T23:09:15.682Z",
                "categories":[
                  {
                    "categoryId": "category-e595f03510f14c389866c3f7ed12cfd7",
                    "key": "giftbit_order",
                    "value": "2018-01-15"
                  },
                  {
                    "categoryId": "category-621196a93a1e4ee3a55949ca6fa34291",
                    "key": "giftbit_program",
                    "value": "program-4adf6d039dba4232876011232560f2cd"
                  }
                ],
                "cardType": "GIFT_CARD",
                "currency": "USD"
              }
            }
