### Cancel Card [POST /cards/{cardId}/cancel]
Cancel a Card permanently.

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
                "userSuppliedId": "cancel-1"
            }
    
+ Response 200

    + Body
    
            <<ourscriptbody>

            {
              "card":{
                "cardId":"card-8bxxa9",
                "userSuppliedId":"giftcard2",
                "contactId":null,
                "dateCreated":"2017-07-28T22:11:09.431Z",
                "categories":[
                  {
                    "categoryId":"category-3dxxc3",
                    "key":"giftbit_order",
                    "value":"2017-07-28"
                  },
                  {
                    "categoryId":"category-59xx67",
                    "key":"city",
                    "value":"seattle"
                  },
                  {
                    "categoryId":"category-66xxd2",
                    "key":"giftbit_status",
                    "value":"CANCELLED"
                  }
                ],
                "cardType":"GIFT_CARD",
                "currency":"USD"
              }
            }