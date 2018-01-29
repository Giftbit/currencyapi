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
    
            {"userSuppliedId":"freeze-transaction-d0d52d3f596a4141b39580209deb423f"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {"transaction":{"cardId":"card-7ae4aec62e2845d1b9eb8c7d77e7936e","cardType":"GIFT_CARD","codeLastFour":"268N","currency":"USD","dateCreated":"2018-01-29T18:33:05.622Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":-5000,"valueAvailableAfterTransaction":0,"valueStoreId":"value-067154396488410aa9bc976ad6e5c78c"}],"transactionId":"transaction-c7193ff982bf49c9919defcbcec18ad5","transactionType":"FREEZE","userSuppliedId":"freeze-transaction-d0d52d3f596a4141b39580209deb423f","value":-5000,"valueAvailableAfterTransaction":0}}  

