### Cancel ValueStore [POST /cards/{cardId}/valueStores/{valueStoreId}/cancel]
Permanently cancels a Card's ValueStore.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + valueStoreId (string, required) - The Lightrail ValueStore ID.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        
    + Body 
    
            {REQUEST_REPLACEMENT:cancelPromotionOnAccount.body}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {REQUEST_REPLACEMENT:cancelPromotionOnAccount.response.body}
