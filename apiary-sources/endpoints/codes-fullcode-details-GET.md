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

            {"details":{"asAtDate":"2018-01-29T19:23:00.244Z","cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","codeLastFour":"VJNC","currency":"USD","valueStores":[{"expires":null,"programId":"program-090711761f094f4baa11666e3432c44c","restrictions":[],"startDate":null,"state":"ACTIVE","value":4650,"valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c","valueStoreType":"PRINCIPAL"}]}}