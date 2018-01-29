### Activate Card [POST /cards/{cardId}/activate]
If a card has been created as inactive, you can use this endpoint to activate.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}} The Card must have been created with `"inactive":true`.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        
    + Body 
    
            {REQUEST_REPLACEMENT:createCardStateChange1Activate.body}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body
    
            {REQUEST_REPLACEMENT:createCardStateChange1Activate.response.body}