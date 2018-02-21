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

            {"programs":[{"programId":"program-a5a204c52df641deaa0511efad7a9578","userSuppliedId":"promotion1","name":"Simple Promotion","active":false,"currency":"USD","dateCreated":"2018-02-21T00:23:18.000Z","programExpiresDate":null,"programStartDate":"2018-02-21T00:23:18.000Z","codeActivePeriodInDays":null,"codeValueMin":0,"codeValueMax":20000,"fixedCodeValues":null,"codeEngine":"SIMPLE_STORED_VALUE","codeConfig":"DEFAULT","valueStoreType":"ATTACHED","metadata":null,"timeZone":"PST","cardType":null},{"programId":"program-db6ae8091edd4bdd97522f575e753c9a","userSuppliedId":"giftCardProgram1","name":"USD Gift Cards","active":false,"currency":"USD","dateCreated":"2018-02-21T00:23:18.000Z","programExpiresDate":null,"programStartDate":"2018-02-21T00:23:18.000Z","codeActivePeriodInDays":null,"codeValueMin":0,"codeValueMax":20000,"fixedCodeValues":null,"codeEngine":"SIMPLE_STORED_VALUE","codeConfig":"DEFAULT","valueStoreType":"PRINCIPAL","metadata":null,"timeZone":"PST","cardType":null},{"programId":"program-account-USD-user-717a97087fcf4ff4a603e3d7afa08951-TEST","userSuppliedId":"program-account-USD-user-717a97087fcf4ff4a603e3d7afa08951-TEST","name":"USD Account Program","active":true,"currency":"USD","dateCreated":"2018-02-21T00:21:59.000Z","programExpiresDate":null,"programStartDate":"1970-01-01T00:00:00.000Z","codeActivePeriodInDays":null,"codeValueMin":0,"codeValueMax":null,"fixedCodeValues":null,"codeEngine":"SIMPLE_STORED_VALUE","codeConfig":"DEFAULT","valueStoreType":"PRINCIPAL","metadata":null,"timeZone":"PST","cardType":"ACCOUNT_CARD"},{"programId":"program-7949d6c364244912942b27e22116ff4e","userSuppliedId":"createDropInGiftCardProgram","name":"Demo Gift Card Program","active":true,"currency":"USD","dateCreated":"2018-02-21T00:21:50.000Z","programExpiresDate":null,"programStartDate":"2018-02-21T00:21:50.000Z","codeActivePeriodInDays":null,"codeValueMin":100,"codeValueMax":100000,"fixedCodeValues":null,"codeEngine":"SIMPLE_STORED_VALUE","codeConfig":"DEFAULT","valueStoreType":"PRINCIPAL","metadata":null,"timeZone":"PST","cardType":null}],"pagination":{"count":4,"limit":100,"maxLimit":1000,"offset":0,"totalCount":4}}