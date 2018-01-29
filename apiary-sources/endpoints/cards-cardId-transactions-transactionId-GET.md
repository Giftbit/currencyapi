### Show Transaction Based on Card ID [GET /cards/{cardId}/transactions/{transactionId}]
Retrieve a particular Transaction by its ID based on the `cardId`.

---

+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + transactionId (string, required) - {{transaction.transactionId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","codeLastFour":"VJNC","currency":"USD","dateCreated":"2018-01-25T01:08:29.325Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","metadata":{"cart":{"items":[{"id":"B000F34ZKS","quantity":1,"tags":["gear","outdoor","clearance"],"unit_price":150},{"id":"B009L1MF7A","quantity":2,"tags":["apparel","outdoor"],"unit_price":100}],"total":250}},"transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-250,"valueAvailableAfterTransaction":4750,"valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c"}],"transactionId":"transaction-83ba46e3a5694c02abdd3b72c0c5365d","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234342","value":-250,"valueAvailableAfterTransaction":4750}}

