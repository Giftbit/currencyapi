### List ValueStores [GET /cards/{cardId}/valueStores{?limit}{?offset}]
Retrieve a paginated list of a Card's ValueStores.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + limit (number, optional) - {{pagination.limit}}
    + offset (number, optional) - {{pagination.offset}}
        

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

+ Response 200
    + Attributes
        + valueStores (array[ValueStore])
        + pagination (Pagination)

    + Body

            {"valueStores":[{"cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","valueStoreId":"value-2923ec5903ab47c3b3d60f6813f107a1","valueStoreType":"PRINCIPAL","currency":"USD","dateCreated":"2018-02-21T00:23:18.348Z","programId":"program-db6ae8091edd4bdd97522f575e753c9a"}],"pagination":{"count":1,"limit":100,"maxLimit":1000,"offset":0,"totalCount":1}}

