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
    
            {"userSuppliedId":"contact-126abb61e0fa4e3c86fd6da50b741465-account","cardType":"ACCOUNT_CARD","contactId":"contact-126abb61e0fa4e3c86fd6da50b741465","currency":"USD","initialValue":500}
        
+ Response 200
    + Attributes
        + card (Card)
        
    + Body

            {"card":{"cardId":"card-4085e168a3924df7a6097010674a77ff","userSuppliedId":"contact-126abb61e0fa4e3c86fd6da50b741465-account","contactId":"contact-126abb61e0fa4e3c86fd6da50b741465","dateCreated":"2018-02-21T00:23:21.360Z","categories":[{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"},{"categoryId":"category-f0ce164180674c20aeacfe494cd8c4d2","key":"giftbit_program","value":"program-account-USD-user-717a97087fcf4ff4a603e3d7afa08951-TEST"}],"cardType":"ACCOUNT_CARD","currency":"USD"}}
            

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
    
            {"userSuppliedId":"giftcard-3asfd34a","programId":"program-db6ae8091edd4bdd97522f575e753c9a","initialValue":5000,"cardType":"GIFT_CARD"}
        
+ Response 200
    + Attributes
        + card (Card)
        
    + Body

            {"card":{"cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","userSuppliedId":"giftcard-3asfd34a","contactId":null,"dateCreated":"2018-02-21T00:23:18.250Z","categories":[{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"},{"categoryId":"category-33461095d3e2462a8305769daea084a2","key":"giftbit_program","value":"program-db6ae8091edd4bdd97522f575e753c9a"}],"cardType":"GIFT_CARD","currency":"USD"}}

