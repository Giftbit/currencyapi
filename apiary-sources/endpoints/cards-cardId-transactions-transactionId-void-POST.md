### Void Pending Transaction [POST /cards/{cardId}/transactions/{transactionId}/void]
Voids a pending Transaction by unlocking the value withheld by the pending Transaction.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + transactionId (string, required) - {{transaction.transactionId}} Must be a pending Transaction.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}} 
        + metadata (Metadata, optional) - {{metadata}}
    
    + Body 
            
            {"userSuppliedId":"transaction-96ca273f8b0d4e78a86bb6392bf51d1a-void"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)
        
    + Body

            {"transaction":{"cardId":"card-d99142eac3bd4abfbe657db000b614f6","cardType":"ACCOUNT_CARD","currency":"USD","dateCreated":"2018-01-29T20:26:20.810Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","metadata":{"giftbit_initial_transaction_id":"transaction-96ca273f8b0d4e78a86bb6392bf51d1a"},"parentTransactionId":"transaction-96ca273f8b0d4e78a86bb6392bf51d1a","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":100,"valueAvailableAfterTransaction":500,"valueStoreId":"value-fc28fcd3f48140fa90b8ea66a479ccdf"}],"transactionId":"transaction-736d945905604f0990be592efdfed6d2","transactionType":"PENDING_VOID","userSuppliedId":"transaction-96ca273f8b0d4e78a86bb6392bf51d1a-void","value":100,"valueAvailableAfterTransaction":500}}

