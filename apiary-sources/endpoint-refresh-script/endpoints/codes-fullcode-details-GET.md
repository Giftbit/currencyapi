### Get Card Details by Gift Code [GET /codes/{fullcode}/details{?asAtDate}{?pin}]
Retrieve a Gift Card's details based on its `fullcode`. 
The response includes the Value Stores (principal or attached promotions) as well as any restrictions on any of the Value Stores.

---
+ Parameters
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.
    + asAtDate (string, optional) - {{balance.asAtDate}}

+ Request (application/json)
    + Headers

            {{header.authorization}}

+ Response 200
    + Attributes (CardDetails)

    + Body

            {REQUEST_REPLACEMENT:getCard1FullcodeDetails.response.body}