### Cancel Card [POST /cards/{cardId}/cancel]
Cancel a Card permanently.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
    
    + Body
            
            
            {REQUEST_REPLACEMENT:createCardStateChange1Cancel.body}
    
+ Response 200

    + Body
    
            {REQUEST_REPLACEMENT:createCardStateChange1Cancel.response.body}