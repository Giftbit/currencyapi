### Get Balance by Gift Code (Deprecated) [GET /codes/{fullcode}/card/balance{?asAtDate}{?pin}]
Retrieve a Card's balance using the Gift Card's `fullcode`, also referred to as Gift Code.

---
+ Parameters
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.
    + asAtDate (string, optional) - {{balance.asAtDate}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes (Balance)

    + Body

            {
                "balance":{
                    "cardId": "card-6dxxx89",
                    "principal":{
                        "currentValue": 150,
                        "state": "ACTIVE",
                        "expires": null,
                        "startDate": null,
                        "programId": "program-dexx72",
                        "valueStoreId": "value-18xx79"
                    },
                    "attached":[],
                    "currency": "USD",
                    "cardType":"GIFT_CARD",
                    "balanceDate": "2017-06-05T17:11:36.999Z"
                }
            }