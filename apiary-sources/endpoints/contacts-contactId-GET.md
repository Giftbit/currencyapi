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
    
            {"contact":{"contactId":"contact-ea97f5460b4849c8a3098e5b424b6b6f","dateCreated":"2018-01-25T22:12:21.000Z","email":"alice@example.com","firstName":null,"lastName":null,"userSuppliedId":"createContact1"}}

