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

            {REQUEST_REPLACEMENT:addPromotionToAccount.response.body}

