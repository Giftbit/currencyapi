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
    
            {"cards":[{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","categories":[{"categoryId":"category-2a5b01c657854ed6a35a3ef070d8f35a","key":"giftbit_program","value":"program-090711761f094f4baa11666e3432c44c"},{"categoryId":"category-1b2460a709cb4ce48ef2f2d3db93a86c","key":"giftbit_order","value":"2018-01-25"}],"contactId":"contact-6e8a485db7cd45ce84d8b7915c2637a3","currency":"USD","dateCreated":"2018-01-25T01:08:28.546Z","userSuppliedId":"giftcard-3asfd34a"}],"pagination":{"count":1,"limit":100,"maxLimit":1000,"offset":0,"totalCount":1}}