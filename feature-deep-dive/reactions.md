# Reactions

[Lightrail](https://www.lightrail.com) Reactions provides an endpoint for your own outbound webhook to send messages and configure actions to happen in Lightrail based on those messages.

## Input

Sending messages or events to Reactions is easy.  Just POST to https://www.lightrail.com/v1/react/input with a JSON body and your API key in the Authorization header.  There are no restrictions on the format of the body.

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

## Managing reactions

## Logs
