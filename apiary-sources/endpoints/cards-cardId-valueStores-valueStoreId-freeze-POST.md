### Freeze ValueStore [POST /cards/{cardId}/valueStores/{valueStoreId}/freeze]
Freeze a Card's ValueStore, preventing all transactions against that ValueStore until unfrozen. 

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
    
            {"userSuppliedId":"value-a446413ee82d456c88cb478ed44e5279-freeze"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"value":-150,"userSuppliedId":"value-a446413ee82d456c88cb478ed44e5279-freeze","dateCreated":"2018-02-21T00:23:22.150Z","transactionType":"FREEZE","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":0,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-4085e168a3924df7a6097010674a77ff","currency":"USD","cardType":"ACCOUNT_CARD","transactionBreakdown":[{"value":-150,"valueAvailableAfterTransaction":0,"valueStoreId":"value-a446413ee82d456c88cb478ed44e5279"}],"transactionId":"transaction-eecb2de3fcfd4dd88c701fc09999f13c"}}

