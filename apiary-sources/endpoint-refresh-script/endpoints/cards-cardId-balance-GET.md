### Get Balance by Card ID (Deprecated) [GET /cards/{cardId}/balance{?asAtDate}]
Retrieve a Card's balance based on its `cardId`.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + asAtDate (string, optional) - {{balance.asAtDate}}
+ Request (application/json)
    + Headers

            {{header.authorization}}

+ Response 200
    + Attributes (Balance)

    + Body

            {
                "balance":{
                    "principal":{
                        "currentValue": 150,
                        "state": "ACTIVE",
                        "expires": null,
                        "startDate": null,
                        "programId": "program-dexx72",
                        "valueStoreId": "value-18xx79"
                    },
                    "attached":[
                    ],
                    "currency": "USD",
                    "cardType":"GIFT_CARD",
                    "balanceDate": "2017-06-05T17:11:36.999Z"
                }
            }