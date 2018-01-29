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
    
            {"userSuppliedId":"unfreeze-transaction-c7193ff982bf49c9919defcbcec18ad5"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

           {"transaction":{"cardId":"card-7ae4aec62e2845d1b9eb8c7d77e7936e","cardType":"GIFT_CARD","codeLastFour":"268N","currency":"USD","dateCreated":"2018-01-29T18:33:05.733Z","giftbitUserId":"user-5022fccf827647ee9cfb63b779d62193-TEST","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":5000,"valueAvailableAfterTransaction":5000,"valueStoreId":"value-067154396488410aa9bc976ad6e5c78c"}],"transactionId":"transaction-8b80db5edcc24bd2afe3272b70e08d24","transactionType":"UNFREEZE","userSuppliedId":"unfreeze-transaction-c7193ff982bf49c9919defcbcec18ad5","value":5000,"valueAvailableAfterTransaction":5000}}
