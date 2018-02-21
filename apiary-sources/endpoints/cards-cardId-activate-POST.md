### Activate Card [POST /cards/{cardId}/activate]
If a card has been created as inactive, you can use this endpoint to activate.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}} The Card must have been created with `"inactive":true`.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        
    + Body 
    
            {"userSuppliedId":"activate-giftcard-a45etsraw"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body
    
            {"transaction":{"value":5000,"userSuppliedId":"activate-giftcard-a45etsraw","dateCreated":"2018-02-21T00:23:20.890Z","transactionType":"ACTIVATE","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":5000,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-33182b81a2984116be6cbf655837b2e0","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":5000,"valueAvailableAfterTransaction":5000,"valueStoreId":"value-ea455f10ff314b09b5d2a24ceee7cbc5"}],"transactionId":"transaction-71e6471e1eb04a44ac1912db0ac95085","codeLastFour":"3GPH"}}