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

            {"pagination":{"count":1,"limit":100,"maxLimit":1000,"offset":0,"totalCount":1},"valueStores":[{"cardId":"card-402aec6b8e8d41a49e453c3ed4ac11ee","currency":"USD","dateCreated":"2018-01-25T01:08:28.710Z","programId":"program-090711761f094f4baa11666e3432c44c","valueStoreId":"value-6e80d7ca755d42fc846cf17aa06ae41c","valueStoreType":"PRINCIPAL"}]}

