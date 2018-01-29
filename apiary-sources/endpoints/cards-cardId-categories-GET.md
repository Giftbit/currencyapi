### List Categories on Card [GET /cards/{cardId}/categories]
Retrieve the list of all Categories associated with a given Card.

---

+ Parameters
    + cardId (string, required) - {{card.cardId}}
    
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + categories (array[Category])

    + Body

            {"categories":[{"categoryId":"category-1b2460a709cb4ce48ef2f2d3db93a86c","key":"giftbit_order","value":"2018-01-25"},{"categoryId":"category-2a5b01c657854ed6a35a3ef070d8f35a","key":"giftbit_program","value":"program-090711761f094f4baa11666e3432c44c"}]}
