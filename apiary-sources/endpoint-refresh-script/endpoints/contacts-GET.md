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
            {RESPONSE_REPLACEMENT:list-contacts-1.response.body}

