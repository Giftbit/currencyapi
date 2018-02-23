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
            
            {"userSuppliedId":"transaction-2a47a9a1f8b1483b82f8692709963025-void"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)
        
    + Body

            {"transaction":{"value":100,"userSuppliedId":"transaction-2a47a9a1f8b1483b82f8692709963025-void","dateCreated":"2018-02-21T00:23:21.943Z","transactionType":"PENDING_VOID","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":500,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-4085e168a3924df7a6097010674a77ff","currency":"USD","cardType":"ACCOUNT_CARD","transactionBreakdown":[{"value":100,"valueAvailableAfterTransaction":500,"valueStoreId":"value-0de33ac5ca5243e7aab1b19cc2e90776"}],"transactionId":"transaction-1262387353e048ed9b215da932fe930a","parentTransactionId":"transaction-2a47a9a1f8b1483b82f8692709963025","metadata":{"giftbit_initial_transaction_id":"transaction-2a47a9a1f8b1483b82f8692709963025"}}}

