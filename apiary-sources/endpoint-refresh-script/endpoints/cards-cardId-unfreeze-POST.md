### Unfreeze Card [POST /cards/{cardId}/unfreeze]
Unfreeze a frozen Card, re-enabling the creation of transactions.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required): {{userSuppliedId}}
   
    + Body 
    
            {REQUEST_REPLACEMENT:createCardStateChange1Unfreeze.body}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

           {REQUEST_REPLACEMENT:createCardStateChange1Unfreeze.response.body}
