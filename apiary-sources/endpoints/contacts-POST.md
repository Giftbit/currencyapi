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
            {"contact":{"contactId":"contact-ea97f5460b4849c8a3098e5b424b6b6f","dateCreated":"2018-01-25T22:12:21.000Z","email":"alice@example.com","firstName":null,"lastName":null,"userSuppliedId":"createContact1"}}
