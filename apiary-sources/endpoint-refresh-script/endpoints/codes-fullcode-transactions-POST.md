
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
    
            {RESPONSE_REPLACEMENT:createTransaction2Card1Fullcode.body}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {RESPONSE_REPLACEMENT:createTransaction2Card1Fullcode.response.body}