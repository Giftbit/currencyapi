### List Categories [GET /categories]
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + categories (array[Category])

    + Body

            {
              "categories":[
                {
                  "categoryId": "category-621196a93a1e4ee3a55949ca6fa34291",
                  "key": "giftbit_program",
                  "value": "program-4adf6d039dba4232876011232560f2cd"
                },
                {
                  "categoryId": "category-6f85f376f743451ab4dda5bbef6d0230",
                  "key": "giftbit_program",
                  "value": "program-1d404778285644078a2c1c9432929c13"
                },
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
              "pagination":{
                "count": 5,
                "limit": 100,
                "maxLimit": 1000,
                "offset": 0,
                "totalCount": 5
              }
            }

