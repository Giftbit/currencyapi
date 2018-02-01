### Show Contact [GET /contacts/{contactId}]
+ Parameters
    + contactId (string, required) - The Lightrail Contact ID.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    
+ Response 200
    + Attributes
        + contact (Contact)

    + Body
    
            {"contact":{"contactId":"contact-067572a652874be6aec2664896965dae","dateCreated":"2018-01-29T20:26:16.438Z","email":"alice@example.com","firstName":null,"lastName":null,"userSuppliedId":"createContact1"}}

