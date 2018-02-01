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
    
            {"cards":[{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","categories":[{"categoryId":"category-a5d681cddb4b4a6bbd5f44aa2e89b4a6","key":"giftbit_program","value":"program-37f8dc6bada64ace830affb1b7100b7f"},{"categoryId":"category-1a167e8f87a24edaa930b37c6c8ff628","key":"giftbit_order","value":"2018-01-29"}],"contactId":null,"currency":"USD","dateCreated":"2018-01-29T20:26:16.841Z","userSuppliedId":"giftcard-3asfd34a"}],"pagination":{"count":1,"limit":100,"maxLimit":1000,"offset":0,"totalCount":1}}
