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
    
            {REQUEST_REPLACEMENT:patchCard1.body}

+ Response 200

    + Attributes 
        + card (Card)

    + Body
        
            {REQUEST_REPLACEMENT:patchCard1.response.body}
