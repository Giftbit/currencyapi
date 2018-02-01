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

            {"details":{"asAtDate":"2018-01-29T20:26:17.347Z","cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","cardType":"GIFT_CARD","codeLastFour":"2RZD","currency":"USD","valueStores":[{"expires":null,"programId":"program-37f8dc6bada64ace830affb1b7100b7f","restrictions":[],"startDate":null,"state":"ACTIVE","value":5000,"valueStoreId":"value-eefd98f834174294af3945d251f8441f","valueStoreType":"PRINCIPAL"}]}}