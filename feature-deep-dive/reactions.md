# Reactions

[Lightrail](https://www.lightrail.com) Reactions provides an endpoint for your own outbound webhook to send messages and configure actions to happen in Lightrail based on those messages.

## Input

Sending messages or events to Reactions is easy.  Just POST to https://www.lightrail.com/v1/react/input with a JSON body and your API key in the Authorization header.  There are no restrictions on the format of the body.  Messages can trigger a reaction, multiple reactions, or none at all.

### Idemotency

The world is messy.  Network connections can fail and cause messages to be processed multiple times.  To ensure that messages are not processed multiple times put a unique identifier on your message.  Reactions will look for the following keys on the JSON object in order: `id`, `messageId`, `message_id`, `eventId`, `event_id`, `sg_message_id`.  If any of these are found then that message is uniquely identified and even if it is sent multiple times it will only be processed once.

### Example

In this example we're sending a simple message.  This is a referral event with both a referrer and referee email address.  We'll use this message later in a reaction that gives each an account credit.

```bash
curl -i \
    -H "Accept: application/json" \
    -H "Authorization: Bearer <your API key>" \
    -X POST \
    -d '{"id":"5a4bf861-ba1a-4d2e-a66d-89c1b2f13e1e","type":"referral","referrer":{"email":"helpfulfriend@example.com"},"referee":{"email":"newcustomer@example.com"}}' \
    https://api.lightrail.com/v1/react/input
```

### Libraries for sending messages

You can send messages to Reactions using your own REST library or use an analytics library that will make your life easier.  Segment has built libraries for sending messages in a number of environments including [Node](https://github.com/segmentio/analytics-node), [Java](https://github.com/segmentio/analytics-java), [Ruby](https://github.com/segmentio/analytics-ruby) and [PHP](https://github.com/segmentio/analytics-php).

## Reaction structure

Now that we're sending events we can react to those events.  A Reaction defines how that happens.  It's made up of a `userSuppliedId`, a `name`, a `when` and an array of `what`s.

`when` defines when the Reaction will apply.  It's a [Lightrail Rule](lightrail-rules.md) string that is evaluated with the event.  If the Reaction When evaluates to `true` then the `what` will happen.  If the Reaction When evaluates to `false` then the `what` won't happen.  An example Reaction When is `"{{event.type == 'referral'}}"`.

`what` defines what the Reaction does.  It's an object that has a `type` and `params`.  Each Reaction What type has different expected params.  The values of the params can be raw values or expressions that are evaluated as [Lightrail Rules](lightrail-rules.md).  Lightrail Rules are surrounded by double braces (`{{}}`).  For example `"user@example.com"` is a raw value that does not need to be evaluated; `"{{event.referrer.email}}"` is a value that is replaced with `referrer.email` of the incomming event; `"{{event.user}}@{{event.domain}}"` is a string put together with `user` from the event, a literal @ sign, and `domain` from the event.  The examples that follow should make this clearer.

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
|type|`string` the type of the Reaction What|
|params|`object` the parameters for the execution of the Reaction What|

### An example

Here again is the message we're going to react to:

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

A fuller definition of `manageContact` can be found below.

## Managing reactions

## Logs
