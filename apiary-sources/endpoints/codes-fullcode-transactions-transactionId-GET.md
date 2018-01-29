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
    
            {"transaction":{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","codeLastFour":"2RZD","currency":"USD","dateCreated":"2018-01-29T20:26:18.992Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"RAWCODE","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-eefd98f834174294af3945d251f8441f"}],"transactionId":"transaction-4e8d9d0ecbf54288a0151b2347989489","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234aweras4","value":-100,"valueAvailableAfterTransaction":4650}}

