<a name="post-transaction-by-cardid-anchor"></a>

### Create Transaction Based on Card ID [POST /cards/{cardId}/transactions]
Creates a transaction against a Card based on its `cardId`.
Transactions can be created as pending which locks the value required for the Transaction until it is either captured or voided. 

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + value (number) - {{transaction.value}}
        + currency (required) - {{currency}}
        + metadata (Metadata, optional) - {{transaction.metadata}}
        + pending (boolean, optional) - {{transaction.pending}}
        + userSuppliedId (string, required) - {{userSuppliedId}}        
        
    + Body 
        
        {REQUEST_REPLACEMENT:createTransaction1Card1.body}
    
            
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {REQUEST_REPLACEMENT:createTransaction1Card1.response.body}

