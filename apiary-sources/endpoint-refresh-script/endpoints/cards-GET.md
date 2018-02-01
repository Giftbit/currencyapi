### List Cards [GET /cards/{?contactId}{?cardType}{?currency}{?userSuppliedId}{?limit}{?offset}]
Retrieve a paginated list of Cards. The search can be narrowed down based on various parameters.

---
+ Request (application/json)
    + Headers
    
             {{header.authorization}}
   
+ Parameter
{#    + categoryKey (string, optional) - A key of a Category. 
    + categoryValue (string, optional) - A value of a Category.  #}
    + contactId (string, optional) - Retrieve only the Cards belonging to a specific Contact.
    + cardType (string, optional) - {{card.cardType}}
    + currency (string, optional) - {{currency}}
    + userSuppliedId (string, optional) - Retrieve the Card created with a specific `userSuppliedId`. Note that since `userSuppliedId`s are unique, this guarantees that exactly one Card will be returned if it exists. 
    + limit (number, optional) - {{pagination.limit}}
    + offset (number, optional) - {{pagination.offset}}

+ Response 200
    + Attributes 
        + cards (array[Card])
        + pagination (Pagination)

    + Body
    
            {REQUEST_REPLACEMENT:listCards1.response.body}
