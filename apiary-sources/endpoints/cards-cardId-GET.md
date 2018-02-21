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
        
            {"card":{"cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","userSuppliedId":"giftcard-3asfd34a","contactId":null,"dateCreated":"2018-02-21T00:23:18.250Z","categories":[{"categoryId":"category-33461095d3e2462a8305769daea084a2","key":"giftbit_program","value":"program-db6ae8091edd4bdd97522f575e753c9a"},{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"}],"cardType":"GIFT_CARD","currency":"USD"}}
