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
            
            
            {"userSuppliedId":"cancel-transaction-f74640609d38482dac0c77f05c1bc932"}
    
+ Response 200

    + Body
    
            {"card":{"cardId":"card-33182b81a2984116be6cbf655837b2e0","userSuppliedId":"giftcard-a45etsraw","contactId":null,"dateCreated":"2018-02-21T00:23:20.703Z","categories":[{"categoryId":"category-327fd33532a249e98952e40f8cf8b2c7","key":"giftbit_status","value":"CANCELLED"},{"categoryId":"category-33461095d3e2462a8305769daea084a2","key":"giftbit_program","value":"program-db6ae8091edd4bdd97522f575e753c9a"},{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"}],"cardType":"GIFT_CARD","currency":"USD"}}