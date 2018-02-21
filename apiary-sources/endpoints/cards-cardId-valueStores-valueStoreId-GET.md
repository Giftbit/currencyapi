### Show ValueStore [GET /cards/{cardId}/valueStores/{valueStoreId}]
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + valueStoreId (string, required) - The Lightrail ValueStore ID.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    
+ Response 200
    + valueStore (ValueStore)

    + Body

            {"valueStore":{"cardId":"card-4085e168a3924df7a6097010674a77ff","valueStoreId":"value-a446413ee82d456c88cb478ed44e5279","valueStoreType":"ATTACHED","currency":"USD","dateCreated":"2018-02-21T00:23:22.066Z","programId":"program-a5a204c52df641deaa0511efad7a9578"}}

