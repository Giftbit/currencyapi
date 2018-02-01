### Show Transaction Based on Card ID [GET /cards/{cardId}/transactions/{transactionId}]
Retrieve a particular Transaction by its ID based on the `cardId`.

---

+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + transactionId (string, required) - {{transaction.transactionId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {REQUEST_REPLACEMENT:createTransaction1Card1.response.body}

