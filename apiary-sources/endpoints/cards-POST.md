### Create Account Card [POST /cards]
Create a Card of type `ACCOUNT_CARD` which is associated with an existing Contact.

---
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes 
        + userSuppliedId (string, required) - {{userSuppliedId}}
        + cardType (string, required) - ACCOUNT_CARD
        + currency (string, required) - {{currency}}
        + initialValue (number, optional) - If not provided, will default to 0.
        + categories (object, optional) - An object of key-value pairs. For example: `'categories': {'city':'Seattle', 'country':'USA'}`.
        + contactId (string, required) - Note, the Contact must be created before the request to create the card.
        + expires (string, optional) - Defaults to never expires.
        + startDate (string, optional) - The date for which the associated ValueStore will become usable.
        + inactive (boolean, optional) - If `true` the Card's `PRINCIPAL` ValueStore will have an `INACTIVE` balance.
        + metadata (Metadata, optional) - {{metadata}}
        
    + Body
    
            {"cardType":"ACCOUNT_CARD","contactId":"contact-067572a652874be6aec2664896965dae","currency":"USD","initialValue":500,"userSuppliedId":"contact-067572a652874be6aec2664896965dae-account"}
        
+ Response 200
    + Attributes
        + card (Card)
        
    + Body

            {"card":{"cardId":"card-d99142eac3bd4abfbe657db000b614f6","cardType":"ACCOUNT_CARD","categories":[{"categoryId":"category-1a167e8f87a24edaa930b37c6c8ff628","key":"giftbit_order","value":"2018-01-29"},{"categoryId":"category-f398fa67056f4116b23c599185800e33","key":"giftbit_program","value":"program-account-USD-user-4646197086af471fa9265fd3d1546ffa"}],"contactId":"contact-067572a652874be6aec2664896965dae","currency":"USD","dateCreated":"2018-01-29T20:26:20.092Z","userSuppliedId":"contact-067572a652874be6aec2664896965dae-account"}}
            

### Create Gift Card [POST /cards]
Create a Card of type `GIFT_CARD`.

---
+ Request (application/json)

    + Headers

            {{header.authorization}}
            
    + Attributes 
        + userSuppliedId (string, required) - {{userSuppliedId}}
        + cardType (string, required) - GIFT_CARD
        + initialValue (number, optional) - If not provided, will default to 0.
        + currency (string) - {{currency}}
        + programId (string, required) - The Lightrail Program ID.
        + categories (object, optional) - An object of key-value pairs. For example: `'categories': {'city':'Seattle', 'country':'USA'}`.
        + contactId (string, optional) - Note, the Contact must be created before the request to create the card.
        + expires (string, optional) - Defaults to never expires.
        + startDate (string, optional) - The date for which the ValueStore will become usable.
        + inactive (boolean, optional) - If `true` the Card's `PRINCIPAL` ValueStore will have an `INACTIVE` balance.
        + metadata (Metadata, optional) - {{metadata}}

    + Body
    
            {"cardType":"GIFT_CARD","initialValue":5000,"programId":"program-37f8dc6bada64ace830affb1b7100b7f","userSuppliedId":"giftcard-3asfd34a"}
        
+ Response 200
    + Attributes
        + card (Card)
        
    + Body

            {"card":{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","categories":[{"categoryId":"category-1a167e8f87a24edaa930b37c6c8ff628","key":"giftbit_order","value":"2018-01-29"},{"categoryId":"category-a5d681cddb4b4a6bbd5f44aa2e89b4a6","key":"giftbit_program","value":"program-37f8dc6bada64ace830affb1b7100b7f"}],"contactId":null,"currency":"USD","dateCreated":"2018-01-29T20:26:16.841Z","userSuppliedId":"giftcard-3asfd34a"}}

