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
            {"contacts":[{"contactId":"contact-083fd74839c84a26974c93a6cbd98dca","userSuppliedId":"createContact2","email":"bill@example.com","firstName":"bill","lastName":"lumbergh","dateCreated":"2018-02-21T00:23:18.000Z"},{"contactId":"contact-126abb61e0fa4e3c86fd6da50b741465","userSuppliedId":"createContact1","email":"alice@example.com","firstName":null,"lastName":null,"dateCreated":"2018-02-21T00:23:18.000Z"},{"contactId":"contact-2e571e7064ab4d9f8b5039fd791baea1","userSuppliedId":"dropinPreviewContact","email":"tim+23ar4esdf32awsx@giftbit.com","firstName":null,"lastName":null,"dateCreated":"2018-02-21T00:21:59.000Z"}],"pagination":{"count":3,"limit":100,"maxLimit":1000,"offset":0,"totalCount":3}}

