### Cancel Card [POST /cards/{cardId}/cancel]
Cancel a Card permanently.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
    
    + Body
            
            
            {"userSuppliedId":"cancel-transaction-0f2ece01348747cd8e670adc9c1a8351"}
    
+ Response 200

    + Body
    
            {"card":{"cardId":"card-996570b110d045cfaaa971a6a0b55409","cardType":"GIFT_CARD","categories":[{"categoryId":"category-a5d681cddb4b4a6bbd5f44aa2e89b4a6","key":"giftbit_program","value":"program-37f8dc6bada64ace830affb1b7100b7f"},{"categoryId":"category-52621c4c2a604b36a340e527c3f22aa0","key":"giftbit_status","value":"CANCELLED"},{"categoryId":"category-1a167e8f87a24edaa930b37c6c8ff628","key":"giftbit_order","value":"2018-01-29"}],"contactId":null,"currency":"USD","dateCreated":"2018-01-29T20:26:19.427Z","userSuppliedId":"giftcard-a45etsraw"}}