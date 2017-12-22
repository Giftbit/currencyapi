## Implementation Details

### Idempotency 
Our API is idempotent. HTTP `POST` requests against the API require a `userSuppliedId` provided in the request body. 
This means that making the same request twice with the same `userSuppliedId` will only result in creating a single object in the API. Note, both requests will have an identical response.
  
If the request body has changed but is using the same `userSuppliedId` you'll receive a `HTTP 409 - Conflict Error`. 

Lightrail's 'list' endpoints accept `userSuppliedId` as a query parameter. This provides a way with linking objects from your system to Lightrail.  

### Currencies 
Lightrail API uses the three-character currency codes from the ISO-4217 standard, e.g. `USD`,` CDN`, and `AUD`. The special value `XXX` is defined by this standard for representing any non-currency values such as points.

All currency values are represented by the smallest currency unit, e.g. cents for USD or CAD. For example, to create a Card for USD1.00, you would set the `initialValue=100`.

Lightrail does not do any form of currency exchange and only enforces the consistency of currencies among connected Lightrail objects. For example, if you create a Card with the principal Value Store in USD, you can only attach USD promotions to this Card and transact against it in USD. Note that Lightrail Transaction endpoints do not presume a default currency and you must provide the currency explicitly when posting a Transaction.

### Dates
Lightrail uses the `yyyy-MM-dd'T'HH:mm:ss.SSSZ` format from the ISO-8601 standard for all dates. This allows you to control things such as value expiry dates in fine granularity to the time zone of your choosing. You can see various examples of date values in the endpoint documentation. 

### Handling Error Responses

Clients should always check the HTTP status code of the response and act accordingly if the response is not a 200.

The response JSON object in case of an error will be in the following format:
- `status`: (number) - echoing the HTTP response code.    
- `message`: (string, optional) - a descriptive error message if one is available. 
- `messageCode`: (string, optional) - a code for the error that can be consumed programmatically.

Note that the error `message` is intended to be shown to an end user; it may change from time to time to improve clarity and might be translated into other languages. For programmatic use, you should use the `messageCode` which reliably corresponds to the particular error case and is not subject to change without notice.

#### Example error responses:
| Status | Description         | Message                                  |
| :----- | ------------------- | ---------------------------------------- |
| `400`  | bad request         | `Missing required parameter 'userSuppliedId'` |
| `401`  | unauthorized        | `Unauthorized.`                          |
| `409`  | idempotency failure | `A different transaction with the same userSuppliedId already exists.` |
| `429`  | rate-limit failure  | `Too many requests.`                     |
