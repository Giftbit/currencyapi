# Accounts

Accounts are basic infrastructure for identity-based value and customer engagement. Track customer spending habits to find out who's buying what and when, then apply [gift cards](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/drop-in-gift-cards.md) and [promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/promotions.md) to directly engage individual customers. 

## Getting Started

[Sign up](https://www.lightrail.com/app/#/register) for a Lightrail account. 

Add one of our [client libraries](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/client-libraries.md) to your project in the language of your choice. If you are using a language for which we don't yet have a client library, you can build directly into our REST API -- feel free to [contact us](mailto:hello@lightrail.com) for support. 

If you are using Stripe to process payments, add one of our [Stripe integration libraries](https://github.com/Giftbit/Lightrail-API-Docs/blob/accounts_and_promotions/docs/client-libraries.md#stripe). If you are using a different payment processor, please [contact us](mailto:hello@lightrail.com) for details on building account credit into your checkout. 

**Note** if you're using our [Drop-In Gift Card](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/drop-in-gift-cards.md) solution, you're probably already set up with Accounts. Go ahead and carry on with [Promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/promotions.md). 

## Step 1: Creating Accounts

Accounts are attached to Contacts, which are connected with a customer record in your system by a `shopperId`. The `shopperId` is a unique identifier from your system, such as their email address or customer ID. 

### Creating Contacts

All client libraries provide methods for creating contacts. 

The only required parameter is the `shopperId`. 

Optionally, you can also provide an `email`, `firstName`, and `lastName`. Here is a sample request:

```javascript
const newContactParams = {
  userSuppliedId: 'customer-9f50629d',
  email: 'test@test.ca',
  firstName: 'Test',
  lastName: 'McTest'
};
Lightrail.createContact(newContactParams).then(
  // called asynchronously
);
```

```php
$contactParams = array(
    'shopperId' => 'customer-9f50629d',
    'email' => 'test@test.ca',
    'firstName' => 'Test',
    'lastName' => 'McTest',
);
$contact = \Lightrail\LightrailContact::create($params);
```

```java
// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 
// For contact creation, `email` is a required parameter instead of `shopperId`

String email = "test@test.ca";
String firstName = "Test";
String lastName = "McTest";
LightrailContact contact = LightrailContact.create(email, firstName, lastName);
```

```ruby
new_contact_params = {
                       shopperId: 'customer-9f50629d',
                       email: 'test@test.ca',
                       firstName: 'Test',
                       lastName: 'McTest'
                     }
contact = Lightrail::Contact.create(new_contact_params)
```

Note the `shopperId` is stored in the Lightrail system as `userSuppliedId`. 

### Creating Accounts

Once you have created a Contact, you can create an Account for them based on their `shopperId`. 

Required parameters: 
- The `shopperId` of the Contact the Account belongs to
- The Account's `currency` (for a points-only account, use `XXX`; otherwise use any standard ISO 4217 currency code)
- A `userSuppliedId` to uniquely identify the Account and guarantee idempotence (since each Contact can have only one Account Card per currency, you can add the currency as a suffix to the `shopperId` you provided for the Contact)

You may optionally include an `initialValue` for the account. If provided, this must be a positive integer in the smallest currency unit (for example, `500` is $5.00 USD).

```javascript
const newAccountParams = {
  userSuppliedId: 'cust-a95a09-USD',
  cardType: 'ACCOUNT_CARD',
  currency: 'USD',
  initialValue: 500,
};
Lightrail.accounts.createAccount({shopperId: 'customer-9f50629d'}, newAccountParams).then(
  // called asynchronously
);
```

```php
$accountParams = array(
    'shopperId'      => 'cust-a95a09',
    'userSuppliedId' => 'cust-a95a09-USD',
    'currency'       => 'USD',
    'initialValue'   => 500
);

$account = \Lightrail\LightrailAccount::createAccountCard($accountParams);
```

```java
// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 
// Accounts are currently managed through the Contact they are attached to. To create an Account, you simply add a supported currency (and amount) to an existing Contact
// Note that for the following retrieval method, a Contact’s `shopperId` is referred to as its `userSuppliedId`

LightrailContact contact = LightrailContact.retrieveByUserSuppliedId('contact-8t3503');
contact.addCurrency("USD", 500);
```

```ruby
new_account_params = {
                       shopper_id: 'cust-a95a09',
                       currency: 'USD',
                       user_supplied_id: 'cust-a95a09-USD',
                     }
new_account = Lightrail::Account.create(new_account_params)
```

The return value will include both the `userSuppliedId` and a server-generated `cardId` which you can persist and use to retrieve the Account Card later.

## Step 2: Transacting against Accounts

### Funding and Charging

You can transact against a customer's Account using either their `shopperId`. 

Required parameters:
- The customer's `shopperId`
- The `value` of the transaction: a positive `value` will add funds to the account, while a negative `value` will post a charge to the account. This amount must be in the smallest currency unit (e.g., `500` for $5.00 USD)
- The `currency` that the transaction is in (note that Lightrail does not handle currency conversion and the contact must have an account in the corresponding currency)
- A `userSuppliedId`, which is a unique transaction identifier to ensure idempotence (for example, the order ID from your e-commerce system)

Optional parameters: 
- Arbitrary `metadata` (important if you are using complex [Promotions](#TODO add link))
- A boolean indicating if the transaction should be `pending` (default is `false`)


```javascript
const transactionParams = {
  value: -3500,
  currency: 'USD',
  userSuppliedId: 'order-a90h09a509gaj00-a4'
};
Lightrail.accounts.createTransaction({shopperId: 'customer-9f50629d'}, transactionParams).then(
  // called asynchronously
);
```

```php
$transactionParams = array(
    'shopperId'      => 'cust-a95a09',
    'currency'       => 'USD',
    'value'          => -3500,
    'userSuppliedId' => 'order-a90h09a509gaj00-a4'
);

\Lightrail\LightrailAccount::createTransaction($transactionParams);
```

```java
// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 

String shopperId = contact.getShopperId();
LightrailTransaction tx = LightrailTransaction.Create.byShopperId(shopperId, -3500, "USD");
```

```ruby
# NOTE Account transactions in the Ruby client are currently inconsistent with other client libraries, it will be updated soon.
# Transactions use either the `.charge` or `.fund` methods

charge = Lightrail::Account.charge({
    shopper_id: 'cust-a95a09',
    currency: 'USD',
    value: -3500,
    user_supplied_id: 'order-a90h09a509gaj00-a4'
  })

fund = Lightrail::Account.fund({
    shopper_id: 'cust-a95a09'
    currency: 'USD',
    value: 350,
    user_supplied_id: 'dividend-fe2d'
  })
```

The return value includes the full details of the transaction, including both the `userSuppliedId` you provided and a server-generated `transactionId`. 

### Transaction Simulation and Balance Checking

Because Accounts can contain conditional value such as [Promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/promotions.md), doing a "balance check" means one of two things: checking the maximum value that an Account _could_ have available if all conditions are met on all attached promotions, or checking how much an Account has available for a particular transaction given its specific circumstances. 

To display the maximum value of a customer's account, use our drop-in [Balance Widget](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/drop-in-gift-cards.md#displaying-account-balance). 

To display details to a customer for confirmation before completing a transaction, use a transaction simulation.

#### Transaction Simulation

Transaction simulations can tell you whether or not an account has enough funds before attempting to post the transaction. 

In the case of insufficient funds, this method can also tell you the maximum value for which the transaction _would be_ successful. For example, if you simulate a $35 drawdown, the method can tell you that it _would be_ successful if it were only for $20. 

The parameters for this method call are almost identical to those for posting a transaction. To get the maximum value, add `nsf: false` to your transaction parameters (this tells the system not to throw an NSF error and instead return a best-effort transaction breakdown): 

```javascript
const simulationParams = {
  value: -6960,
  currency: 'USD',
  userSuppliedId: 'order-s3xx30',
  nsf: false
};
Lightrail.accounts.simulateTransaction({shopperId: 'customer-9f50629d'}, simulationParams).then(
  // called asynchronously
);
```

```php
$simulationParams = array(
    'shopperId'      => 'customer-9f50629d',
    'currency'       => 'USD',
    'value'          => -6960,
    'userSuppliedId' => 'order-s3xx30',
    'nsf'            => 'false'
);

\Lightrail\LightrailAccount::simulateTransaction($simulationParams);
```

```java
// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 

LightrailTransaction simulatedTx = LightrailTransaction.Simulate.byShopperId('customer-9f50629d', -6960, 'USD');
```

```ruby
simulated_charge = Lightrail::Account.simulate_charge({
    shopper_id: 'customer-9f50629d',
    currency: 'USD',
    value: -6960,
    user_supplied_id: 'order-s3xx30',
    nsf: false
  })
```

The response will be similar to the response for posting a transaction with a `value` that indicates the maximum value that the account can provide for this transaction. Since this is just a simulation and NOT an actual transaction, it will not have a `transactionId`. 

Once you're ready to charge the Account, simply pass the `value` returned from the simulation into the [charge](TODO ADD LINK TO ABOVE SECTION) method.

## Next Steps

Once you're set up with Accounts, it's easy to add our [Drop-In Gift Card solution](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/drop-in-gift-cards.md). You can also take customer engagement to the next level with targeted [Promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/promotions.md). 

## Support

Contact us any time at hello@lightrail.com —- we are here to help.

You can also check out our [sample app](https://github.com/Giftbit/stripe-integration-sample-webapp) for a working example of Accounts infrastructure supporting our Drop-in Gift Card solution.
