<a name="object-model-anchor"></a>
## The Lightrail Object Model
The full API object model is here for your reference. We will discuss these objects and their relationships in this section. Depending on your use-case you may only need some of these objects and the corresponding endpoints. If you prefer to start with something more hands-on, feel free to move on to [Common Use-Cases](#use-cases-anchor).   


![Lightrail Object Model](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/apiary-sources/assets/lightrail-objects.svg)

### Core Objects: Cards and Value Stores

The Card is the core concept in the Lightrail model and provides the main interface for storing, maintaining, and interacting with any sort of value that your business wishes to issue. Currently, there are two types of cards in Lightrail, _Gift Cards_ and _Account Cards_, which are distinguished by their `cardType` attribute. We will discuss these two types of cards in a bit.

A Card's value is stored in an object called a _Value Store_ which represents a specific instance of issued value and its attributes, such as its amount and validity period.  

When a Card is created, a `principal` Value Store is automatically created and added to it. When additional promotions are added to a Card, they are represented as `attached` Value Stores. Unlike `attached` Value Stores which are often short-lived, the `principal` Value Store is tied to the Card throughout its lifetime and represents the overall state of the Card. For example, if the Principal Value Store is expired or canceled, the Card is also considered expired or canceled.

For example, a customer can buy a gift card with a primary value of $30 which never expires. This is stored in the Card's `principal` Value Store. Later, and in order to encourage the recipient to spend the gift value, you may attach a $5 promotional value to this Card as part of your _Back to School_ campaign, only valid in the last week of August. While this `attached` Value Store is active, the Card holder can spend $35 with the Card; when the attached Value Store expires at the end of August, the Principal Value Store will still be valid and the Card can still be used up to $30. See [the diagram below](#transaction-valustore-anchor) for a depiction of this example.


### Programs
A Lightrail _Program_ is a template for issuing Lightrail value, in the form of Value Stores. Programs specify the general attributes of Value Stores that derive from them, such as currency, validity period, minimum/maximum amount, as well as the constraints that apply to spending them, known as _Redemption Rules_.

Lightrail recognizes that issuing value seldom happens in isolation and is usually part of a broader context which we call a Program. Therefore, whenever a new Value Store is created (e.g. at the time of Card creation or attaching promotions) you need to specify the Program used for creating that Value Store. As an analogy, think of Programs as minting facilities and Value Stores as coins. Just as valid coins can only be created by a minting facility, Lightrail values can only be issued as part of a Program and are subject to its broader rules and restrictions. 

Lightrail currently supports two types of Programs which are differentiated based on their `type` attribute: 

- _Principal Programs_ are used to organize and create  `principal` Value Stores, namely to create new Cards, and
- _Promotional Programs_ are used to create `attached` Value Stores which can be added to existing cards and provide some additional promotional value to the card holder subject to more restrictive conditions.

Note that Cards are also connected to Programs through their Value Stores and cannot exist in isolation. Therefore, before you start creating Cards, you need to set up at least one Principal Program to be used for creating the Principal Value Stores of your Cards. The Principal Value Store is created automatically in the course of Card creation, but you have to provide the `programId` in the Card creation request. Account Cards can be an exception to this as we will see in a bit.

Since Lightrail does not handle currency exchange, it requires a single currency for all the Value Stores on the Card. For example, if you create a Card with the Principal Value Store in CAD, all subsequent attached promotions must derive from CAD Programs.

Programs are also a great way to organize, track, and analyze values. For example, you probably want to know how many people took advantage of your _Back to School_ promotions and how it affected your sales. The Lightrail Web App provides various reports, stats, and analyses for the values created in each Program. 

You can create programs using the Lightrail Web App; the API also has [an endpoint](#get-programs-anchor) for programmatically retrieving the list of your Programs.

### Contacts

Individual customers are represented by _Contact_ objects in Lightrail. You can store some basic information about the individual such as their name and email address on the Contact object. Contacts can be associated with Cards in order to track and analyze different Lightrail values held by a customer as we will discuss below.

### Gift Cards

As the name implies, Gift Cards represent a value created as a gift. Lightrail Gift Cards have a `fullcode`,  a  unique and unguessable alpha-numeric code, also referred to as Gift Code, which can be used by the Gift Card recipient to redeem its value.

Since anyone who knows the `fullcode` can redeem the Gift Card value, the `fullcode` is often delivered to the Gift Card's recipient in confidence. To minimize the risk of its exposure (e.g. in the course of passing JSON objects to the browser) only [one specific Lightrail endpoint](#get-fullcode-anchor) returns the `fullcode` and other endpoints only return the last four characters of the code when necessary. For similar reasons, we recommend that you refrain from persisting the `fullcode` in your database or logs.

While Contacts are not mandatory for Gift Cards, it is possible and recommended to associate a Gift Card with a Contact when you know the recipient. This will enable tracking all Lightrail values available to a customer both programmatically via the API and in the Lightrail Web App.

### Account Cards 

Account Cards represent values associated with an individual customer, represented by a linked Contact object. Account Cards can essentially be thought of as a customer's account, making them suitable for implementing customer account credit or points programs. Lightrail requires that a Contact has only one Account Card per currency. This makes handling transactions against account credits simpler as will be discussed in the [Account Credit Use-Case](#use-cases-account-credits-anchor).

Unlike Gift Cards, since Account Cards are tied to a known customer, they do not have a `fullcode` and interaction with their value is only possible via the Card object interface.

To keep creation of Account Cards simpler, Lightrail does not require specifying a Program for Account Card creation and uses a default Program automatically created under the hood. The Principal Value Stores of all of your Account Cards (in each currency) are derived from that default Program. 

### Transactions

Various interactions with the Lightrail system take place in the form of _Transactions_. The most common types of Transactions are adding or deducting value from a Card, also known as  _funding_ and _drawdown_. Some other actions on Cards or Value Stores are also modelled as Transactions; for example, _activation_, _cancellation_, _freezing_, and _unfreezing_.

Lightrail supports a two-step _pending_ drawdown. A pending drawdown Transaction withholds the funds temporarily until eventually they are collected via a subsequent _capture_ Transaction, or canceled via a _void_ Transaction. 

Transactions are [primarily](#post-transaction-by-cardid-anchor) created by `cardId`.  But to simplify Gift Card redemption at the checkout, Lightrail also provides [an endpoint](#post-transaction-by-fullcode-anchor) for creating Transactions by a Card's `fullcode`. To improve security, this endpoint only allows drawdown Transactions.

One of the features of the Lightrail API is encapsulating the Card Value Stores behind a simple interface at the time of Transaction. While you can add many promotional attached Value Stores to Cards, at Transaction time, you do not need to worry about the logic of splitting the drawdown value against potentially many Value Stores; Lightrail transaction processing automatically handles this for you. The  `transactionBreakdown` object in a Transaction response provides the details of how the funds were extracted from different ValueStores by Lightrail behind the scene.

For example, if there is $30 in the Principal Value Store and a $5 attached Value Store from a promotional _Back to School_ program, when attempting a $8 drawdown, Lightrail automatically decides the break-down of this amount against existing Value Stores and you do not have to specify or even be aware of them. In this case, for example, Lightrail will prioritize the spending of the $5 value which is closer to its expiry date, and then, charges the remaining $3 from the Principal Value Store.  

### Redemption Rules

Redemption Rules are a powerful feature of Lightrail which enable setting sophisticated conditions on how value can be spent. Redemption rules are defined on Programs and are applied to the Value Stores created from them.

Redemption Rules can unlock powerful marketing promotions such as, "$10 off if you spend at least $100," or "$15 off if you buy two or more pairs of jeans." Currently, Redemption Rules are defined at the time of Program creation in the Lightrail Web App. 

When transacting against a Card and looking to collect the funds from its different Value Stores, Redemption Rules determine whether or not each of the Card's Value Stores is spendable for that Transaction. Every rule is a Boolean expression that operates on the Transaction request object's `metadata`; the Value Store will be available for spending on that Transaction only if the rule evaluates to `true`. 

Transaction `metadata` is a generic JSON object provided in the Transaction request object which represents any additional information you wish to provide and store, including contextual information based on which redemption rules operate. This provides a very powerful and flexible mechanism to define any relevant metadata in the Transaction request and use this metadata to make decisions about unlocking promotional values. 

Check out the <a href="https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/redemption-rules.md" target="_blank">Redemption Rules Implementation Guide</a> and <a href="https://github.com/Giftbit/Lightrail-API-Docs/blob/master/feature-deep-dive/RedemptionRules.md" target="_blank">Redemption Rules Reference Documentation</a> for further details.

### Walk-Through Example

Suppose that to boost your sales you want to give a $5 promotional value to customers who would spend at least $100 at your online store.

This rule can be formulated as the following:

`metadata.cart.total >= 10000` 

Note that `cart ` is a custom metadata object defined by your system. After you create a new Promotion Program with this redemption rule, you can create a new $5 Value Store derived from this Program and attach it to some Account or Gift Cards, thereby giving them a $5 promotional value subject to this condition. 

At the checkout page, your e-commerce system examines the customer's cart and accordingly provides a `cart` object in the `metadata` attribute in the Transaction request object. For example:

```
"metadata": {
    "cart": {
      "total": 10350
    }
}
```

Once this Transaction request is received, Lightrail will iterate through the Card's Value Stores and evaluate their redemption rules against the Transaction `metadata`. In this case, if the `cart.total` is greater than or equal to $100 (i.e. 10000 cents), it unlocks the $5 Value Store for spending. The following diagram depicts this process for a sample Transaction. 

<a name="transaction-valustore-anchor"></a>

![Transaction, Value Stores, and Redemption Rules](https://giftbit.github.io/Lightrail-API-Docs/assets/transaction-valustores.svg)

Lightrail Transaction object includes a `transactionBreakdown` which provides the breakdown of how the value of the Transaction was extracted from the Card's Value Stores. Here is an example for the above example:

```
"transactionBreakdown":[  
      {  
        "value":-300,
        "valueAvailableAfterTransaction":2700,
        "valueStoreId":"v9nxx5p"
      },
      {  
        "value":-500,
        "valueAvailableAfterTransaction":0,
        "valueStoreId":"v6mxx9a"
      }
    ]
```

You can use this information to show the customer what promotions were unlocked in their current checkout. 

Moreover, when using the Transaction simulation endpoints, you can compare the list of unlocked Value Stores with the full list of all Value Stores on the Card, and show the customer what Value Stores were NOT unlocked together with a hint about what they can do to unlock more promotions. This is an important user experience to encourage customers to take advantage of available promotions and boost your sales.  

Check out the <a href="https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/redemption-rules.md" target="_blank">Redemption Rules Implementation Guide</a> for a more detailed example.