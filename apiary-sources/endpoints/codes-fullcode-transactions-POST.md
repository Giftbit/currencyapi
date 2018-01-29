
### Create Transaction Based on Gift Code [POST /codes/{fullcode}/transactions{?pin}] 

<a name="post-transaction-by-fullcode-anchor"></a>

Creates a drawdown transaction against a Gift Card based on its `fullcode`. 
Since this endpoint is designed to facilitate redemption, it only allows drawdown transactions, so, the `value` must be a negative integer. 

If the Transactions is created as pending, the value will be locked until it is either captured or voided.
Note that there are no Lightrail endpoints for capturing or voiding a pending transaction based on `fullcode`, 
so, when creating a pending Transaction you need to save the `cardId` from the response to this call in order to eventually capture or void it. 


---
+ Parameters
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + value (number) - A negative integer indicating the value of the transaction in the smallest unit for the currency, e.g. cents for USD.
        + currency (required) - {{currency}}
        + metadata (Metadata, optional) - {{transaction.metadata}}
        + pending (boolean, optional) - {{transaction.pending}}
        + userSuppliedId (string, required) - {{userSuppliedId}}        
        
    + Body 
    
            {"currency":"USD","userSuppliedId":"transaction-234aweras4","value":-100}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","codeLastFour":"VJNC","currency":"USD","dateCreated":"2018-01-29T19:21:46.880Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","transactionAccessMethod":"RAWCODE","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c"}],"transactionId":"transaction-082164b5d7da45b297574672c0a53b63","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234aweras4","value":-100,"valueAvailableAfterTransaction":4650}}