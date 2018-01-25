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
    
            {
              "user":{
                "username":"tim+apidocresfresh@giftbit.com",
                "mode":"TEST",
                "scopes":[
                ],
                "roles":[
                  "accountManager",
                  "contactManager"
                ],
                "effectiveScopes":[
                  {
                    "deny":false,
                    "scopePath":[
                      "lightrailV1",
                      "account"
                    ]
                  },
                  {
                    "deny":false,
                    "scopePath":[
                      "lightrailV1",
                      "payments"
                    ]
                  }
                ]
              }
            }
        
+ Response 401

        {
            "status": 401,
            "message": "Unauthorized",
            "code": "CREDENTIALS_INVALID"
        }