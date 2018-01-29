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
        
        {"currency":"USD","metadata":{"cart":{"items":[{"id":"B000F34ZKS","quantity":1,"tags":["gear","outdoor","clearance"],"unit_price":150},{"id":"B009L1MF7A","quantity":2,"tags":["apparel","outdoor"],"unit_price":100}],"total":250}},"userSuppliedId":"transaction-234342","value":-250}
    
            
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","codeLastFour":"2RZD","currency":"USD","dateCreated":"2018-01-29T20:26:18.820Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","metadata":{"cart":{"items":[{"id":"B000F34ZKS","quantity":1,"tags":["gear","outdoor","clearance"],"unit_price":150},{"id":"B009L1MF7A","quantity":2,"tags":["apparel","outdoor"],"unit_price":100}],"total":250}},"transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-250,"valueAvailableAfterTransaction":4750,"valueStoreId":"value-eefd98f834174294af3945d251f8441f"}],"transactionId":"transaction-77e9457194134421ac6a26ceffd2f4c4","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234342","value":-250,"valueAvailableAfterTransaction":4750}}

