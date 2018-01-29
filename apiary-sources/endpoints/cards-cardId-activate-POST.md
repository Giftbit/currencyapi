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
    
            {"transaction":{"cardId":"card-996570b110d045cfaaa971a6a0b55409","cardType":"GIFT_CARD","codeLastFour":"ZVRK","currency":"USD","dateCreated":"2018-01-29T20:26:19.619Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":5000,"valueAvailableAfterTransaction":5000,"valueStoreId":"value-82d9f25fd4fc46a785c299e130b5d142"}],"transactionId":"transaction-9067b10939f341cd8bf6ce146bd66279","transactionType":"ACTIVATE","userSuppliedId":"activate-giftcard-a45etsraw","value":5000,"valueAvailableAfterTransaction":5000}}