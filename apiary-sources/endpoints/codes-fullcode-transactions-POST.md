
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
    
            {"userSuppliedId":"transaction-234aweras4","value":-100,"currency":"USD"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"value":-100,"userSuppliedId":"transaction-234aweras4","dateCreated":"2018-02-21T00:23:20.303Z","transactionType":"DRAWDOWN","transactionAccessMethod":"RAWCODE","valueAvailableAfterTransaction":4650,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":-100,"valueAvailableAfterTransaction":4650,"valueStoreId":"value-2923ec5903ab47c3b3d60f6813f107a1"}],"transactionId":"transaction-51b9e07d4ca04867b7fef4f12ab47c7c","codeLastFour":"NC3D"}}