### Freeze Card [POST /cards/{cardId}/freeze]
Freeze a Card, preventing all transactions until unfrozen. 

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
    + Body 
    
            {RESPONSE_REPLACEMENT:createCardStateChange1Freeze.body}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {RESPONSE_REPLACEMENT:createCardStateChange1Freeze.response.body}  

