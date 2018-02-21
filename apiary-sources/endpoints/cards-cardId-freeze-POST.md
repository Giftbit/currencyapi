### Freeze Card [POST /cards/{cardId}/freeze]
Freeze a Card, preventing all transactions until unfrozen. 

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
    + Body 
    
            {"userSuppliedId":"freeze-transaction-71e6471e1eb04a44ac1912db0ac95085"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"value":-5000,"userSuppliedId":"freeze-transaction-71e6471e1eb04a44ac1912db0ac95085","dateCreated":"2018-02-21T00:23:20.973Z","transactionType":"FREEZE","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":0,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-33182b81a2984116be6cbf655837b2e0","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":-5000,"valueAvailableAfterTransaction":0,"valueStoreId":"value-ea455f10ff314b09b5d2a24ceee7cbc5"}],"transactionId":"transaction-e5980d8555524023a83cedbf545e6a5d","codeLastFour":"3GPH"}}  

