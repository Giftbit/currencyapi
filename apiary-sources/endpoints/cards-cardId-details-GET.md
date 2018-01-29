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

            {"details":{"asAtDate":"2018-01-29T19:23:00.369Z","cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","cardType":"GIFT_CARD","codeLastFour":"VJNC","currency":"USD","valueStores":[{"expires":null,"programId":"program-090711761f094f4baa11666e3432c44c","restrictions":[],"startDate":null,"state":"ACTIVE","value":4650,"valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c","valueStoreType":"PRINCIPAL"}]}}