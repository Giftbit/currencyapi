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

            {"details":{"valueStores":[{"valueStoreType":"PRINCIPAL","value":5000,"state":"ACTIVE","expires":null,"startDate":null,"programId":"program-db6ae8091edd4bdd97522f575e753c9a","valueStoreId":"value-2923ec5903ab47c3b3d60f6813f107a1","restrictions":[]}],"currency":"USD","cardType":"GIFT_CARD","asAtDate":"2018-02-21T00:23:18.701Z","cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","codeLastFour":"NC3D"}}