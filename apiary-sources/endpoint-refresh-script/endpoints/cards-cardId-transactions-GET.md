### List Card Transactions Based on Card ID [GET /cards/{cardId}/transactions{?userSuppliedId}{?transactionType}{?limit}{?offset}]
Retrieve a paginated list of a Card's Transactions based on the Card ID.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + userSuppliedId (string, optional) - return the transaction with the specific `userSuppliedId`. Note that since `userSuppliedId`s are unique, this guarantees a unique transaction in the response if it exists.
    + transactionType (string, optional) - return only transactions of a specific type.
    + limit (number, optional) - {{pagination.limit}}
    + offset (number, optional) - {{pagination.offset}}
    
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + transactions (array[Transaction])
        + pagination (Pagination)

    + Body
            
            {REQUEST_REPLACEMENT:getCard1Transactions.response.body}