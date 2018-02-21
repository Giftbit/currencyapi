### Unfreeze Card [POST /cards/{cardId}/unfreeze]
Unfreeze a frozen Card, re-enabling the creation of transactions.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required): {{userSuppliedId}}
   
    + Body 
    
            {"userSuppliedId":"unfreeze-transaction-e5980d8555524023a83cedbf545e6a5d"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

           {"transaction":{"value":5000,"userSuppliedId":"unfreeze-transaction-e5980d8555524023a83cedbf545e6a5d","dateCreated":"2018-02-21T00:23:21.060Z","transactionType":"UNFREEZE","transactionAccessMethod":"CARDID","valueAvailableAfterTransaction":5000,"giftbitUserId":"user-717a97087fcf4ff4a603e3d7afa08951-TEST","cardId":"card-33182b81a2984116be6cbf655837b2e0","currency":"USD","cardType":"GIFT_CARD","transactionBreakdown":[{"value":5000,"valueAvailableAfterTransaction":5000,"valueStoreId":"value-ea455f10ff314b09b5d2a24ceee7cbc5"}],"transactionId":"transaction-f74640609d38482dac0c77f05c1bc932","codeLastFour":"3GPH"}}
