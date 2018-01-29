
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

            {"transaction":{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","codeLastFour":"2RZD","currency":"USD","dateCreated":"2018-01-29T20:26:18.992Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"RAWCODE","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-eefd98f834174294af3945d251f8441f"}],"transactionId":"transaction-4e8d9d0ecbf54288a0151b2347989489","transactionType":"DRAWDOWN","userSuppliedId":"transaction-234aweras4","value":-100,"valueAvailableAfterTransaction":4650}}