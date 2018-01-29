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
    
            {
              "userSuppliedId":"alice-account-usd",
              "cardType":"ACCOUNT_CARD",
              "contactId":"contact-83deaef84b804fb38ef92aea81ed134a",
              "currency":"USD",
              "initialValue":500,
              "categories": {
                "city":"seattle"
              }
            }
        
+ Response 200
    + Attributes
        + card (Card)
        
    + Body

            {
              "card":{
                "cardId": "card-6bd0dee5efe047de9c2e3b11d11be97e",
                "userSuppliedId": "alice-account-usd",
                "contactId": "contact-83deaef84b804fb38ef92aea81ed134a",
                "dateCreated": "2018-01-15T22:52:18.749Z",
                "categories":[
                  {
                    "categoryId": "category-1b483e9c4e864a9fa9fe32b350ec85f9",
                    "key": "city",
                    "value": "seattle"
                  },
                  {
                    "categoryId": "category-e595f03510f14c389866c3f7ed12cfd7",
                    "key": "giftbit_order",
                    "value": "2018-01-15"
                  },
                  {
                    "categoryId": "category-fbbb512ccf564b198c15af55f83ba1ac",
                    "key": "giftbit_program",
                    "value": "program-account-USD-user-b3dc5abb7a754911a68bc0e96cb8f028"
                  }
                ],
                "cardType": "ACCOUNT_CARD",
                "currency": "USD"
              }
            }
            

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
    
            {RESPONSE_REPLACEMENT:createCard1.body}
        
+ Response 200
    + Attributes
        + card (Card)
        
    + Body

            {RESPONSE_REPLACEMENT:createCard1.response.body}

