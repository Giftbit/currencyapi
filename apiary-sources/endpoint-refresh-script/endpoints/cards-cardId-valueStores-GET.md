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

            {RESPONSE_REPLACEMENT:getCard1ValueStores.response.body}

