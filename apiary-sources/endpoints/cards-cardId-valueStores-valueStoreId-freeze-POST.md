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
    
            {"userSuppliedId":"value-8be6b00b5ed148ba98d824a0d30434aa-freeze"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-d99142eac3bd4abfbe657db000b614f6","cardType":"ACCOUNT_CARD","currency":"USD","dateCreated":"2018-01-29T20:26:21.011Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-150,"valueAvailableAfterTransaction":0,"valueStoreId":"value-8be6b00b5ed148ba98d824a0d30434aa"}],"transactionId":"transaction-6f8e652d545541c6adcc09312b506ebf","transactionType":"FREEZE","userSuppliedId":"value-8be6b00b5ed148ba98d824a0d30434aa-freeze","value":-150,"valueAvailableAfterTransaction":0}}

