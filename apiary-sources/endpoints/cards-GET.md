### List Cards [GET /cards/{?contactId}{?cardType}{?currency}{?userSuppliedId}{?limit}{?offset}]
Retrieve a paginated list of Cards. The search can be narrowed down based on various parameters.

---
+ Request (application/json)
    + Headers
    
             {{header.authorization}}
   
+ Parameter
{#    + categoryKey (string, optional) - A key of a Category. 
    + categoryValue (string, optional) - A value of a Category.  #}
    + contactId (string, optional) - Retrieve only the Cards belonging to a specific Contact.
    + cardType (string, optional) - {{card.cardType}}
    + currency (string, optional) - {{currency}}
    + userSuppliedId (string, optional) - Retrieve the Card created with a specific `userSuppliedId`. Note that since `userSuppliedId`s are unique, this guarantees that exactly one Card will be returned if it exists. 
    + limit (number, optional) - {{pagination.limit}}
    + offset (number, optional) - {{pagination.offset}}

+ Response 200
    + Attributes 
        + cards (array[Card])
        + pagination (Pagination)

    + Body
    
            {"cards":[{"cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","userSuppliedId":"giftcard-3asfd34a","contactId":null,"dateCreated":"2018-02-21T00:23:18.250Z","categories":[{"categoryId":"category-33461095d3e2462a8305769daea084a2","key":"giftbit_program","value":"program-db6ae8091edd4bdd97522f575e753c9a"},{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"}],"cardType":"GIFT_CARD","currency":"USD"},{"cardId":"card-0f28eec8b7b6467fadb8f30e80f0c4c1","userSuppliedId":"733cafb0-6078abc6-65cfb216","contactId":"contact-2e571e7064ab4d9f8b5039fd791baea1","dateCreated":"2018-02-21T00:21:59.006Z","categories":[{"categoryId":"category-f0ce164180674c20aeacfe494cd8c4d2","key":"giftbit_program","value":"program-account-USD-user-717a97087fcf4ff4a603e3d7afa08951-TEST"},{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"}],"cardType":"ACCOUNT_CARD","currency":"USD"}],"pagination":{"count":2,"limit":100,"maxLimit":1000,"offset":0,"totalCount":2}}
