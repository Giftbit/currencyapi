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

            {
                "categories":[
                    {
                        "categoryId": "category-46xx15",
                        "key": "giftbit_program",
                        "value": "program-dexx72"
                    },
                    {
                        "categoryId": "category-daxxd9",
                        "key": "giftbit_order",
                        "value": "2017-06-05"
                    }
            }
