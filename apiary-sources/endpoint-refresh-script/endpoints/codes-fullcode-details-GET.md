### Get Card Details by Gift Code [GET /codes/{fullcode}/details{?asAtDate}{?pin}]
Retrieve a Gift Card's details based on its `fullcode`. 
The response includes the Value Stores (principal or attached promotions) as well as any restrictions on any of the Value Stores.

---
+ Parameters
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.
    + asAtDate (string, optional) - {{balance.asAtDate}}

+ Request (application/json)
    + Headers

            {{header.authorization}}

+ Response 200
    + Attributes (CardDetails)

    + Body

            {  
              "details":{  
                "valueStores":[  
                  {  
                    "valueStoreType":"PRINCIPAL",
                    "value":33301,
                    "state":"ACTIVE",
                    "expires":null,
                    "startDate":null,
                    "programId":"program-1dxxa9",
                    "valueStoreId":"value-2fxxf2",
                    "restrictions":[]
                  },
                  {  
                    "valueStoreType":"ATTACHED",
                    "value":500,
                    "state":"ACTIVE",
                    "expires":null,
                    "startDate":null,
                    "programId":"program-c7xxe6",
                    "valueStoreId":"value-79xxee",
                    "restrictions":[  
                      "If your cart total is at least $100."
                    ]
                  }
                ],
                "currency":"USD",
                "cardType":"GIFT_CARD",
                "asAtDate":"2017-09-14T18:09:09.520Z",
                "cardId":"card-6dxx89"
              }
            }