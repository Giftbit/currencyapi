### Show ValueStore [GET /cards/{cardId}/valueStores/{valueStoreId}]
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + valueStoreId (string, required) - The Lightrail ValueStore ID.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    
+ Response 200
    + valueStore (ValueStore)

    + Body

            {
                "valueStore":{
                    "cardId": "card-99xx65",
                    "valueStoreId": "value-0fxx04",
                    "valueStoreType": "PRINCIPAL",
                    "currency": "XXX",
                    "dateCreated": "2017-06-05T15:39:25.392Z",
                    "programId": "program-dexx72"
                }
            }

