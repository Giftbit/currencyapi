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
        
            {REQUEST_REPLACEMENT:capturePendingTransactionAccountCard.body}

+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {REQUEST_REPLACEMENT:capturePendingTransactionAccountCard.response.body}

