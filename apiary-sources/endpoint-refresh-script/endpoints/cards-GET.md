### List Cards [GET /cards/{?contactId}{?cardType}{?currency}{?userSuppliedId}{?limit}{?offset}]
Retrieve a paginated list of Cards. The search can be narrowed down based on various parameters.

---
+ Request (application/json)
    + Headers
    
             {{header.authorization}}
   
+ Parameter
{#    + categoryKey (string, optional) - A key of a Category. 
    + categoryValue (string, optional) - A value of a Category.  #}
    + contactId (string, optional) - Retrieve only the Cards belonging to a specific Contact.
    + cardType (string, optional) - {{card.cardType}}
    + currency (string, optional) - {{currency}}
    + userSuppliedId (string, optional) - Retrieve the Card created with a specific `userSuppliedId`. Note that since `userSuppliedId`s are unique, this guarantees that exactly one Card will be returned if it exists. 
    + limit (number, optional) - {{pagination.limit}}
    + offset (number, optional) - {{pagination.offset}}

+ Response 200
    + Attributes 
        + cards (array[Card])
        + pagination (Pagination)

    + Body
    
            {
              "cards":[
                {
                  "cardId":"card-a0xx82",
                  "userSuppliedId":"anonymous-giftcard28",
                  "contactId":null,
                  "dateCreated":"2017-07-28T21:21:00.606Z",
                  "categories":[
                    {
                      "categoryId":"category-3dxxc3",
                      "key":"giftbit_order",
                      "value":"2017-07-28"
                    },
                    {
                      "categoryId":"category-d2xx57",
                      "key":"giftbit_program",
                      "value":"program-93xxe3"
                    }
                  ],
                  "cardType":"GIFT_CARD",
                  "currency":"USD"
                },
                {
                  "cardId":"card-15xxbd",
                  "userSuppliedId":"anonymous-giftcard3",
                  "contactId":null,
                  "dateCreated":"2017-07-28T21:21:00.599Z",
                  "categories":[
                    {
                      "categoryId":"category-3dxxc3",
                      "key":"giftbit_order",
                      "value":"2017-07-28"
                    },
                    {
                      "categoryId":"category-d2xx57",
                      "key":"giftbit_program",
                      "value":"program-93xxe3"
                    }
                  ],
                  "cardType":"GIFT_CARD",
                  "currency":"USD"
                }
              ],
              "pagination":{
                "count":2,
                "limit":2,
                "maxLimit":1000,
                "offset":0,
                "totalCount":94
              }
            }

