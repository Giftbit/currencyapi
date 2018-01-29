### Simulate a Transaction and Balance-Check Based on Gift Code [POST /cards/{fullcode}/transactions/dryRun{?pin}]
<a name="post-transaction-by-code-dryRun-anchor"></a>
Simulates creating a transaction against a Gift Card based on its `fullcode` and returns the would-be Transaction. Note that the returned Transaction object does not have a `transactionId` because it is merely a simulation and not an actual Transaction.

The `nsf` attribute determines the behaviour of this endpoint in the case of insufficient funds. If `nsf` is set to `true` (which is the default), it will return an error indicating insufficient funds. 
If `nsf` is set to `false`, the response will return a hypothetical drawdown Transaction object with the maximum value the Card can pay, i.e. its available balance. 

It is particularly important to provide the full context of the Transaction, i.e. its metadata, if your system uses Redemption Rules, since in that case the 
effective balance of a Card varies depending on the metadata of the Transaction.

---

+ Parameters
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.

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
    
            {"currency":"USD","userSuppliedId":"transaction-234aweras4","value":-100}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","codeLastFour":"VJNC","currency":"USD","dateCreated":"2018-01-29T19:21:46.880Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","transactionAccessMethod":"RAWCODE","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c"}],"transactionId":"transaction-082164b5d7da45b297574672c0a53b63","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234aweras4","value":-100,"valueAvailableAfterTransaction":4650}}
