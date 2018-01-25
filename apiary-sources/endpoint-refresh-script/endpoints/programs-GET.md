<a name="get-programs-anchor"></a>

### List Programs [GET /programs{?limit}{?offset}]
Retrieves a paginated list of all Lightrail Programs belonging to the user. 

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Parameters
    + limit (number, optional) - {{pagination.limit}}
    + offset (number, optional) - {{pagination.offset}}
        
+ Response 200

    + Body

            {
                "programs":[
                    {
                      "programId": "program-ccxx45",
                      "userSuppliedId": "74xxdf",
                      "name": "Short-Lived Promo",
                      "active": true,
                      "currency": "USD",
                      "dateCreated": "2017-07-04T21:12:09.000Z",
                      "programExpiresDate": null,
                      "programStartDate": "2017-07-04T21:11:33.000Z",
                      "codeActivePeriodInDays": 7,
                      "codeValueMin": 1000,
                      "codeValueMax": 2000,
                      "fixedCodeValues": null,
                      "codeEngine": "SIMPLE_STORED_VALUE",
                      "codeConfig": "DEFAULT",
                      "valueStoreType": "ATTACHED",
                      "metadata": null,
                      "timeZone": "PST",
                      "cardType": null
                    },
                    {
                      "programId": "program-1dxxa9",
                      "userSuppliedId": "3cxxff",
                      "name": "USD Program",
                      "active": true,
                      "currency": "USD",
                      "dateCreated": "2017-07-01T14:34:52.000Z",
                      "programExpiresDate": null,
                      "programStartDate": "2017-07-01T14:33:43.000Z",
                      "codeActivePeriodInDays": null,
                      "codeValueMin": 500,
                      "codeValueMax": 10000,
                      "fixedCodeValues": null,
                      "codeEngine": "SIMPLE_STORED_VALUE",
                      "codeConfig": "DEFAULT",
                      "valueStoreType": "PRINCIPAL",
                      "metadata": null,
                      "timeZone": "PST",
                      "cardType": null
                    }
                ],
                "pagination":{
                    "count": 2,
                    "limit": 100,
                    "maxLimit": 1000,
                    "offset": 0,
                    "totalCount": 2
                }
            }