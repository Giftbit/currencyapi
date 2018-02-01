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

            {"pagination":{"count":2,"limit":100,"maxLimit":1000,"offset":0,"totalCount":2},"programs":[{"active":true,"cardType":null,"codeActivePeriodInDays":null,"codeConfig":"DEFAULT","codeEngine":"SIMPLE_STORED_VALUE","codeValueMax":20000,"codeValueMin":0,"currency":"USD","dateCreated":"2018-01-29T20:26:16.000Z","fixedCodeValues":null,"metadata":null,"name":"Simple Promotion","programExpiresDate":null,"programId":"program-17aae714584b4355a2232fdd6a719398","programStartDate":"2018-01-29T20:26:16.000Z","timeZone":"PST","userSuppliedId":"promotion1","valueStoreType":"ATTACHED"},{"active":true,"cardType":null,"codeActivePeriodInDays":null,"codeConfig":"DEFAULT","codeEngine":"SIMPLE_STORED_VALUE","codeValueMax":20000,"codeValueMin":0,"currency":"USD","dateCreated":"2018-01-29T20:26:16.000Z","fixedCodeValues":null,"metadata":null,"name":"USD Gift Cards","programExpiresDate":null,"programId":"program-37f8dc6bada64ace830affb1b7100b7f","programStartDate":"2018-01-29T20:26:16.000Z","timeZone":"PST","userSuppliedId":"giftCardProgram1","valueStoreType":"PRINCIPAL"}]}