### Update Contact on Card [PATCH /cards/{cardId}]
Update the Contact associated with a Card, effectively transferring the Card to another Contact.

---
+ Parameters 
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes
        + contactId (string, required) - The unique identifier of the new Contact to whom the card must be assigned.
            
    + Body
    
            {"contactId":"contact-6e8a485db7cd45ce84d8b7915c2637a3"}

+ Response 200

    + Attributes 
        + card (Card)

    + Body
        
            {"card":{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","categories":[{"categoryId":"category-1b2460a709cb4ce48ef2f2d3db93a86c","key":"giftbit_order","value":"2018-01-25"},{"categoryId":"category-2a5b01c657854ed6a35a3ef070d8f35a","key":"giftbit_program","value":"program-090711761f094f4baa11666e3432c44c"}],"contactId":"contact-6e8a485db7cd45ce84d8b7915c2637a3","currency":"USD","dateCreated":"2018-01-25T01:08:28.546Z","userSuppliedId":"giftcard-3asfd34a"}}
