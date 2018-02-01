### Refund Transaction [POST /cards/{cardId}/transactions/{transactionId}/refund]
Refund a Transaction by reversing its effect. 

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + transactionId (string, required) - {{transaction.transactionId}} Must be an existing Transaction with transactionType `DRAWDOWN`.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        + metadata (Metadata, optional) - {{metadata}}

    
    + Body 
            
            {REQUEST_REPLACEMENT:refundTransactionAccountCard.body}
    
+ Response 200
    + Attributes
        + transaction (Transaction)
        
    + Body

            {REQUEST_REPLACEMENT:refundTransactionAccountCard.response.body}
            
