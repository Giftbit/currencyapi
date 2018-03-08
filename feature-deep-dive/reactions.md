# Reactions

[Lightrail](https://www.lightrail.com) Reactions takes events from your system and triggers actions in Lightrail.  It's flexible.  The events can be any structure that works for you and the actions can use and manipulate that data.  It decouples the system that generates the events from the promotion logic.

Currently all Reactions management is done through REST calls.  We are exploring the best way to expose this feature in the web UI.

## Input

Sending events to Reactions is easy.  Just POST to https://www.lightrail.com/v1/react/input with a JSON body and your API key in the Authorization header.  There are no restrictions on the format of the body.  Events can trigger one reaction, multiple reactions, or none at all.

### Idempotency

The world is messy.  Network connections can fail and cause events to be processed multiple times.  To ensure that events are not processed multiple times put a unique identifier on your event.  Reactions will look for the following keys on the JSON object in order: `id`, `messageId`, `message_id`, `eventId`, `event_id`, `sg_message_id`.  If any of these are found then that event is uniquely identified and even if it is sent multiple times it will only be processed once.

### Example

In this example we're sending a simple event.  This is a referral event with both a referrer and referee email address.  We'll use this event later in a reaction that gives each an account credit.

```bash
curl -i \
    -H "Accept: application/json" \
    -H "Authorization: Bearer <your API key>" \
    -X POST \
    -d '{"id":"5a4bf861-ba1a-4d2e-a66d-89c1b2f13e1e","type":"referral","referrer":{"email":"helpfulfriend@example.com"},"referee":{"email":"newcustomer@example.com"}}' \
    https://api.lightrail.com/v1/react/input
```

### Libraries for sending events

You can send events to Reactions using your own REST library or use an analytics library that will make your life easier.  Segment has built libraries for sending events in a number of environments including [Node](https://github.com/segmentio/analytics-node), [Java](https://github.com/segmentio/analytics-java), [Ruby](https://github.com/segmentio/analytics-ruby) and [PHP](https://github.com/segmentio/analytics-php).

## Evaluation

Now that we're sending events we can react to those events.  A Reaction defines how that happens.  It's made up of a `userSuppliedId`, a `name`, a `when` and an array of `what`s.  It's evaluated in a context that includes the `event` (the full body of the input POST) object and the `shared` object (containing values you [manage manually](#managing-the-shared-object)).

`when` defines when the Reaction will apply.  It's a [Lightrail Rule](lightrail-rules.md) string that is evaluated with the event.  If the Reaction When evaluates to `true` then the `what` will happen.  If the Reaction When evaluates to `false` then the `what` won't happen.  An example Reaction When is `"{{event.type == 'referral'}}"`.

`what` defines what the Reaction does.  It's an object that has a `type` and `params`.  The `type` selects a predefined [Reaction What](#reaction-whats) that is available in the Lightrail system.  Each `type` of Reaction What is similar to a function that expects certain parameters in a `params` object (for details on the available `type`s, see [below](#reaction-whats)).  

The values inside params can be raw values or expressions that are evaluated as [Lightrail Rules](lightrail-rules.md).  Lightrail Rules are surrounded by double braces (`{{}}`).  For example `"user@example.com"` is a raw value that does not need to be evaluated; `"{{event.referrer.email}}"` is a value that is replaced with `referrer.email` of the incoming event; `"{{event.user}}@{{event.domain}}"` is a string put together with `user` from the event, a literal @ sign, and `domain` from the event.  The examples that follow should make this clearer.

### Formal definition

**Reaction**

| member | value |
|--------|-------|
|userSuppliedId|`string` the ID of the Reaction|
|version|`number` 1 is the only supported value at this time|
|enabled|`boolean` (optional) set to false to disable the Reaction|
|name|`string` the friendly name of the Reaction|
|when|`string` a Lightrail Rule that determines when the Reaction applies|
|what|`object` a Reaction What that defines what happens when the Reaction applies|

**Reaction What**

| member | value |
|--------|-------|
|type|`string` the type of the [Reaction What](#reaction-whats)|
|params|`object` the parameters for the execution of the Reaction What|

### Referral example

Here again is the event we're going to react to:

```json
{
    "id": "5a4bf861-ba1a-4d2e-a66d-89c1b2f13e1e",
    "type": "referral",
    "referrer": {
        "email": "helpfulfriend@example.com"
    },
    "referee": {
        "email": "newcustomer@example.com"
    }
}
```

Here is the Reaction that will credit $5 to both the referrer and the referee:

```json
{
    "userSuppliedId": "referralbonus",
    "version": 1,
    "enabled": true,
    "name": "USD $5 for Referrer and Referee",
    "when": "{{event.type == 'referral'}}",
    "what": [
        {
            "type": "manageContact",
            "params": {
                "account": {
                    "currency": "USD",
                    "addValue": 500
                },
                "contact": {
                    "email": "{{event.referrer.email}}"
                }
            }
        },
        {
            "type": "manageContact",
            "params": {
                "account": {
                    "currency": "USD",
                    "addValue": 500
                },
                "contact": {
                    "email": "{{event.referee.email}}"
                }
            }
        }
    ]
}
```

Note that this Reaction has 2 Reaction Whats: one to credit the referrer and one to credit the referee.  The type of the Reaction Whats is `manageContact` and that the params are particular to that Reaction What type.  In the params we're specifying what contact we're managing and that the email address is evaluated using a value from the event.  We're also specifying that we want to add value to the USD account of that contact and those values are raw; they never change.

A fuller definition of `manageContact` can be found [below](#managecontact).

## Managing Reactions

The REST API for managing your Reactions is simple.  All calls require your API key in the Authorization header.

GET https://api.lightrail.com/v1/react/reactions to list your Reactions.

POST https://api.lightrail.com/v1/react/reactions with a Reaction in the body to create a Reaction.

GET https://api.lightrail.com/v1/react/reactions/{reactionId} to get an individual Reaction.

PUT https://api.lightrail.com/v1/react/reactions/{reactionId} to create or update a Reaction (reactionId in the path must match `userSuppliedId` in the Reaction object).

DELETE https://api.lightrail.com/v1/react/reactions/{reactionId} to delete a Reaction.

## Managing the Shared Object

Reactions are evaluated in an environment that includes an object called `shared` which of course is shared between all Reactions.  This object is encrypted at rest on the server and is the natural place to store API keys and other special values.

GET https://api.lightrail.com/v1/react/shared to get the shared object.

PUT https://api.lightrail.com/v1/react/shared to set the value of the shared object.

DELETE https://api.lightrail.com/v1/react/shared clear the shared object.

## Reaction Whats

These are the actions that will be triggered when a qualifying event is received. 

There are currently two `types` of Reaction Whats available: 
* `http` for making an HTTP request
* `manageContact` for creating or updating a contact and optionally adding value to an attached account

Each `type` of What requires particular `params` to be set ahead of time or passed in from the [event](#input) that triggers it. 

### Make an HTTP request

**type**

`http`

**params**

| parameter | description |
|-----------|-------------|
|method|`string` The method of the request.|
|url|`string` The URL to make the request to.|
|query|`object` (optional) A map of additional query parameters to send with the request.  Existing query parameters in `url` will be respected.|
|body|`any` (optional) The body of the request.  By default if the `body` is a string the `Content-Type` will be set to `application/x-www-form-urlencoded`, otherwise the `Content-Type` will be set to `application/json` and body data serialized as JSON.  This behavior can be changed by setting `Content-Type` in `headers`.|
|headers|`object` (optional) A map of additional headers to send with the request.|

**example**

In this example we POST JSON data to a test REST server.

```json
{
    "userSuppliedId": "testHttpPost",
    "version": 1,
    "name": "testHttpPost",
    "when": "{{event.type == 'httpTest'}}",
    "what": [
        {
            "type": "http",
            "params": {
                "method": "POST",
                "url": "https://postman-echo.com/post",
                "body": {
                    "key": "This is a member of the JSON object.",
                    "some_data": "{{event.data}}"
                },
                "headers": {
                    "X-Custom": "This is a custom extra HTTP header.",
                    "Authorization": "Bearer {{shared.apikey}}"
                }
            }
        }
    ]
}
```

### Manage a Contact

Create or update a contact and optionally add value to an account.

**type**

`manageContact`

**params**

| parameter | description |
|-----------|-------------|
|contact    |`object` The contact to manage.|
|contact.contactId|`string` (optional) The Lightrail contactId of the contact to manage.  One of contactId, shopperId or email must be defined.|
|contact.shopperId|`string` (optional) The shopperId of the contact to manage.  One of contactId, shopperId or email must be defined.|
|contact.email|`string` (optional) The email of the contact to manage.  One of contactId, shopperId or email must be defined.|
|contact.firstName|`string` (optional) Update the first name of the contact if set.|
|contact.lastName|`string` (optional) Update the last name of the contact if set.|
|account    |`object` (optional) Account operations.|
|account.currency|`string` (optional) The currency of the account to manage.|
|account.addValue|`number` (optional) Value to add to the account.|
|account.attachProgram|`object` (optional) A program to attach to the account.|
|account.attachProgram.programId|`string` The programId of the program to attach.|
|account.attachProgram.value|`number` (optional) The value to add when attaching the program.|

**example**

In this example a signup event triggers creation of a contact and USD account card.

```json
{
    "userSuppliedId": "usdonsignup",
    "version": 1,
    "name": "Create USD account on signup",
    "when": "{{event.type == 'signup'}}",
    "what": [
        {
            "type": "manageContact",
            "params": {
                "account": {
                    "currency": "USD"
                },
                "contact": {
                    "firstName": "{{event.firstName}}",
                    "lastName": "{{event.lastName}}",
                    "email": "{{event.email}}"
                }
            }
        }
    ]
}
```

## Logs

We're sending events, and we're reacting to them, now let's see how they're doing.  We can get the first 10 event logs from the last 10 minutes with the following API call:

GET https://api.lightrail.com/v1/reactlogs

This endpoint supports the following query parameters:

| parameter | description |
|-----------|-------------|
|fromDate|ISO date offset to start getting logs from.|
|limit|For pagination. The maximum number of results to return at once.|
|offset|For pagination. The offset of the first results in the total results.|

### Example referral logs

Following on our referral Reaction from earlier here's a log where we processed our example event:

```json
{
    "logs": [
        {
            "date": "2018-02-22T22:52:50.531Z",
            "event": {
                "id": "5a4bf861-ba1a-4d2e-a66d-1",
                "type": "referral",
                "referrer": {
                    "email": "helpfulfriend@example.com"
                },
                "referee": {
                    "email": "newcustomer@example.com"
                }
            },
            "success": true,
            "reactionLogs": [
                {
                    "reaction": {
                        "userSuppliedId": "referralbonus",
                        "version": 1,
                        "enabled": true,
                        "name": "USD $5 for Referrer and Referee",
                        "when": "{{event.type == 'referral'}}",
                        "what": [
                            {
                                "type": "manageContact",
                                "params": {
                                    "account": {
                                        "currency": "USD",
                                        "addValue": 500
                                    },
                                    "contact": {
                                        "email": "{{event.referrer.email}}"
                                    }
                                }
                            },
                            {
                                "type": "manageContact",
                                "params": {
                                    "account": {
                                        "currency": "USD",
                                        "addValue": 500
                                    },
                                    "contact": {
                                        "email": "{{event.referee.email}}"
                                    }
                                }
                            }
                        ]
                    },
                    "whatIndex": 0,
                    "success": true,
                    "logs": [
                        "Searching for Contact by email 'helpfulfriend@example.com'.",
                        "Found Contact with contactId 'contact-a84841e6ca6f49dc80e6178cc2b26caa'.",
                        "Searching for existing Account Card for currency 'USD'.",
                        "Found Card with cardId 'card-95febc322b1e4955940cca27dfed6c9b'.",
                        "Creating transaction in USD for 500."
                    ]
                },
                {
                    "reaction": {
                        "userSuppliedId": "referralbonus",
                        "version": 1,
                        "enabled": true,
                        "name": "USD $5 for Referrer and Referee",
                        "when": "{{event.type == 'referral'}}",
                        "what": [
                            {
                                "type": "manageContact",
                                "params": {
                                    "account": {
                                        "currency": "USD",
                                        "addValue": 500
                                    },
                                    "contact": {
                                        "email": "{{event.referrer.email}}"
                                    }
                                }
                            },
                            {
                                "type": "manageContact",
                                "params": {
                                    "account": {
                                        "currency": "USD",
                                        "addValue": 500
                                    },
                                    "contact": {
                                        "email": "{{event.referee.email}}"
                                    }
                                }
                            }
                        ]
                    },
                    "whatIndex": 1,
                    "success": true,
                    "logs": [
                        "Searching for Contact by email 'newcustomer@example.com'.",
                        "Found Contact with contactId 'contact-7619845529ca43a5ae6ec74a72ccb3f7'.",
                        "Searching for existing Account Card for currency 'USD'.",
                        "Found Card with cardId 'card-d0e72d1961f340ddaf9c8a63861210e9'.",
                        "Creating transaction in USD for 500."
                    ]
                }
            ]
        }
    ],
    "fromDate": "2018-02-22T22:44:00.189Z",
    "pagination": {
        "count": 1,
        "limit": 10,
        "maxLimit": 100,
        "offset": 0
    }
}
```

Note that we have two entries in `reactionLogs`: one for each Reaction What.
