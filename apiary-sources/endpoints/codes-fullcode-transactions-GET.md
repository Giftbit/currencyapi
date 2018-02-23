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

            {"transactions":[{"value":-100,"userSuppliedId":"transaction-234aweras4","dateCreated":"2018-02-21T00:23:20.303Z","transactionType":"DRAWDOWN","transactionAccessMethod":"RAWCODE","valueAvailableAfterTransaction":4650,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-2923ec5903ab47c3b3d60f6813f107a1"}],"transactionId":"transaction-51b9e07d4ca04867b7fef4f12ab47c7c","codeLastFour":"NC3D"},{"value":-250,"userSuppliedId":"transaction-234342","dateCreated":"2018-02-21T00:23:20.148Z","transactionType":"DRAWDOWN","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":4750,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":-250,"valueAvailableAfterTransaction":4750,"valueStoreId":"value-2923ec5903ab47c3b3d60f6813f107a1"}],"transactionId":"transaction-4b938172cc9142e2b7c2474efb6de811","metadata":{"cart":{"total":250,"items":[{"quantity":1,"id":"B000F34ZKS","unit_price":150,"tags":["gear","outdoor","clearance"]},{"quantity":2,"id":"B009L1MF7A","unit_price":100,"tags":["apparel","outdoor"]}]}},"codeLastFour":"NC3D"},{"value":5000,"userSuppliedId":"giftcard-3asfd34a","dateCreated":"2018-02-21T00:23:18.350Z","transactionType":"INITIAL_VALUE","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":5000,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":5000,"valueAvailableAfterTransaction":5000,"valueStoreId":"value-2923ec5903ab47c3b3d60f6813f107a1"}],"transactionId":"transaction-4503204807d443a094fb753d2e2d151d","codeLastFour":"NC3D"}],"pagination":{"count":3,"limit":100,"maxLimit":1000,"offset":0,"totalCount":3}}
