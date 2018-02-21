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
    
            {"userSuppliedId":"transaction-c0a3ec8162cb4e498da6f918035f1e9f-cancel"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"value":-150,"userSuppliedId":"transaction-c0a3ec8162cb4e498da6f918035f1e9f-cancel","dateCreated":"2018-02-21T00:23:22.316Z","transactionType":"CANCELLATION","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":0,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-4085e168a3924df7a6097010674a77ff","currency":"USD","cardType":"ACCOUNT_CARD","transactionBreakdown":[{"value":-150,"valueAvailableAfterTransaction":0,"valueStoreId":"value-a446413ee82d456c88cb478ed44e5279"}],"transactionId":"transaction-8f769c59281846c3bd090e08ae1760e5"}}
