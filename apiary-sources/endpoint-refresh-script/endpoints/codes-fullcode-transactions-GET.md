### List Card Transactions Based on Gift Code [GET /codes/{fullcode}/transactions{?pin}{?userSuppliedId}{?transactionType}{?limit}{?offset}]
Retrieve a paginated list of a Card's Transactions based on the Gift Card's `fullcode`.

---
+ Parameters
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.
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

            {REQUEST_REPLACEMENT:getCard1TransactionsByFullcode.response.body}
