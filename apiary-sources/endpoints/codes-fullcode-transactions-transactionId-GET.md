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
    
            {"transaction":{"value":-100,"userSuppliedId":"transaction-234aweras4","dateCreated":"2018-02-21T00:23:20.303Z","transactionType":"DRAWDOWN","transactionAccessMethod":"RAWCODE","valueAvailableAfterTransaction":4650,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-2923ec5903ab47c3b3d60f6813f107a1"}],"transactionId":"transaction-51b9e07d4ca04867b7fef4f12ab47c7c","codeLastFour":"NC3D"}}

