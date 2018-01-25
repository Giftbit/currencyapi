### Add ValueStore [POST /cards/{cardId}/valueStores]
Adds a ValueStore from a Promotion Program to a Card. 

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        + currency (string, required) - {{currency}}
        + programId (string, required) - The unique id of the Promotion Program. Note the Program's `ValueStoreType` must be of type `ATTACHED`.
        + expires (string, optional) - Defaults to lifespan set by program.
        + startDate (string, optional) - The date for which the ValueStore will become usable.
        + initialValue (number, optional) - The initial value of the ValueStore.
        
    + Body 
    
            {
                "userSuppliedId":"promo1",
                "currency": "XXX",
                "programId":"program-d0xx04",
                "initialValue":150
            }
    
+ Response 200
    + Attributes
        + valueStore (ValueStore)

    + Body

            {
                "valueStore":{
                    "cardId": "card-99xx65",
                    "valueStoreId": "value-80xx82",
                    "valueStoreType": "ATTACHED",
                    "currency": "XXX",
                    "dateCreated": "2017-06-05T16:14:58.314Z",
                    "programId": "program-d0xx04",
                    "expires": "2017-06-13T06:59:59.000Z"
                }   
            }

