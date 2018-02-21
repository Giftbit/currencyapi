# Setting Up Lightrail Redemption Rules

## Introduction

This document is a step-by-step guide to setting up Lightrail Redemption Rules. 

- For further details on how to process redemption, read our Implementation Guide for [Redeeming Lightrail Value at Checkout](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/giftcode-checkout.md).
- For further details on how to write Redemption Rules, check out the [Redemption Rules Reference Documentation](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/feature-deep-dive/redemption-rules.md).
- To learn how to add promotions to Lightrail Cards, check out the use-case guide for [Creating Lightrail Promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/promotions.md).
- To learn more about Lightrail concepts, check out the section on the Lightrail Object Model in the [Lightrail API Docs](https://www.lightrail.com/docs/). 

## Concepts

Lightrail Redemption Rules enable setting sophisticated conditions on how value can be spent. For example, "$10 off if you spend at least $100," or "$10 off if you buy two or more pairs of jeans." These conditional promotions are values which are only redeemable under certain circumstances and if certain conditions are met: there is $10 additional value on the Card but it is only available in a purchase of at least $100, or if there are at least two pairs of jeans in the cart. 

Redemption Rules are defined at the time of creating a Promotion Program and apply to any Promotions  derived from that Program. Under the hood, Lightrail implements promotions as additional Value Stores that can be created and attached to existing Cards. Redemption rules are Boolean expressions which determine whether the value of such a Value Store can be spent in the context of a given Transaction. If the conditions are met, the value is unlocked; otherwise, the value is not available for that Transaction. 

When transacting against a Card and looking into collecting funds from its Value Stores, Lightrail Transaction Manager evaluates each Value Store's Redemption Rule against the Transaction's JSON object; the promotional value from that Value Store can be spent in that Transaction only if the rule evaluates to `true`. For example, at the checkout, if the cart total is over $100, the customer gets an additional $10 promotional value to redeem but this value is not available in purchases less than $100. 

## Setting Up Redemption Rules

There are two main steps to enable Redemption Rules in your system: 

- Add all relevant information about the context of the Transaction in the Transaction `metadata`. As discussed further below, we suggest that you proactively include a set of standard metadata most likely to be used in your Redemption Rules. 
- Create Promotion Programs with Redemption Rules and attach promotions based on these Programs to Gift Cards or Account Cards. This can be done in the Lightrail Web App.

### Establishing Metadata

Collecting and including metadata in the Transaction request is done programmatically in your Lightrail integration module. Here are some of the most important considerations in this step:

- Determine what information needs to be included in the metadata.
- Determine the structure to organize this information into a single `metadata` object.
- Identify how and from what sources you can obtain this information. 

#### Metadata Elements

We recommend evaluating what data you have available that might potentially be used for a promotion, and including that in the Transaction `metadata`. Any metadata not referenced in a redemption rule is safely ignored. So, if you are not sure, err on the side of including more metadata. It is better to have something you do not use yet than need something but not have it.

Note that adding promotions with new Redemption Rules is a fairly easy task that can be done routinely by your marketing team using the Lightrail Web App. Adding new metadata, on the other hand, entails changing the Transaction request which requires changing your Lightrail integration code by a software developer team. This can get costly and time-consuming, so, to minimize future developer involvement, try to be as general and proactive as possible in your initial design of Transaction metadata. We suggest that at minimum, you include the [Sample Metadata Structure](#sample-metadata-structure) in this document.

Note that we advise against including information about the customer in the `metadata`. To target a specific set of customers, filter them out and apply the promotion to their Gift Cards or Account Cards. To do this, you can either use the Lightrail Web App or you can call the API from within your business workflows.

#### Metadata Structure

Designing a suitable structure for the metadata is important since it affects how your Redemption Rules are formulated. For example, consider the Redemption Rule "if you spend at least $100 ," and assume the following metadata structure:

```javascript
"metadata": {
  "cart": {
     "items": [
       {
         "id": "B000F34ZKS", 
         "quantity": 1,
         "unit_price": 20695,
         "tags": ["gear", "outdoor", "clearance", "Rocketship"]
       },
       {
         "id": "B009L1MF7A", 
         "quantity": 3,
         "unit_price": 2320,
         "tags": ["apparel", "outdoor", "Rocketship"]
       }
     ]
  }  
} 
```

With this structure, you need to calculate the cart total within the rule which leads to the following:

```javascript
metadata.cart.items.map(item => item.quantity * item.unit_price).sum() >= 10000
```

Now consider the alternative structure in which the cart total is stored in a separate attribute on the `cart` object:
```javascript
"metadata": {
  "cart": {
    "total": 27655,
    //...
  }  
} 
```
This leads to the much simpler form for the rule:

```javascript
metadata.cart.total >= 10000
```

#### Collecting Metadata

Once you determined the metadata which needs to be included in the Transaction, you need to find the values. Some parts of the metadata, such as the ordered items, totals, and shipping method are usually available in your e-commerce platform data structures in the form of a _cart_ or an _order_ object. For product labels and categories, you may have to fetch the _product_ object from your back-end database. Other information, such as the store and branch ID are normally part of the configuration of your online store.

### Creating a Promotion with Redemption Rules

When creating a new Promotion Program in the Lightrail Web App you can specify the Redemption Rule. The rule will be applied to any promotion derived from this Program. To attach a promotion to a Card based on this Program in the Web App, you can go the Card page and select _Attach Promotion_ and then select the Promotion Program with the Redemption Rule you just created. Alternatively, you can do this programmatically via API calls as discussed in the use-case guide for [Creating Lightrail Promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/promotions.md).

To double check, call the Card details API endpoint to retrieve the list of all Value Stores on the Card. As you can see, in the example below, the Redemption Rule description is listed in the `restrictions` object of the attached Value Store:  

```javascript
GET https://api.lightrail.com/v1/cards/{cardId}/details
{  
  "details":{  
    "valueStores":[  
      {  
        "valueStoreType":"PRINCIPAL",
        "value":5000,
        "state":"ACTIVE",
        "expires":null,
        "startDate":null,
        "programId":"program-1ddxxa9",
        "valueStoreId":"value-66xxf2",
        "restrictions":[]
      },
      {  
        "valueStoreType":"ATTACHED",
        "value":500,
        "state":"ACTIVE",
        "expires":null,
        "startDate":null,
        "programId":"program-c7xxe6",
        "valueStoreId":"value-02xx6c",
        "restrictions":[  
          "If your cart total is at least $100." // <== redemption rule
        ]
      }
    ],
    "currency":"USD",
    "cardType":"GIFT_CARD",
    "asAtDate":"2017-09-14T18:09:09.520Z",
    "cardId":"card-dcxx37"
  }
}
```

### Balance-Check

Since the availability of an attached promotion depends on its Redemption Rule, the total available value of a Lightrail Card varies depending on the context of the Transaction. For example, if a customer has a Card with $10 principal value and a $5 attached promotion subject to some Redemption Rules, the total available value of the Card can be $10 or $15 depending on the Transaction. Therefore, when checking the available Balance of a Card, you need to provide the full context by providing a complete `metadata` object. 

Lightrail `dryRun` endpoints provide a mechanism for simulating a Transaction and checking the maximum a Card can pay towards a given Transaction, considering all the metadata. These endpoints also return a `transactionBreakdown` which provides the breakdown of how the value of the Transaction would be extracted from the Card's Value Stores. You can use this information to show the customer what promotions will be unlocked in their current checkout. Moreover, by comparing this list with the full list of all Value Stores on the Card, you can also show the customer the Value Stores which were NOT unlocked together with a hint about what they can do to unlock more promotions. More details on these endpoints are given in the Walk-Through Example below.

### Creating Transactions

All of the Transaction creation endpoints in Lightrail accept a `metadata` attribute to provide the metadata about the context of the requested Transaction. In the case of split-tender where you need to post a pending Transaction first and capture it later, the metadata must be included on _both_ the initial pending Transaction and the capture Transaction request. 

Note that Lightrail evaluates the metadata again at the time of _capture_ and if the Redemption Rules are not met (because of missing or incomplete metadata) the call to capture will fail. So, you need to repeat the Transaction metadata when you make the call to _capture_ a pending Transaction.

See our implementation guide for [Redeeming Lightrail Value at Checkout](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/giftcode-checkout.md) for further details about handling split-tender.

### Walk-Through Example

Suppose you want to boost your sales by giving an additional $5 promotional value to customers who make a purchase of at least $100. Here are the steps necessary to unlock this promotion:

- Make sure you have the total purchase value in your Transaction metadata. If you follow our [Suggested Metadata Structure](#suggested-metadata-structure) you already have this as `metadata.cart.total`.
- Create a Promotion Program using the Lightrail Web App with the following Redemption Rule: `metadata.cart.total >= 10000`.
- Attach a $5 promotion based on this Program to the Account Card of eligible customers.

#### Balance-Check

When processing an order at the checkout, you need to determine how much is available in the customer's Account Card. The `dryRun` endpoint can provide this information if you send a request with the value of `nsf` attribute set to `false`. This tells Lightrail to return a best-effort would-be Transaction instead of a not-sufficient-funds (NSF) error if the Card's available value is not enough for the Transaction. 

For example, assuming the customer wants to check out a cart with $253.35 balance, using a Card with $50 principal value and a $5 conditional promotion (as discussed above), the `dryRun` request will look like the following. This asks the Lightrail API to simulate a Transaction with the value of $253.35 on the provided Card and in case of insufficient funds, return the maximum the Card can spend in this Transaction: 

```javascript
POST https://www.lightrail.com/v1/cards/{cardId}/transactions/dryRun
{
  "userSuppliedId":"order-s3xx30",
  "value":-27655,
  "currency":"USD",
  "nsf": false,
  "metadata": {
    "cart": {
      "total": 27655,
      "items": [
        {
          "id": "B000F34ZKS", 
          "quantity": 1,
          "unit_price": 20695,
          "tags": ["gear", "outdoor", "clearance", "Rocketship"]
        },
        {
          "id": "B009L1MF7A", 
          "quantity": 3,
          "unit_price": 2320,
          "tags": ["apparel", "outdoor", "Rocketship"]
        }
      ]
    }  
  } 
}
```

The response will be similar to the following. Note that this is just a simulation and NOT an actual Transaction; for instance, it does not have a `transactionId`. The response indicates that for this Transaction, the maximum value this Card can provide is $55. In other words, if you attempt to create a drawdown Transaction for up to $55, it will be successful. 

By checking the `transactionBreakdown`, you can see that this value is coming from two different Value Stores: one with a $50 value (which is the principal Value Store of the Card), and the other with a $5 value (which is the attached promotion). Generally, it is a good user-experience if you show the user the promotions unlocked for the Transaction. 

```javascript
{  
  "transaction":{  
    "value":-5500,
    "userSuppliedId":"order-s3xx30",
    "dateCreated":null,
    "transactionType":"DRAWDOWN",
    "transactionAccessMethod":"CARDID",
    "valueAvailableAfterTransaction":0,
    "cardId":"card-dcxx37",
    "currency":"USD",
    "transactionBreakdown":[  
      {  
        "value":-500,
        "valueAvailableAfterTransaction":0,
        "valueStoreId":"value-02xx6c"
      },
      {  
        "value":-5000,
        "valueAvailableAfterTransaction":0,
        "valueStoreId":"value-66xxf2"
      }
    ],
    "transactionId":null,
    "metadata":{  
      "cart":{  
        "total":27655,
        "items":[  
          {  
            "quantity":1,
            "id":"B000F34ZKS",
            "unit_price":20695,
            "tags":["gear","outdoor","clearance","Rocketship"]
          },
          {  
            "quantity":3,
            "id":"B009L1MF7A",
            "unit_price":2320,
            "tags":["apparel","outdoor","Rocketship"]
          }
        ]
      }
    },
    "codeLastFour":"YNJC"
  }
}
```

Now, suppose that the customer removes one of the items from the cart, therefore changing the metadata. Particularly, the cart total is now $69.60. The new request to the `dryRun` endpoint looks like the following:

```javascript
POST https://www.lightrail.com/v1/cards/{cardId}/transactions/dryRun
{
  "userSuppliedId":"order-s3xx30",
  "value":-6960,
  "currency":"USD",
  "nsf": false,
  "metadata": {
    "cart": {
      "total": 6960,
      "items": [
        {
          "id": "B009L1MF7A",
          "quantity": 3,
          "unit_price": 2320,
          "tags": ["apparel", "outdoor", "Rocketship"]
        }
      ]
    }  
  } 
}
```

You can see from the response that the Card can now pay only up to $50 for this Transaction. The `transactionBreakdown` shows that for this Transaction, there is only one Value Store available with $50. This is because the metadata of this Transaction did not meet the Redemption Rules of the other Value Store (the $5 promotion) which is only spendable if the cart total is $100 or more.

```javascript
{  
  "transaction":{  
    "value":-5000,
    "userSuppliedId":"order-s3xx30",
    "dateCreated":null,
    "transactionType":"DRAWDOWN",
    "transactionAccessMethod":"CARDID",
    "valueAvailableAfterTransaction":0,
    "cardId":"card-dcxx37",
    "currency":"USD",
    "transactionBreakdown":[  
      {  
        "value":-5000,
        "valueAvailableAfterTransaction":0,
        "valueStoreId":"value-66xxf2"
      }
    ],
    "transactionId":null,
    "metadata":{  
      "cart":{  
        "total":6960,
        "items":[  
          {  
            "quantity":3,
            "id":"B009L1MF7A",
            "unit_price":2320,
            "tags":["apparel","outdoor","Rocketship"]
          }
        ]
      }
    },
    "codeLastFour":"YNJC"
  }
}
```

#### Showing the Balance and Hints to the Customer

The `transactionBreakdown` object provides valuable information for letting the customer know what promotions are unlocked and what promotions still remain inactive for the current cart. This user experience can improve your sales by hinting at what the customer can do to take advantage of more promotions. 

Consider the last example above in which only one Value Store was available for $50. You can get the list of all Value Stores on the Card by calling the following endpoint:

```javascript
GET https://api.lightrail.com/v1/cards/{cardId}/details
{  
  "details":{  
    "valueStores":[  
      {  
        "valueStoreType":"PRINCIPAL",
        "value":5000,
        "state":"ACTIVE",
        "expires":null,
        "startDate":null,
        "programId":"program-1ddxxa9",
        "valueStoreId":"value-66xxf2",
        "restrictions":[]
      },
      {  
        "valueStoreType":"ATTACHED",
        "value":500,
        "state":"ACTIVE",
        "expires":null,
        "startDate":null,
        "programId":"program-c7xxe6",
        "valueStoreId":"value-02xx6c",
        "restrictions":[  
          "If your cart total is at least $100."
        ]
      }
    ],
    "currency":"USD",
    "cardType":"GIFT_CARD",
    "asAtDate":"2017-09-14T18:09:09.520Z",
    "cardId":"card-dcxx37"
  }
}
```

Now, if you compare this list with the list of unlocked Value Stores in the `transactionBreakdown`, you can determine what promotions were NOT unlocked and display meaningful hints to the customer (based on the `restrictions` attribute) on how they can unlock them. Here is a simple example of the user experience:

![cart-example-redemption-rules-1](https://giftbit.github.io/Lightrail-API-Docs/assets/cart-example-redemption-rules-1.png)

![cart-example-redemption-rules-2](https://giftbit.github.io/Lightrail-API-Docs/assets/cart-example-redemption-rules-2.png)

#### Transaction

To proceed with the payment, note that there is $50 available on the Card but the checkout balance is $69.60. So you need to process a split-tender in which the remainder of the order balance is paid by a third-party payment method: 

- Post a pending Transaction on the Card for its maximum available value for this Transaction, i.e. $50.
- Charge the rest of the checkout balance on another payment method, e.g. a credit card.
- If the third-party payment succeeds, _capture_ the pending Transaction on the Lightrail Card; otherwise, _void_ it and tell the customer the payment failed.

For further details about handling split-tender at the checkout, see our implementation guide for [Redeeming Lightrail Value at Checkout](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/giftcode-checkout.md).

The request to create the pending Transaction will be similar to the following:

```javascript
POST https://www.lightrail.com/v1/cards/{cardId}/transactions
{
  "userSuppliedId":"order-s3xx30",
  "value":-5000,
  "currency":"USD",
  "pending": true,
  "metadata": {
    "cart": {
      "total": 2320,
      "items": [
        {
          "id": "B009L1MF7A",
          "quantity": 3,
          "unit_price": 2320,
          "tags": ["apparel", "outdoor", "Rocketship"]
        }
      ]
    }  
  } 
}
```

This will lead to a very similar response to that of the `dryRun` endpoint, except that it will include a real Transaction object with `transactionId` and `dateCreated`. 

After attempting to charge the third-party, you will have to _capture_ or _void_ the pending Transaction. These requests will be similar to the following. Note that in the _capture_ request, you have to repeat the metadata  since Lightrail will verify that again before capturing the Transaction.

```javascript
POST https://api.lightrail.com/v1/cards/{cardId}/transactions/{pendingTransactionId}/capture
{  
  "userSuppliedId":"order-s3xx30-capture",
  "metadata":{  
    "cart":{  
      "total":2320,
      "items":[  
        {  
          "id":"B009L1MF7A",
          "quantity":3,
          "unit_price":2320,
          "tags":["apparel","outdoor","Rocketship"]
        }
      ]
    }
  }
}
```

```javascript
POST https://api.lightrail.com/v1/cards/{cardId}/transactions/{pendingTransactionId}/void
{
  "userSuppliedId":"order-s3xx30-void"
}
```

## Sample Metadata Structure

We suggest that you include the following metadata structure in your Transaction requests. This will cover the basic information needed for most common Redemption Rules. 

Note that these are just for your reference and you can pick and choose from these samples or add any additional attributes as you see fit for your use-cases. 

- `cart` (object): the main object to record the shopping cart and its contents.
  - `total` (integer): the total value of the purchase. Whether this is pre- or post-tax depends on your decision.
  - `items ` (array of objects): ordered items in the cart, each with the following attributes: 
    - `id` (string): the product ID.
    - `quantity` (integer): the quantity.
    - `unit_price` (integer): the unit price of the item.
    - `tags` (array of string): a list of product categories.

Additionally, if you foresee that you will have Redemption Rules based on the payment method or the origin of the Transaction, here are some additional suggested metadata:

- `origin` (object): metadata about the origin of the transaction
  - `id` (string): the store ID in which the Transaction takes place. 
  - `tags` (array of string): other attributes of the origin.
- `payment` (object):
  - `id` (string): the ID of the payment method.
  - `tags` (array of string): other attributes of the payment.
- `delivery`(object):
  - `id` (string): the delivery method ID.
  - `tags` (array of string): other attributes of the delivery.

Here is an example of the metadata based on this structure:

```javascript
{
  "cart": {
    "total": 25335,
    "items": [
      {
        "id": "B000F34ZKS", //tent
        "quantity": 1,
        "unit_price": 20695,
        "tags": ["gear", "outdoor", "clearance", "Rocketship"]
      },
      {
        "id": "B009L1MF7A", //jacket
        "quantity": 2,
        "unit_price": 2320,
        "tags": ["apparel", "outdoor", "Rocketship"]
      }
    ]
  },
  "origin": {
  	"id": "A210",
    "tags": [
      "warehouse", "CA"
    ]
  },
  "payment":{
    "id": "stripe",
    "tags": []
  },
  "delivery":{
    "id": "USPS",
    "tags": []
  }
}
```

## Common Rule Examples

Here are some examples of common Redemption Rules based on the above metadata structure. Note that you can straightforwardly combine these rules using logical operators to create more complex rules.

We will provide examples for a $5 off subject to some Redemption Rule. Note that once you create a Promotion Program with the Redemption Rule, you can attach any value based on that Promotion Program to a Card. So, the $5 value in this section is only an example. 

#### Minimum Transaction Value

 Promotional value is available only if the Transaction value exceeds a minimum amount. For example, $5 off if you spend more than $100:

```javascript
metadata.cart.total >= 10000
```

#### Specific Product(s) 

You restrict a promotional value to a specific product. For example:

- $5 off if you buy a 3-person tent (with the product ID `B000F34ZKS`):

```javascript
//specific product
metadata.cart.items.some(item => item.id=='B000F34ZKS')
```
#### Specific Product Category

You can restrict a promotional value to a specific Product category.   

-  $5 off if you buy an _outdoor_ product:

```javascript
//specific product category
metadata.cart.items.some(item => item.tags.some(tag => tag=='outdoor'))
```

By dynamically tagging some products for a promotion, this mechanism can be used to dynamically enable a promotion for a group of products:

```javascript
//specific product category
metadata.cart.items
  .some(item => item.tags.some(tag => tag=='back-to-school-sale'))
```

You can also set more complicated rules based on product categories and create more powerful promotion rules. For example:  

-  $5 off if you buy an _outdoor_ product not in _clearance_:

```javascript
//specific product category
metadata.cart.items
  .some(item => (item.tags.some(tag => tag=='outdoor') && !item.tags.some(tag => tag=='clearance'))
```

#### Minimum Quantity

These rules require that the quantity of a particular item or category of items in the cart exceeds a minimum number in order for the promotional value to be available. For example:

- $5 off if you buy 3 or more pairs of any types of Jeans:

```javascript
//minimum product category quantity
metadata.cart.items
  .filter(item => (item.tags.some(tag => tag=='jeans')))
  .map(item => item.quantity)
  .sum() >= 3
```

#### Restrictions on Payment

In some use-cases a promotional value depends on the conditions around the payment such as the payment method or other payment attributes. 

Note that in this context, payment refers to the third-party payment in split-tender where the remainder of an order is paid by a payment method other than Lightrail. In order to provide this metadata, your workflow should allow the user to commit to the third-party payment method before posting the Lightrail pending Transaction; otherwise, you will not be able to provide the right metadata to unlock such promotions. For example:

- $5 off if you pay with debit:

```javascript
metadata.payment.tags.some(tag => tag == 'debit')
```

#### Restrictions on Origin

If you have multiple stores or operate in different regions, you can set Redemption Rules based on different attributes on the origin of the Transaction such as type of store, region, or store ID. For example:

- $5 off if you make a purchase in the Seattle branch (with store ID `A210`): 

```javascript
metadata.origin.id == 'A210'
```

- $5 off if you order from any warehouse branch in California:

```javascript
metadata.origin.tags.some(tag => tag == 'warehouse' && tag == 'CA')
```

#### Restrictions on Delivery

If your online store supports multiple types of delivery, you can set Redemption Rules based on the delivery method or other attributes related to the delivery.

- $5 off if the customer picks up the ordered items from the store:

```javascript
metadata.delivery.id == 'store-pickup'
```

#### 

