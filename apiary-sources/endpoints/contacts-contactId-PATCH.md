### Update Contact [PATCH /contacts/{contactId}]
Updates a Contact.

---
+ Parameters
    + contactId (string, required) - The Lightrail Contact ID.
    
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes
        + email (string, optional) 
        + firstName (string, optional)
        + lastName (string, optional) 
            
    + Body
    
            {"firstName":"bill","lastName":"lumbergh"}
    
+ Response 200
    + Attributes 
        + contact (Contact)

    + Body
    
            {"contact":{"contactId":"contact-6e8a485db7cd45ce84d8b7915c2637a3","dateCreated":"2018-01-25T22:12:21.000Z","email":"bill@example.com","firstName":"bill","lastName":"lumbergh","userSuppliedId":"createContact2"}}

