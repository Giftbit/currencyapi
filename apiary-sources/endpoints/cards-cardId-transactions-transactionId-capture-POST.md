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
        
            {"userSuppliedId":"transaction-64eb3b0cab034e00b1b90a6d72aa7616-capture"}

+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"value":-100,"userSuppliedId":"transaction-64eb3b0cab034e00b1b90a6d72aa7616-capture","dateCreated":"2018-02-21T00:23:21.652Z","transactionType":"DRAWDOWN","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":400,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-4085e168a3924df7a6097010674a77ff","currency":"USD","cardType":"ACCOUNT_CARD","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":400,"valueStoreId":"value-0de33ac5ca5243e7aab1b19cc2e90776"}],"transactionId":"transaction-48bf5e731f624e7d9b4325353377d9a2","parentTransactionId":"transaction-64eb3b0cab034e00b1b90a6d72aa7616","metadata":{"giftbit_initial_transaction_id":"transaction-64eb3b0cab034e00b1b90a6d72aa7616"}}}

