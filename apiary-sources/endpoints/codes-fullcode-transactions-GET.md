### List Card Transactions Based on Gift Code [GET /codes/{fullcode}/transactions{?pin}{?userSuppliedId}{?transactionType}{?limit}{?offset}]
Retrieve a paginated list of a Card's Transactions based on the Gift Card's `fullcode`.

---
+ Parameters
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.
    + userSuppliedId (string, optional) - return the transaction with the specific `userSuppliedId`. Note that since `userSuppliedId`s are unique, this guarantees a unique transaction in the response if it exists.
    + transactionType (string, optional) - return only transactions of a specific type.
    + limit (number, optional) - {{pagination.limit}}
    + offset (number, optional) - {{pagination.offset}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + transactions (array[Transaction])
        + pagination (Pagination)

    + Body

            {"pagination":{"count":3,"limit":100,"maxLimit":1000,"offset":0,"totalCount":3},"transactions":[{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","codeLastFour":"VJNC","currency":"USD","dateCreated":"2018-01-29T19:21:46.880Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","transactionAccessMethod":"RAWCODE","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c"}],"transactionId":"transaction-082164b5d7da45b297574672c0a53b63","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234aweras4","value":-100,"valueAvailableAfterTransaction":4650},{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","codeLastFour":"VJNC","currency":"USD","dateCreated":"2018-01-25T01:08:29.325Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","metadata":{"cart":{"items":[{"id":"B000F34ZKS","quantity":1,"tags":["gear","outdoor","clearance"],"unit_price":150},{"id":"B009L1MF7A","quantity":2,"tags":["apparel","outdoor"],"unit_price":100}],"total":250}},"transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-250,"valueAvailableAfterTransaction":4750,"valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c"}],"transactionId":"transaction-83ba46e3a5694c02abdd3b72c0c5365d","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234342","value":-250,"valueAvailableAfterTransaction":4750},{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","codeLastFour":"VJNC","currency":"USD","dateCreated":"2018-01-25T01:08:28.716Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":5000,"valueAvailableAfterTransaction":5000,"valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c"}],"transactionId":"transaction-415f9c3f6c8e4d9591d97bca612890aa","transactionType":"INITIAL_VALUE","userSuppliedId":"giftcard-3asfd34a","value":5000,"valueAvailableAfterTransaction":5000}]}
