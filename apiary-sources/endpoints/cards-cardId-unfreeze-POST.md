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
    
            {"userSuppliedId":"unfreeze-transaction-4462590ec3d246ea8a3562e93584afae"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

           {"transaction":{"cardId":"card-996570b110d045cfaaa971a6a0b55409","cardType":"GIFT_CARD","codeLastFour":"ZVRK","currency":"USD","dateCreated":"2018-01-29T20:26:19.798Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":5000,"valueAvailableAfterTransaction":5000,"valueStoreId":"value-82d9f25fd4fc46a785c299e130b5d142"}],"transactionId":"transaction-0f2ece01348747cd8e670adc9c1a8351","transactionType":"UNFREEZE","userSuppliedId":"unfreeze-transaction-4462590ec3d246ea8a3562e93584afae","value":5000,"valueAvailableAfterTransaction":5000}}
