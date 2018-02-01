### Retrieve Card [GET /cards/{cardId}]
Retrieve the Card object by its `cardId`. 

---
+ Parameters 
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

+ Response 200

    + Attributes 
        + card (Card)

    + Body
        
            {REQUEST_REPLACEMENT:getCard1Card.response.body}
