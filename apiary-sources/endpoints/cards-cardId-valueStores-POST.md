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
    
            {"userSuppliedId":"card-4085e168a3924df7a6097010674a77ff-promotion1","currency":"USD","programId":"program-a5a204c52df641deaa0511efad7a9578","initialValue":150}
    
+ Response 200
    + Attributes
        + valueStore (ValueStore)

    + Body

            {"valueStore":{"cardId":"card-4085e168a3924df7a6097010674a77ff","valueStoreId":"value-a446413ee82d456c88cb478ed44e5279","valueStoreType":"ATTACHED","currency":"USD","dateCreated":"2018-02-21T00:23:22.066Z","programId":"program-a5a204c52df641deaa0511efad7a9578"}}

