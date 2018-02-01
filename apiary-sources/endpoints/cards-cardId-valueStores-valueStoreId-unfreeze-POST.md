### Unfreeze ValueStore [POST /cards/{cardId}/valueStores/{valueStoreId}/unfreeze]
Unfreeze a Card's frozen ValueStore, re-enabling the creation of transactions against that ValueStore.

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
    
            {"userSuppliedId":"transaction-6f8e652d545541c6adcc09312b506ebf-Unfreeze"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-d99142eac3bd4abfbe657db000b614f6","cardType":"ACCOUNT_CARD","currency":"USD","dateCreated":"2018-01-29T20:26:21.104Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":150,"valueAvailableAfterTransaction":150,"valueStoreId":"value-8be6b00b5ed148ba98d824a0d30434aa"}],"transactionId":"transaction-6c8afefe4a63420bae6573cce57bcf9b","transactionType":"UNFREEZE","userSuppliedId":"transaction-6f8e652d545541c6adcc09312b506ebf-Unfreeze","value":150,"valueAvailableAfterTransaction":150}}

