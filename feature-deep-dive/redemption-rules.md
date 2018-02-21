# Redemption Rules Reference

[Lightrail](https://www.lightrail.com/) redemption rules are extra conditions placed on promotions. A redemption rule is evaluated with the transaction data to determine if the promotion can be used for that transaction. This unlocks powerful marketing promotions such as: "$10 good for purchases of $50 or more" or "$5 off your purchase when you buy a red hat."

This document is a reference for writing Lightrail Redemption Rules. To learn more about how to set up Redemption Rules in your system, check out the [Lightrail Redemption Rules Implementation Guide](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/redemption-rules.md).

## Evaluation

Redemption rules are set for a promotion and evaluated using variables from the submitted drawdown transaction. They are single statements that evaluate to a single value, not a full script to be executed. If the rule evaluates to `true` then the promotion can be applied to the transaction. If the rule evaluates to `false` then the promotion cannot be applied to the transaction.

Here's an example of a not particularly useful redemption rule:

<!-- Use javascript as the syntax language because it's close enough. -->
```javascript
1 == 2
```

This rule is made up of two numbers, and one operator: equals. This rule will evaluate to true when 1 equals 2, which is never.  This promotion can never be redeemed.

Here's a more realistic redemption rule:

```javascript
metadata.cartValue >= 5000
```

This rule evaluates to true if the value of the shopping cart is greater or equal to 5000.  In the currency `USD` this is the rule for "purchase of $50.00 or more."  In the currency `JPY` this would be "purchase of ¥5000 or more."

The full syntax of Redemption Rules is laid out in the guide on [Lightrail Rules](lightrail-rules.md).

The three variables of a transaction that can be used for a rule are:
- `value`: a positive number for the value of the Lightrail transaction (which may not be the value of the full cart in split-tender transactions)
- `currency`: a string for the ISO code of the currency of the transaction
- `metadata`: a map of arbitrary data that can be sent with the transaction

These variables are exactly as they are passed into the REST endpoint that created the transaction.  `metadata` is by far the most useful, and gets its own section. To learn about the basic metadata structure recommended by Lightrail, check out the [Lightrail Redemption Rules Implementation Guide](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/redemption-rules.md).

## Metadata

Best practice is for `metadata` to be consistent in structure and data types.  The more predictable the better.  The rest of this section describes what happens when `metadata` doesn't match what you expected.

When attempting to access variables that don't exist, `null` will be returned.  This is true even when attempting to access children of `null` (this is known as null propagation).  Functions will return a default value when their inputs can't be used.

```javascript
metadata.doesNotExist → null
metadata.does.not.exist → null
find(null) → null
findIndex(null) → -1
some(null) → false
every(null) → false
map(null) → []
```

## Example Rules

### Doughnut shop examples

In this `metadata` cart items are duplicated if more than one is bought.

```json
{
  "cart": {
    "total": 1960,
    "items": [
      {
        "id": "chocolate",
        "quantity": 1,
        "unit_price": 150,
        "tags": ["doughnut"]
      },
      {
        "id": "mapleglazed",
        "quantity": 1,
        "unit_price": 150,
        "tags": ["doughnut"]
      },
      {
        "id": "longjohn",
        "quantity": 1,
        "unit_price": 150,
        "tags": ["doughnut"]
      },
      {
        "id": "bearclaw",
        "quantity": 1,
        "unit_price": 250,
        "tags": ["doughnut"]
      }, 
      {
        "id": "dripcoffee",
        "unit_price": 315,
        "tags": ["coffee", "medium"]
      },
      {
        "id": "dripcoffee",
        "unit_price": 315,
        "tags": ["coffee", "medium"]
      },
      {
        "id": "dripcoffee",
        "unit_price": 315,
        "tags": ["coffee", "medium"]
      },
      {
        "id": "dripcoffee",
        "unit_price": 315,
        "tags": ["coffee", "medium"]
      }
    ]
  },
  "delivery": {
    "id":"store-pickup"
  }
}
```

#### Spend at least $10

```javascript
metadata.cart.total >= 1000
```

#### Buy any 5 items

```javascript
metadata.cart.items.size() >= 5
```

#### Canadian Special: discount maple glazed doughnuts

```javascript
metadata.cart.items.some(item => item.id == 'mapleglazed')
```

#### Buy a medium coffee and any doughnut

```javascript
metadata.cart.items.some(item => item.tags.some(tag=> tag=='coffee') && item.tags.some(tag=> tag=='medium')) && metadata.cart.items.some(item => item.tags.some(tag=> tag=='doughnut'))
```

#### Buy 4 coffees

```javascript
metadata.cart.items.filter(item => item.tags.some(tag => tag == 'coffee')).size() >= 4
```

#### Buy 4 coffees and pickup in store

```javascript
metadata.delivery.id=='store-pickup' && metadata.cart.items.filter(item => item.tags.some(tag => tag == 'coffee')).size() >= 4

```

#### Buy any 4 items of value > $1.00

```javascript
metadata.cart.items.filter(item => item.unit_price > 100).size() >= 4
```

### Concert tees examples

In this `metadata` cart items have a `quantity`.

```json
{
  "cart": {
    "total": 9593,
    "items": [
      {
        "id": "fce425c0",
        "quantity": 1,
        "unit_price": 3495,
        "tags": ["shirt", "medium", "ledzeppelin"]
      },
      {
        "id": "6cd226e1",
        "quantity": 1,
        "unit_price": 3299,
        "tags": ["shirt", "medium", "rollingstones"]
      },
      {
        "id": "ba991060",
        "quantity": 1,
        "unit_price": 1799,
        "tags": ["cd", "ledzeppelin"]
      },
      {
        "id": "bd086f23",
        "quantity": 5,
        "unit_price": 200,
        "tags": ["sticker", "thewho"]
      }
    ]
  }
}
```

#### Buy any 5 items

```javascript
metadata.cart.items.map(item => item.quantity).sum() >= 5
```

#### Buy any 2 t-shirts

```javascript
metadata.cart.items.filter(item => item.tags.some(tag => tag=='shirt')).map(item => item.quantity).sum() >= 2
```

#### Buy any 2 Led Zeppelin items worth at least $5

```javascript
metadata.cart.items.filter(item => item.unit_price >= 500 && item.tags.some(tag => tag=='ledzeppelin')).map(item => item.quantity).sum() >= 2
```

#### Buy any 4 stickers and a shirt

```javascript
metadata.cart.items.filter(item => item.tags.some(tag => tag=='sticker')).map(item => item.quantity).sum() >= 4 && metadata.cart.some(item => item.tags.some(tag => tag=='shirt'))
```

#### Buy $10 worth of stickers

```javascript
metadata.cart.items.filter(item => item.tags.some(tag => tag=='sticker')).map(item => item.quantity * item.unit_price).sum() >= 1000
```

#### Buy $20 worth of stickers or CDs

```javascript
metadata.cart.items.filter(item => item.tags.some(tag => tag=='sticker' || tag=='cd')).map(item => item.quantity * item.unit_price).sum() >= 2000
```

## Cheat sheet

For the developer short on time and long on experience:

- rules are evaluated using `value`, `currency`, `balance` and `metadata` of the transaction JSON
- rules are evaluated to a single value which is then coerced to a boolean
- if the final value is true the transaction can drawdown on the promotion
- syntax is modeled on EcmaScript
- types are dynamic with coercion following EcmaScript's rules
- there is no assignment, no looping, no recursion, no null pointer exception
- all operations on collections are done ala functional programming with lambdas
- all member access is null propagation (like the `?.` operator of C# or Groovy)
