### Retrieve Account Cards or Gift Cards for Contact [GET /cards{?contactId}{?cardType}{?currency}]
Retrieve a paginated list of a Contact's Cards.

---
+ Parameters 
    + contactId (string, required) - The Lightrail Contact ID.
    + cardType (string, required) - {{card.cardType}}
    + currency (string, optional) - {{currency}} Only needed if the Contact has Account Cards in multiple currencies.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + cards (array[Card])
        + pagination (Pagination)

    + Body
    
            {
              "cards":[
                {
                    "cardId": "card-6bd0dee5efe047de9c2e3b11d11be97e",
                    "userSuppliedId": "alice-account-usd",
                    "contactId": "contact-83deaef84b804fb38ef92aea81ed134a",
                    "dateCreated": "2018-01-15T22:52:18.749Z",
                    "categories":[
                      {
                        "categoryId": "category-1b483e9c4e864a9fa9fe32b350ec85f9",
                        "key": "city",
                        "value": "seattle"
                      },
                      {
                        "categoryId": "category-e595f03510f14c389866c3f7ed12cfd7",
                        "key": "giftbit_order",
                        "value": "2018-01-15"
                      },
                      {
                        "categoryId": "category-fbbb512ccf564b198c15af55f83ba1ac",
                        "key": "giftbit_program",
                        "value": "program-account-USD-user-b3dc5abb7a754911a68bc0e96cb8f028"
                      }
                    ],
                    "cardType": "ACCOUNT_CARD",
                    "currency": "USD"
                }
              ],
              "pagination":{
                "count":1,
                "limit":100,
                "maxLimit":1000,
                "offset":0,
                "totalCount":1
              }
            }