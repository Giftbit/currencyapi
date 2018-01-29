### Show Transaction Based on Gift Code [GET /codes/{fullcode}/transactions/{transactionId}{?pin}]
Retrieves a particular Transaction by its ID based on the `fullcode`.

---
+ Parameters
    + transactionId (string, required) - {{transaction.transactionId}}
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.
    

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + transaction (Transaction)
        
    + Body 
    
            {RESPONSE_REPLACEMENT:createTransaction2Card1Fullcode.response.body}

