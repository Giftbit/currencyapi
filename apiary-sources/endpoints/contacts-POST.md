### Create Contact [POST /contacts]
Creates a new Contact. 

---
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes 
        + userSuppliedId (string, required) - {{userSuppliedId}}
        + email (string, optional) - The contact's email.
        + firstName (string, optional) - The contact's first name.
        + lastName (string, optional) - The contact's last name.
        
            
    + Body
            {"userSuppliedId":"createContact1","email":"alice@example.com"}

    
+ Response 200
    + Attributes 
        + contact (Contact)

    + Body
            {"contact":{"contactId":"contact-126abb61e0fa4e3c86fd6da50b741465","userSuppliedId":"createContact1","email":"alice@example.com","firstName":null,"lastName":null,"dateCreated":"2018-02-21T00:23:17.871Z"}}
