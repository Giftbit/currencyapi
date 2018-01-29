### Get Card Details by Card ID [GET /cards/{cardId}/details{?asAtDate}]
Retrieve a Card's details based on its `cardId`. 
The response includes the Value Stores (principal or attached promotions) as well as any restrictions on any of the Value Stores.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + asAtDate (string, optional) - {{balance.asAtDate}}
+ Request (application/json)
    + Headers

            {{header.authorization}}

+ Response 200
    + Attributes (CardDetails)

    + Body

            {RESPONSE_REPLACEMENT:getCard1Details.response.body}