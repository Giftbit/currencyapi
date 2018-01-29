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
    
            {"userSuppliedId":"freeze-transaction-9067b10939f341cd8bf6ce146bd66279"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-996570b110d045cfaaa971a6a0b55409","cardType":"GIFT_CARD","codeLastFour":"ZVRK","currency":"USD","dateCreated":"2018-01-29T20:26:19.711Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-5000,"valueAvailableAfterTransaction":0,"valueStoreId":"value-82d9f25fd4fc46a785c299e130b5d142"}],"transactionId":"transaction-4462590ec3d246ea8a3562e93584afae","transactionType":"FREEZE","userSuppliedId":"freeze-transaction-9067b10939f341cd8bf6ce146bd66279","value":-5000,"valueAvailableAfterTransaction":0}}  

