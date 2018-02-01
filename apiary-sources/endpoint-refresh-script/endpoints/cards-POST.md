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
    
            {REQUEST_REPLACEMENT:createAccountCard.body}
        
+ Response 200
    + Attributes
        + card (Card)
        
    + Body

            {REQUEST_REPLACEMENT:createAccountCard.response.body}
            

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
    
            {REQUEST_REPLACEMENT:createCard1.body}
        
+ Response 200
    + Attributes
        + card (Card)
        
    + Body

            {REQUEST_REPLACEMENT:createCard1.response.body}

