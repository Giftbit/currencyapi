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

            {"pagination":{"count":3,"limit":100,"maxLimit":1000,"offset":0,"totalCount":3},"transactions":[{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","codeLastFour":"2RZD","currency":"USD","dateCreated":"2018-01-29T20:26:18.992Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"RAWCODE","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-eefd98f834174294af3945d251f8441f"}],"transactionId":"transaction-4e8d9d0ecbf54288a0151b2347989489","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234aweras4","value":-100,"valueAvailableAfterTransaction":4650},{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","codeLastFour":"2RZD","currency":"USD","dateCreated":"2018-01-29T20:26:18.820Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","metadata":{"cart":{"items":[{"id":"B000F34ZKS","quantity":1,"tags":["gear","outdoor","clearance"],"unit_price":150},{"id":"B009L1MF7A","quantity":2,"tags":["apparel","outdoor"],"unit_price":100}],"total":250}},"transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-250,"valueAvailableAfterTransaction":4750,"valueStoreId":"value-eefd98f834174294af3945d251f8441f"}],"transactionId":"transaction-77e9457194134421ac6a26ceffd2f4c4","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234342","value":-250,"valueAvailableAfterTransaction":4750},{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","codeLastFour":"2RZD","currency":"USD","dateCreated":"2018-01-29T20:26:16.937Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":5000,"valueAvailableAfterTransaction":5000,"valueStoreId":"value-eefd98f834174294af3945d251f8441f"}],"transactionId":"transaction-7255dad2812f447b80f66c0fb95232d0","transactionType":"INITIAL_VALUE","userSuppliedId":"giftcard-3asfd34a","value":5000,"valueAvailableAfterTransaction":5000}]}
