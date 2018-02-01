
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
    
            {"currency":"USD","metadata":{"cart":{"items":[{"id":"B000F34ZKS","quantity":1,"tags":["gear","outdoor","clearance"],"unit_price":150},{"id":"B009L1MF7A","quantity":2,"tags":["apparel","outdoor"],"unit_price":100}],"total":250}},"nsf":false,"userSuppliedId":"transaction-234342","value":-250}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","codeLastFour":"2RZD","currency":"USD","dateCreated":null,"giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","metadata":{"cart":{"items":[{"id":"B000F34ZKS","quantity":1,"tags":["gear","outdoor","clearance"],"unit_price":150},{"id":"B009L1MF7A","quantity":2,"tags":["apparel","outdoor"],"unit_price":100}],"total":250}},"transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-250,"valueAvailableAfterTransaction":4750,"valueStoreId":"value-eefd98f834174294af3945d251f8441f"}],"transactionId":null,"transactionType":"DRAWDOWN","userSuppliedId":"transaction-234342","value":-250,"valueAvailableAfterTransaction":4750}}

