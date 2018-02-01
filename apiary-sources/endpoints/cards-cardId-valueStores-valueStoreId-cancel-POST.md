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
    
            {"userSuppliedId":"transaction-6c8afefe4a63420bae6573cce57bcf9b-cancel"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-d99142eac3bd4abfbe657db000b614f6","cardType":"ACCOUNT_CARD","currency":"USD","dateCreated":"2018-01-29T20:26:21.231Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-150,"valueAvailableAfterTransaction":0,"valueStoreId":"value-8be6b00b5ed148ba98d824a0d30434aa"}],"transactionId":"transaction-7319e692ec4840c396bb3195e3574dfd","transactionType":"CANCELLATION","userSuppliedId":"transaction-6c8afefe4a63420bae6573cce57bcf9b-cancel","value":-150,"valueAvailableAfterTransaction":0}}
