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
    
            {"contact":{"contactId":"contact-5382784e8f394532a5b0cff7fa2c6af8","dateCreated":"2018-01-29T20:26:17.000Z","email":"bill@example.com","firstName":"bill","lastName":"lumbergh","userSuppliedId":"createContact2"}}

