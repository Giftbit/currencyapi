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
            
            {REQUEST_REPLACEMENT:voidPendingTransactionAccountCard.body}
    
+ Response 200
    + Attributes
        + transaction (Transaction)
        
    + Body

            {REQUEST_REPLACEMENT:voidPendingTransactionAccountCard.response.body}

