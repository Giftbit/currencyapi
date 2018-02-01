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
            {"email":"alice@example.com","userSuppliedId":"createContact1"}

    
+ Response 200
    + Attributes 
        + contact (Contact)

    + Body
            {"contact":{"contactId":"contact-067572a652874be6aec2664896965dae","dateCreated":"2018-01-29T20:26:16.438Z","email":"alice@example.com","firstName":null,"lastName":null,"userSuppliedId":"createContact1"}}
