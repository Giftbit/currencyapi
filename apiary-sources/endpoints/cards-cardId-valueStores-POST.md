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
    
            {"currency":"USD","initialValue":150,"programId":"program-17aae714584b4355a2232fdd6a719398","userSuppliedId":"card-d99142eac3bd4abfbe657db000b614f6-promotion1"}
    
+ Response 200
    + Attributes
        + valueStore (ValueStore)

    + Body

            {"valueStore":{"cardId":"card-d99142eac3bd4abfbe657db000b614f6","currency":"USD","dateCreated":"2018-01-29T20:26:20.921Z","programId":"program-17aae714584b4355a2232fdd6a719398","valueStoreId":"value-8be6b00b5ed148ba98d824a0d30434aa","valueStoreType":"ATTACHED"}}

