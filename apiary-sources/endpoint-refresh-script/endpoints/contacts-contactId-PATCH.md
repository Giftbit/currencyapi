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
    
            {REQUEST_REPLACEMENT:patchContact2.body}
    
+ Response 200
    + Attributes 
        + contact (Contact)

    + Body
    
            {REQUEST_REPLACEMENT:patchContact2.response.body}

