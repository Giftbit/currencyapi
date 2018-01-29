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

            {RESPONSE_REPLACEMENT:listPrograms1.response.body}