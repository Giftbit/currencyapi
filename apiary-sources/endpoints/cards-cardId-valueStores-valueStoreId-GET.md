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

            {"valueStore":{"cardId":"card-d99142eac3bd4abfbe657db000b614f6","currency":"USD","dateCreated":"2018-01-29T20:26:20.921Z","programId":"program-17aae714584b4355a2232fdd6a719398","valueStoreId":"value-8be6b00b5ed148ba98d824a0d30434aa","valueStoreType":"ATTACHED"}}

