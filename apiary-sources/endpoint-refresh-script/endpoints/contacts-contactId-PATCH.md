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
    
            {
                "firstName": "Alice",
                "lastName": "Liddel"
            }
    
+ Response 200
    + Attributes 
        + contact (Contact)

    + Body
    
            {
                "contact":{
                    "contactId": "contact-83deaef84b804fb38ef92aea81ed134a",
                    "userSuppliedId": "314159265359",
                    "email": "alice@example.com",
                    "firstName": "Alice",
                    "lastName": "Liddel",
                    "dateCreated": "2018-01-15T21:51:24.000Z"
                }
            }

