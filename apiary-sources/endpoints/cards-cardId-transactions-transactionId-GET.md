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

            {"transaction":{"value":-250,"userSuppliedId":"transaction-234342","dateCreated":"2018-02-21T00:23:20.148Z","transactionType":"DRAWDOWN","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":4750,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":-250,"valueAvailableAfterTransaction":4750,"valueStoreId":"value-2923ec5903ab47c3b3d60f6813f107a1"}],"transactionId":"transaction-4b938172cc9142e2b7c2474efb6de811","metadata":{"cart":{"total":250,"items":[{"quantity":1,"id":"B000F34ZKS","unit_price":150,"tags":["gear","outdoor","clearance"]},{"quantity":2,"id":"B009L1MF7A","unit_price":100,"tags":["apparel","outdoor"]}]}},"codeLastFour":"NC3D"}}

