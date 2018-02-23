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

            {"categories":[{"categoryId":"category-33461095d3e2462a8305769daea084a2","key":"giftbit_program","value":"program-db6ae8091edd4bdd97522f575e753c9a"},{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"}]}
