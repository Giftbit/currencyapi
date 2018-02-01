### List Categories [GET /categories]
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + categories (array[Category])

    + Body

            {"categories":[{"categoryId":"category-1a167e8f87a24edaa930b37c6c8ff628","key":"giftbit_order","value":"2018-01-29"},{"categoryId":"category-a5d681cddb4b4a6bbd5f44aa2e89b4a6","key":"giftbit_program","value":"program-37f8dc6bada64ace830affb1b7100b7f"}],"pagination":{"count":2,"limit":100,"maxLimit":1000,"offset":0,"totalCount":2}}

