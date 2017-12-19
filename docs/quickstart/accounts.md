# Accounts

Accounts are basic infrastructure for identity-based customer engagement. Track customer spending habits to find out who's buying what and when, then apply  [gift cards](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/drop-in-gift-cards.md) and [promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/promotions.md) to directly engage individual customers. 

## Getting Started

[Sign up](https://www.lightrail.com/app/#/register) for a Lightrail account. 

Add one of our [client libraries](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/client-libraries.md) to your project in the language of your choice. If you are using a language for which we don't yet have a client library, you can build directly into our REST API -- feel free to [contact us](mailto:hello@lightrail.com) for support. 

If you are using Stripe to process payments, add one of our [Stripe integration libraries](https://github.com/Giftbit/Lightrail-API-Docs/blob/accounts_and_promotions/docs/client-libraries.md#stripe). If you are using a different payment processor, please [contact us](mailto:hello@lightrail.com) for details on building account credit into your checkout. 

**Note** if you're using our [Drop-In Gift Card](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/drop-in-gift-cards.md) solution, you're probably already set up with Accounts. Go ahead and carry on with [Promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/promotions.md). 

## Step 1: Creating Accounts

### Creating Contacts

Lightrail Accounts are attached to Contacts, which correspond to customer records in your system. 

To create a new Contact, you need to provide a `shopperId`:  this is a unique identifier which ensures idempotence and also links the Lightrail Contact to the corresponding customer record in your system (for example, their customer ID or email address). 

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
    'shopperId' => 'cust-a95a09',
    'email' => 'test@test.com'
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

Response types will vary by language. Note however that the `shopperId` is stored in the Lightrail system as `userSuppliedId`, so the value you supplied as the `shopperId` will show up under this key in the response. There will also be a server-generated `contactId` which you can persist and use to retrieve the Contact later.

### Retrieving a Contact

You can retrieve a Contact by either its `shopperId` or its `contactId`.

```javascript
Lightrail.contacts.getByShopperId('customer-9f50629d').then(
  // called asynchronously
);

Lightrail.contacts.getContactById('contact-271a').then(
  // called asynchronously
);
```

```php
$contact1 = \Lightrail\LightrailContact::retrieveByContactId('contact-0s459jy6h56');

$contact2 = \Lightrail\LightrailContact::retrieveByShopperId('cust-a95a09');
```

```java
// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 
// For retrieving Contacts, `shopperId` is referred to as `userSuppliedId`:

LightrailContact contact1 = LightrailContact.retrieve(contactId);

LightrailContact contact2 = LightrailContact.retrieveByUserSuppliedId(contactId);
```

```ruby
contact1 = Lightrail::Contact.retrieve_by_shopper_id('contact-8t3503')

contact2 = Lightrail::Contact.retrieve_by_contact_id('customer-9f50629d')
```

### Creating Accounts

Once you have created a Contact, you can create an Account for them based on either their `shopperId` or their `contactId`. 

Required parameters: 
- The `shopperId` or `contactId` of the Contact the Account belongs to
- The Account's `currency` (for a points-only account, use `XXX`; otherwise use any standard ISO 4217 currency code)
- A `userSuppliedId` to uniquely identify the Account and guarantee idempotence (since each Contact can have only one Account Card per currency, you can add the currency as a suffix to the `shopperId` you provided for the Contact)

You may optionally include an `initialValue` for the account. If provided, this must be a positive integer in the smallest currency unit (for example, `500` is 5 USD).

```javascript
const newAccountParams = {
  userSuppliedId: 'customer-9f50629d-USD',
  cardType: 'ACCOUNT_CARD',
  currency: 'USD',
  initialValue: 500,
};
Lightrail.accounts.createAccount({shopperId: 'customer-9f50629d'}, newAccountParams).then(
  // called asynchronously
);

Lightrail.accounts.createAccount({contactId: 'contact-8t3503'}, newAccountParams).then(
  // called asynchronously
);
```

```php
$contact = \Lightrail\LightrailContact::retrieveByShopperId('cust-a95a09');

$accountParams = array(
    'shopperId'      => 'cust-a95a09',  // alternatively use the Lightrail generated identifier: 'contactId' => 'contact-0s459jy6h56'
    'userSuppliedId' => 'cust-a95a09-usd-account',
    'currency'       => 'USD',
    'initialValue'   => 500
);

$account = \Lightrail\LightrailAccount::createAccountCard();
```

```java
// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 
// For Account creation, you simply add a supported currency (and amount) to an existing Contact

LightrailContact contact = LightrailContact.create(email, firstName, lastName);
contact.addCurrency("USD", 500)
       .addCurrency("CAD", 500);
```

```ruby
new_account_params = {
                       shopper_id: 'customer-9f50629d',   # alternatively use the Lightrail generated identifier: contactId: 'contact-0s459jy6h56'
                       currency: 'USD',
                       user_supplied_id: 'this-is-a-new-account',
                     }
new_account = Lightrail::Account.create(new_account_params)
```

The return value will include both the `userSuppliedId` and a server-generated `cardId` which you can persist and use to retrieve the Account Card later.

## Step 2: Transacting against Accounts

### Funding and Charging

You can transact against a customer's Account using either their `shopperId` or the `contactId`. 

Required parameters:
- The customer's `shopperId` or `contactId`
- The `value` of the transaction: a positive `value` will add funds to the account, while a negative `value` will post a charge to the account. This amount must be in the smallest currency unit (e.g., `500` for 5 USD)
- The `currency` that the transaction is in (note that Lightrail does not handle currency conversion and the contact must have an account in the corresponding currency)
- A `userSuppliedId`, which is a unique transaction identifier to ensure idempotence (for example, the order ID from your e-commerce system)

Optional parameters: 
- Arbitrary `metadata` (important if you are using complex [Promotions](#TODO add link))
- A boolean indicating if the transaction should be `pending` (default is `false`)


```javascript
const transactionParams = {
  value: 1200,
  currency: 'USD',
  userSuppliedId: 'tx-fe2d'
};
Lightrail.accounts.createTransaction({shopperId: 'customer-9f50629d'}, transactionParams).then(
  // called asynchronously
);
```

```php
$transactionParams = array(
    'shopperId'      => 'cust-a95a09',    // alternatively use 'contactId' => 'contact-0s459jy6h56'
    'currency'       => 'USD',
    'value'          => -350,
    'userSuppliedId' => 'order-a90h09a509gaj00-a4'
);

\Lightrail\LightrailAccount::createTransaction($transactionParams);
```

```java
// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 

String shopperId = contact.getShopperId();
LightrailTransaction tx = LightrailTransaction.Create.byShopperId(shopperId, -200, "USD");

// or

String contactId = contact.getContactId();
LightrailTransaction tx = LightrailTransaction.Create.byContact(contactId, -450, "USD");
```

```ruby
# NOTE Account transactions in the Ruby client are currently inconsistent with other client libraries, it will be updated soon. 

charge = Lightrail::Account.charge({
    shopper_id: '<SHOPPER ID>',     # or instead use contact_id: '<CONTACT ID>'
    currency: 'USD',
    value: -1350,
    user_supplied_id: 'order-fe2d'
  })

fund = Lightrail::Account.fund({
    shopper_id: '<SHOPPER ID>',     # or instead use contact_id: '<CONTACT ID>'
    currency: 'USD',
    value: 500,
    user_supplied_id: 'order-fe2d'
  })
```

The return value includes the full details of the transaction, including both the `userSuppliedId` you provided and a server-generated `transactionId`. 

### Transaction Simulation and Balance Checking

Because Accounts can contain conditionally-available value such as [Promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/promotions.md), doing a "balance check" means one of two things: checking the maximum value that an Account _could_ have available if all conditions are met on all attached value, or checking how much an Account has available for a particular transaction given its specific circumstances. 

The maximum value is typically used for displaying a customer's account balance (see also our drop-in [Balance Widget](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/drop-in-gift-cards.md#displaying-account-balance)). Transaction simulations are typically used to display details to a customer for confirmation before completing a transaction. 

#### Maximum Value

An Account's maximum possible available value can be determined by getting the Account Details and summing the active `valueStores`: 

```javascript
// DOESN'T EXIST YET
```

```php
// DOESN'T EXIST YET
```

```java
// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 

int maximumValue = contact.retrieveMaximumValue("USD");
```

```ruby
maximum_account_value = Lightrail::Account.get_maximum_account_value({
    shopper_id: '<SHOPPER ID>',     # or instead use contact_id: '<CONTACT ID>'
    currency: 'USD'
  })
```

#### Transaction Simulation

Before attempting to post a transaction, you may wish to do a transaction simulation to find out whether or not the account has enough funds. 

In the case of insufficient funds, this method can also tell you the maximum value for which the transaction _would be_ successful. For example, if you simulate a $35 drawdown, the method can tell you that it _would be_ successful if it were only for $20. 

The parameters for this method call are almost identical to those for posting a transaction. To get the maximum value, add `nsf: false` to your transaction parameters: 

```javascript
const simulationParams = {
  value: -6960,
  currency: 'USD',
  userSuppliedId: 'order-s3xx30',
  metadata: {
    cart: {
      total: 6960,
      items: [{
        quantity: 3,
        id: 'B009L1MF7A',
        unit_price: 2320
      }]
    }
  }
};
Lightrail.accounts.simulateTransaction({shopperId: 'customer-9f50629d'}, simulationParams).then(
  // called asynchronously
);
```

```php
$simulationParams = array(
    'shopperId'      => 'cust-a95a09',
    'currency'       => 'USD',
    'value'          => -6500,
    'userSuppliedId' => 'order-a90h09a509gaj00-a4',
    'nsf'            => 'false'
);

\Lightrail\LightrailAccount::simulateTransaction($simulationParams);
```

```java
// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 

String contactId = contact.getContactId();
LightrailTransaction simulatedTx = LightrailTransaction.Simulate.byContact(contactId, -3500, 'USD');

//or

String shopperId = contact.getShopperId();
LightrailTransaction simulatedTx = LightrailTransaction.Simulate.byShopperId(shopperId, -3500, 'USD');
```

```ruby
simulated_charge = Lightrail::Account.simulate_charge({
    shopper_id: '<SHOPPER ID>',     # or instead use contact_id: '<CONTACT ID>'
    currency: 'USD',
    value: -3500,
    user_supplied_id: 'order-fe2d-4c',
    nsf: false
  })
```

The response will be similar to the response for posting a transaction, with a `value` that indicates the maximum value that the account can provide for this transaction. Since this is just a simulation and NOT an actual transaction, it will not, for example, have a `transactionId`. 
