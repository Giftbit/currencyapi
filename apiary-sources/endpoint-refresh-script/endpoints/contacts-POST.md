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
            {RESPONSE_REPLACEMENT:createContact1.body}

    
+ Response 200
    + Attributes 
        + contact (Contact)

    + Body
            {RESPONSE_REPLACEMENT:createContact1.response.body}
