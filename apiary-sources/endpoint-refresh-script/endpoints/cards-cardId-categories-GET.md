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

            {REQUEST_REPLACEMENT:getCard1Categories.response.body}
