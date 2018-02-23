### Ping [GET]
+ Request (application/json)
    + Headers

            {{header.authorization}}

+ Response 200
    + Attributes
        + username (string) - The email address associated with the account.
        + mode (string) - String indicating whether the credentials provided are for TEST or LIVE mode.
        + scopes - A list of scopes associated with the credentials.
        + roles - A list of roles associated with the credentials.
        + effectiveScopes - A list of the effective scopes as a result of combining roles and scopes.
        
    + Body
    
            {"user":{"username":"user@example.com","mode":"TEST","scopes":[],"roles":["accountManager","contactManager","customerServiceManager","customerServiceRepresentative","pointOfSale","programManager","promoter","reporter","securityManager","teamAdmin","webPortal"],"effectiveScopes":["lightrailV1:account","lightrailV1:payments","lightrailV1:sharedSecret","lightrailV1:stripeConnect","lightrailV1:token","lightrailV1:userImage:create","lightrailV1:contact","lightrailV1:card","lightrailV1:valueStore","lightrailV1:transaction","lightrailV1:valueStore","lightrailV1:transaction","lightrailV1:card:deliver","lightrailV1:code:balance","lightrailV1:transaction:create:drawdown","lightrailV1:transaction:create:capture","lightrailV1:transaction:create:void","lightrailV1:program","lightrailV1:programKey","lightrailV1:valueStore","lightrailV1:stats","lightrailV1:transaction:create:cancel","lightrailV1:transaction:create:freeze","lightrailV1:transaction:create:unfreeze","lightrailV1:transaction:create:activate","lightrailV1:team","lightrailV1:portal","lightrailV1:token:list","lightrailV1:token:create","lightrailV1:team:show"]}}
        
+ Response 401

        {
            "status": 401,
            "message": "Unauthorized",
            "code": "CREDENTIALS_INVALID"
        }