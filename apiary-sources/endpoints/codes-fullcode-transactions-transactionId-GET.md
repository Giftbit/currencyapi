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
    
            {"transaction":{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","codeLastFour":"VJNC","currency":"USD","dateCreated":"2018-01-29T19:21:46.880Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","transactionAccessMethod":"RAWCODE","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c"}],"transactionId":"transaction-082164b5d7da45b297574672c0a53b63","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234aweras4","value":-100,"valueAvailableAfterTransaction":4650}}

