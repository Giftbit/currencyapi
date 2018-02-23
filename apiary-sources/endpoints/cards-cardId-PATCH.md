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
    
            {"contactId":"contact-083fd74839c84a26974c93a6cbd98dca"}

+ Response 200

    + Attributes 
        + card (Card)

    + Body
        
            {"card":{"cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","userSuppliedId":"giftcard-3asfd34a","contactId":"contact-083fd74839c84a26974c93a6cbd98dca","dateCreated":"2018-02-21T00:23:18.250Z","categories":[{"categoryId":"category-86ce6f1d0c2947cbbfdc10b6d8d5402a","key":"giftbit_order","value":"2018-02-21"},{"categoryId":"category-33461095d3e2462a8305769daea084a2","key":"giftbit_program","value":"program-db6ae8091edd4bdd97522f575e753c9a"}],"cardType":"GIFT_CARD","currency":"USD"}}
