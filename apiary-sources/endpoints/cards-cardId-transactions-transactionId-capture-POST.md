### Capture Pending Transaction [POST /cards/{cardId}/transactions/{transactionId}/capture]
Capture a pending Transaction, thereby collecting the value withheld by the pending Transaction.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + transactionId (string, required) - The `transactionId` of the pending Transaction.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        + metadata (Metadata, optional) - {{transaction.metadata}}

    
    + Body 
        
            {"userSuppliedId":"transaction-5fb7376a8c4a47dc95f05c340fa5f87a-capture"}

+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-d99142eac3bd4abfbe657db000b614f6","cardType":"ACCOUNT_CARD","currency":"USD","dateCreated":"2018-01-29T20:26:20.517Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","metadata":{"giftbit_initial_transaction_id":"transaction-5fb7376a8c4a47dc95f05c340fa5f87a"},"parentTransactionId":"transaction-5fb7376a8c4a47dc95f05c340fa5f87a","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":400,"valueStoreId":"value-fc28fcd3f48140fa90b8ea66a479ccdf"}],"transactionId":"transaction-aaff893f88164f94a47a4af1c3411900","transactionType":"DRAWDOWN","userSuppliedId":"transaction-5fb7376a8c4a47dc95f05c340fa5f87a-capture","value":-100,"valueAvailableAfterTransaction":400}}

