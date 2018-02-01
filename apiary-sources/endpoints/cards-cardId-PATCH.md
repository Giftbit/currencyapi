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
    
            {"contactId":"contact-5382784e8f394532a5b0cff7fa2c6af8"}

+ Response 200

    + Attributes 
        + card (Card)

    + Body
        
            {"card":{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","categories":[{"categoryId":"category-1a167e8f87a24edaa930b37c6c8ff628","key":"giftbit_order","value":"2018-01-29"},{"categoryId":"category-a5d681cddb4b4a6bbd5f44aa2e89b4a6","key":"giftbit_program","value":"program-37f8dc6bada64ace830affb1b7100b7f"}],"contactId":"contact-5382784e8f394532a5b0cff7fa2c6af8","currency":"USD","dateCreated":"2018-01-29T20:26:16.841Z","userSuppliedId":"giftcard-3asfd34a"}}
