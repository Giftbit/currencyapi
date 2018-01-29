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
    
            {"transaction":{"cardId":"card-7ae4aec62e2845d1b9eb8c7d77e7936e","cardType":"GIFT_CARD","codeLastFour":"268N","currency":"USD","dateCreated":"2018-01-29T18:33:05.525Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":5000,"valueAvailableAfterTransaction":5000,"valueStoreId":"value-067154396488410aa9bc976ad6e5c78c"}],"transactionId":"transaction-d0d52d3f596a4141b39580209deb423f","transactionType":"ACTIVATE","userSuppliedId":"activate-giftcard-a45etsraw","value":5000,"valueAvailableAfterTransaction":5000}}