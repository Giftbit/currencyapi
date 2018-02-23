
### Simulate a Transaction and Balance-Check Based on Card ID [POST /cards/{cardId}/transactions/dryRun]
<a name="post-transaction-by-cardid-dryRun-anchor"></a>
Simulates creating a transaction against a Card based on its `cardId` and returns the would-be Transaction. Note that the returned Transaction object does not have a `transactionId` because it is merely a simulation and not an actual Transaction.

The `nsf` attribute determines the behaviour of this endpoint in the case of insufficient funds. If `nsf` is set to `true` (which is the default), it will return an error indicating insufficient funds. 
If `nsf` is set to `false`, the response will return a hypothetical drawdown Transaction object with the maximum value the Card can pay, i.e. its available balance. 

It is particularly important to provide the full context of the Transaction, i.e. its metadata, if your system uses Redemption Rules, since in that case the 
effective balance of a Card varies depending on the metadata of the Transaction.

---

+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + value (number) - {{transaction.value}}
        + currency (required) - {{currency}}
        + nsf (boolean, optional) - whether to return an error in case of insufficient funds or return a best-effort Transaction.
        + metadata (Metadata, optional) - {{transaction.metadata}}
        + userSuppliedId (string, required) - {{userSuppliedId}}

    + Body 
    
            {"userSuppliedId":"transaction-234342","value":-250,"currency":"USD","nsf":false,"metadata":{"cart":{"total":250,"items":[{"id":"B000F34ZKS","quantity":1,"unit_price":150,"tags":["gear","outdoor","clearance"]},{"id":"B009L1MF7A","quantity":2,"unit_price":100,"tags":["apparel","outdoor"]}]}}}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"value":-250,"userSuppliedId":"transaction-234342","dateCreated":null,"transactionType":"DRAWDOWN","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":4750,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":-250,"valueAvailableAfterTransaction":4750,"valueStoreId":"value-2923ec5903ab47c3b3d60f6813f107a1"}],"transactionId":null,"metadata":{"cart":{"total":250,"items":[{"quantity":1,"id":"B000F34ZKS","unit_price":150,"tags":["gear","outdoor","clearance"]},{"quantity":2,"id":"B009L1MF7A","unit_price":100,"tags":["apparel","outdoor"]}]}},"codeLastFour":"NC3D"}}

