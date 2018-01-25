{#
### Get Balance by Gift Code (Deprecated) [GET /codes/{fullcode}/balance{?asAtDate}{?pin}]
This endpoint is deprecated and should no longer be used. 

---
+ Parameters
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.
    + asAtDate (string, optional) - {{balance.asAtDate}}

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Body

            {
                balance":{
                    "currentValue": 150,
                    "fundedValue": 0,
                    "drawdownValue": 0,
                    "cancellationValue": 0,
                    "initialValue": 150,
                    "frozenValue": 0,
                    "inactiveValue": 0,
                    "pendingValue": 0,
                    "currency": "USD"
                }
            }
#}