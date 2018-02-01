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

            {"pagination":{"count":1,"limit":100,"maxLimit":1000,"offset":0,"totalCount":1},"valueStores":[{"cardId":"card-eacdcbc40f0f4570b88a7b83959ecf70","currency":"USD","dateCreated":"2018-01-29T20:26:16.935Z","programId":"program-37f8dc6bada64ace830affb1b7100b7f","valueStoreId":"value-eefd98f834174294af3945d251f8441f","valueStoreType":"PRINCIPAL"}]}

