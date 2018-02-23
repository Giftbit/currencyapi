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
    
            {"contact":{"contactId":"contact-083fd74839c84a26974c93a6cbd98dca","userSuppliedId":"createContact2","email":"bill@example.com","firstName":"bill","lastName":"lumbergh","dateCreated":"2018-02-21T00:23:18.000Z"}}

