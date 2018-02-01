### Retrieve Card [GET /cards/{cardId}]
Retrieve the Card object by its `cardId`. 

---
+ Parameters 
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

+ Response 200

    + Attributes 
        + card (Card)

    + Body
        
            {"card":{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","categories":[{"categoryId":"category-a5d681cddb4b4a6bbd5f44aa2e89b4a6","key":"giftbit_program","value":"program-37f8dc6bada64ace830affb1b7100b7f"},{"categoryId":"category-1a167e8f87a24edaa930b37c6c8ff628","key":"giftbit_order","value":"2018-01-29"}],"contactId":null,"currency":"USD","dateCreated":"2018-01-29T20:26:16.841Z","userSuppliedId":"giftcard-3asfd34a"}}
