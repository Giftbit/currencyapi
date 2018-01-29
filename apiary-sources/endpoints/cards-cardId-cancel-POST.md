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
            
            
            {"userSuppliedId":"cancel-transaction-8b80db5edcc24bd2afe3272b70e08d24"}
    
+ Response 200

    + Body
    
            {"card":{"cardId":"card-7ae4aec62e2845d1b9eb8c7d77e7936e","cardType":"GIFT_CARD","categories":[{"categoryId":"category-bc005e9ec7674d7989a1ca9cbf08c4ab","key":"giftbit_program","value":"program-1649285334224c05bf7a8c2644d54949"},{"categoryId":"category-d7edb68537314bffac56ce3615b2d7d7","key":"giftbit_order","value":"2018-01-29"},{"categoryId":"category-ac01a564749d4fd3b95e501ce5559f4c","key":"giftbit_status","value":"CANCELLED"}],"contactId":null,"currency":"USD","dateCreated":"2018-01-29T18:33:05.236Z","userSuppliedId":"giftcard-a45etsraw"}}