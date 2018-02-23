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
    
            {"contact":{"contactId":"contact-126abb61e0fa4e3c86fd6da50b741465","userSuppliedId":"createContact1","email":"alice@example.com","firstName":null,"lastName":null,"dateCreated":"2018-02-21T00:23:17.871Z"}}

