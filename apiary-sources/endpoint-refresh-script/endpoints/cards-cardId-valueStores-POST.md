### Add ValueStore [POST /cards/{cardId}/valueStores]
Adds a ValueStore from a Promotion Program to a Card. 

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        + currency (string, required) - {{currency}}
        + programId (string, required) - The unique id of the Promotion Program. Note the Program's `ValueStoreType` must be of type `ATTACHED`.
        + expires (string, optional) - Defaults to lifespan set by program.
        + startDate (string, optional) - The date for which the ValueStore will become usable.
        + initialValue (number, optional) - The initial value of the ValueStore.
        
    + Body 
    
            {REQUEST_REPLACEMENT:addPromotionToAccount.body}
    
+ Response 200
    + Attributes
        + valueStore (ValueStore)

    + Body

            {REQUEST_REPLACEMENT:addPromotionToAccount.response.body}

