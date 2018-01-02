## Implementation Details

### Idempotency 
The Lightrail API is idempotent. HTTP `POST` requests against the API require a `userSuppliedId` to be provided in the request body as an idempotency key. 
This means that making the same request twice with the same `userSuppliedId` will only result in creating a single object in the API. Note, both requests will have an identical response.
  
If the request body has changed but is using the same `userSuppliedId` you'll receive a `HTTP 409 - Conflict Error`. 

Lightrail's 'list' endpoints accept `userSuppliedId` as a query parameter. This provides a way of linking objects from your system to Lightrail.  

### ShopperIds
If you've been using the Lightrail [client libraries](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/client-libraries.md#client-libraries) you'll have seen references to the `shopperId`. The `shopperId` is simply a customer ID from your system. Using the client-libraries allows you to make requests against a customer's account in Lightrail directly based off their customer ID in your system. 

### Currencies 
The Lightrail API uses the three-character currency codes from the ISO-4217 standard, e.g. USD, CDN, and AUD. The special value XXX is defined by this standard for representing any non-currency values such as points.

All currency values are represented by the smallest currency unit, e.g. cents for USD or CAD. For example, to create a gift card for $1.00 USD, you would set the initialValue=100.

Lightrail does not do any form of currency exchange. If you create a gift card or account, you can only attach promotions in that same currency.

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
