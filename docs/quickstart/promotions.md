# Promotions

Promotions enables you to incentive and engage your customers. Once you’re set up with [customer accounts](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/accounts.md) that tell you who’s spending and how they’re spending, take the next step by adding tailor-made promotions that match your customer’s preferences. 

This is a quick step-by-step guide to adding two types of promotions to existing customer accounts using the Lightrail API: a sign-up bonus (this type of promotion can be applied to any use case that incentivizes customers to spend at a particular time), and a high value purchase incentive (this type of promotion can be applied to any use case that rewards customers for making a particular type of purchase). 

Want more? Lightrail supports many more complex types of promotions. See [Redemption Rules](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/redemption-rules.md) to take your incentives to the next level or [contact us](mailto:hello@lightrail.com) for more options. 

## Getting Started

This guide assumes that you are already using Lightrail to power your customer accounts. 

If you don’t already have that set up, start by [signing up](https://www.lightrail.com/app/#/register) for a Lightrail account and using our client libraries to get started with [customer accounts](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/quickstart/accounts.md). 

Note that our client libraries do not yet support Promotions: this guide will explain how to implement them with the web app and a few simple API calls. 

## Example 1: Sign-up Bonus (a "when-to-buy" incentive)

This is a quick walk-through of how to set up a $10 sign-up bonus, valid for 60 days, that is automatically attached to every new customer account. It's an example of a generic type of promotion that can be widely applied to many different use cases. 

### Step 1: Initialize Promotion Program

This step is done in the Lightrail webapp. 

The details of the promotion are set in the [Promotion Program](https://www.lightrail.com/app/#/promotions?_k=ilb8m9) section of your account. 

For a $10 bonus that can be used for 60 days after a customer signs up, set the following details:
- Keep the default Program Period ('Start Date: Now' and 'End Date: Forever') - this lets you keep using this program indefinitely to attach bonuses whenever customers sign up
- Set the Currency Type to 'USD' (or your store's currency)
- Set the Value Options to 'Fixed Value' and set the amount to $10
- In Promotion Expiry, set the default validity period to 60 days (this means the bonus will be valid for 60 days once it's attached to a customer's account)
- Skip the Redemption Rules section (these are used for [other types of promotions](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/redemption-rules.md))

Once saved, you’ll need to copy the `programId` to be used in the API calls below.

### Step 2: Attach Promotion to Account

This step takes place in the code that handles new sign-ups. 

Promotions are attached to Accounts as new `valueStores`. A `valueStore` is simply a container for value that can be subject to extra rules and conditions -- for example, "this value expires after 60 days". 

After creating the new Account, save the `cardId` for the new Account Card and use it to call the `.../cards/{cardId}/valueStores` endpoint with the following parameters: 
- The `programId` for the Promotion Program you created in Step 1 (recall the Promotion Program sets the promotion's basic details: validity period, value range, etc) 
- A `userSuppliedId` to guarantee idempotency (for example, `{cardId}-sign-up-bonus`)
- The `currency` of the sign-up bonus -- must match the currency of the Promotion Program
- The `initialValue` of the sign-up bonus -- an integer in the smallest currency unit (e.g., $10.00 USD is `1000`); amount must match the value (or be within the value range) set in the Promotion Program's Value Options in Step 1

If you are using this example as a starting point for creating a different promotion, you may also (optionally) include a `startDate` and `expires` to override the default validity period set by the Promotion Program. Considerations: 
- The `startDate` must still be within the range set by the Promotion Program (in the context of this example, this can be any date because we set the Program Period from 'Now' to 'Forever' in Step 1)
- The `expires` date can be set to any future date and will override the validity period set in the Promotion Program (in the context of this example, you could set the sign-up bonus to be valid until August 31 regardless of when sign-up occurred, instead of just letting it expire after 60 days)

Sample request call: 

```
POST https://api.lightrail.com/v1/cards/{cardId}/valueStores
{
  "programId": "program-53xx36",
  "currency": "USD",
  "initialValue": 1000
  "userSuppliedId": "card-6dxx89-sign-up-bonus",
}
```

Example response: 

```json
{
  "valueStore": {
    "cardId": "card-6dxx89",
    "valueStoreId": "value-e3xxce",
    "valueStoreType": "ATTACHED",
    "currency": "USD",
    "dateCreated": "2017-08-28T21:47:27.154Z",
    "programId": "program-53xx36",
    "expires": "2017-09-05T06:59:59.000Z"
  }
}
```

Now that the Promotion is attached to the Account, its value will be added to the Account's available credit and will be automatically used for any transactions. 


## Example 2: High Value Purchase Incentive (a "what-to-buy" incentive)

This type of Promotion -- incentivizing specific spending behaviour -- relies on Lightrail's Redemption Rules. These conditional promotions attach value to an Account, that can only be redeemed if certain conditions are met: for example, there is an additional $25 in your Account, but it's only available if your total purchase is $300 or more. 

For more detailed information, see our [Redemption Rules guide](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/redemption-rules.md).

### Step 1: Send Detailed Metadata with Transactions

This step takes place in your checkout code. 

Rules use the `metadata` from a transaction request body to determine whether a given Promotion can be redeemed in the context of a given transaction. If the conditions are met, the value is unlocked; otherwise, the value is not available for that transaction. 

This means that it is important to send the right details, structured in the right way, in your transaction `metadata`. For example, for a Promotion that is only available if "order total is $300 or more", the metadata would need to include at least the following: 

```json
{
  "cart": {
    "total": 35000
  }
}

// For more detailed recommendations on metadata contents and structure, see https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/redemption-rules.md
```

### Step 2: Initialize Promotion Program

This step takes place in the Lightrail webapp. It is analogous to [Step 1 of the Sign-Up Bonus](#step-1-initialize-promotion-program) flow, but involves setting more complex details. 

Initialize a new [Promotion Program](https://www.lightrail.com/app/#/promotions?_k=ilb8m9) in your Lightrail dashboard. For a $25 Promotion that can only be used if the customer makes a purchase of $300 or more, set the following details:
- Keep the default Program Period ('Start Date: Now' and 'End Date: Forever') - this lets you keep using this program indefinitely
- Set the Currency Type to 'USD' (or your store's currency)
- Set the Value Options to 'Fixed Value' and set the amount to $25
- In Promotion Expiry, set the default validity period to "Forever" (this means that once the Promotion has been applied to a customer's account, it will not expire)
- In the Redemption Rules section, set the Rule Description to "Get $25 if you spend over $300" and set the Rule Syntax to "`metadata.cart.total >= 30000`" (**note** this syntax relies on your metadata JSON adhering to a particular structure; for more examples, see the [Redemption Rules guide](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/use-cases/redemption-rules.md#common-rule-examples))

Save the Promotion Program, then copy and save the Program ID that gets generated. 

### Step 3: Attach Promotion to Account

This step is analogous to [Step 2 of the Sign-Up Bonus](#step-2-attach-promotion-to-account) flow. How you determine which customers should receive the Promotion is highly dependent on your system and marketing strategies. 

Use the `cardId` for each Account that should receive the promotion to call the `.../cards/{cardId}/valueStores` endpoint with the following parameters: 
- The `programId` for the Promotion Program you created in Step 2
- A `userSuppliedId` for idempotency (for example, `{cardId}-over-300-promo`)
- The `currency` of the Promotion ('USD' or your store's currency)
- The `initialValue` of the Promotion: must be set to $25 (i.e., `2500`) because this is a fixed-value Promotion

Example request:
```
POST https://api.lightrail.com/v1/cards/{cardId}/valueStores
{
  "programId": "program-9079f0",
  "currency": "USD",
  "initialValue": 2500
  "userSuppliedId": "card-6dxx89-over-300-bonus",
}
```

Example response:
```json
{
    "valueStore": {
        "cardId": "card-6dxx89",
        "valueStoreId": "value-1cc243",
        "valueStoreType": "ATTACHED",
        "currency": "USD",
        "dateCreated": "2017-12-21T18:23:13.708Z",
        "programId": "program-9079f0",
        "metadata": {
            "_redemption_rule": "metadata.cart.total >= 30000",
            "_redemption_rule_explanation": "Get $25 if you spend over $300"
        }
    }
}
```

Now that the Promotion is attached to the Account, its value will be added to the Account's available credit and will be automatically used for any transactions with a `cart.total` over $300. 

If you want to check to make sure that the Promotion was properly attached, you can simulate a qualifying transaction: 
```
POST https://api.lightrail.com/v1/cards/{cardId}/transactions/dryRun
{
  "currency": "USD",
  "value": -32500,
  "userSuppliedId": "testing-for-over-300-promotion",
  "metadata": {
    "cart": {
      "total": 32500
    }
  },
  "nsf": false   // This ensures we will receive a simulated transaction breakdown even if the Account doesn't have the funds to cover the transaction 
}
```

Example response:
```json
{
    "transaction": {
        "value": -32500,
        "userSuppliedId": "testing-over-300-promo-is-attached",
        "dateCreated": null,
        "transactionType": "DRAWDOWN",
        "transactionAccessMethod": "CARDID",
        "cardId": "card-6dxx89",
        "currency": "USD",
        "transactionBreakdown": [
            {
                "value": -30000,
                "valueStoreId": "value-f070bb15b79c4064a206c66c7342dc5c"
            },
            {
                "value": -2500,
                "valueStoreId": "value-1cc243"   // This is our Promotion
            }
        ],
        "transactionId": null,
        "metadata": {
            "cart": {
                "total": 30500
            }
        },
        "codeLastFour": "8665"
    }
}
```
