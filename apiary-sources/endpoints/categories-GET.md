### List Categories [GET /categories]
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + categories (array[Category])

    + Body

            {"categories":[{"categoryId":"category-33461095d3e2462a8305769daea084a2","key":"giftbit_program","value":"program-db6ae8091edd4bdd97522f575e753c9a"},{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"},{"categoryId":"category-f0ce164180674c20aeacfe494cd8c4d2","key":"giftbit_program","value":"program-account-USD-user-717a97087fcf4ff4a603e3d7afa08951-TEST"}],"pagination":{"count":3,"limit":100,"maxLimit":1000,"offset":0,"totalCount":3}}

