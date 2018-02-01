<a name="contact-list-anchor"></a>

### List Contacts [GET /contacts{?email}{?firstName}{?lastName}{?userSuppliedId}{?limit}{?offset}]
Retrieve a paginated list of Contacts.

---
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
+ Parameters 
    + email (string, optional) - The contact's email.
    + firstName (string, optional) - The contact's first name.
    + lastName (string, optional) - The contact's last name.
    + userSuppliedId (string, required) - Retrieve the Contact created with a specific `userSuppliedId`. Note that since `userSuppliedId`s are unique, this guarantees that exactly one Contact will be returned if it exists.
    + limit (number, optional) - {{pagination.limit}}
    + offset (number, optional) - {{pagination.offset}}
    
+ Response 200
    + Attributes
       + contact (Contact)
       + pagination (Pagination)

    + Body
            {"contacts":[{"contactId":"contact-5382784e8f394532a5b0cff7fa2c6af8","dateCreated":"2018-01-29T20:26:17.000Z","email":"bill@example.com","firstName":"bill","lastName":"lumbergh","userSuppliedId":"createContact2"},{"contactId":"contact-067572a652874be6aec2664896965dae","dateCreated":"2018-01-29T20:26:16.000Z","email":"alice@example.com","firstName":null,"lastName":null,"userSuppliedId":"createContact1"}],"pagination":{"count":2,"limit":100,"maxLimit":1000,"offset":0,"totalCount":2}}

