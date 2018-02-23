### Retrieve Account Cards or Gift Cards for Contact [GET /cards{?contactId}{?cardType}{?currency}]
Retrieve a paginated list of a Contact's Cards.

---
+ Parameters 
    + contactId (string, required) - The Lightrail Contact ID.
    + cardType (string, required) - {{card.cardType}}
    + currency (string, optional) - {{currency}} Only needed if the Contact has Account Cards in multiple currencies.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + cards (array[Card])
        + pagination (Pagination)

    + Body
    
            {"cards":[{"cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","userSuppliedId":"giftcard-3asfd34a","contactId":"contact-083fd74839c84a26974c93a6cbd98dca","dateCreated":"2018-02-21T00:23:18.250Z","categories":[{"categoryId":"category-33461095d3e2462a8305769daea084a2","key":"giftbit_program","value":"program-db6ae8091edd4bdd97522f575e753c9a"},{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"}],"cardType":"GIFT_CARD","currency":"USD"}],"pagination":{"count":1,"limit":100,"maxLimit":1000,"offset":0,"totalCount":1}}