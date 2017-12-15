# Promotions

Once you’re set up with [customer accounts](#TODO add link) that tell you who’s spending and how they’re spending, take the next step by adding tailor-made promotions that match your customer’s preferences. 

This is a quick step-by-step guide to adding promotions to existing customer accounts by programmatically calling the Lightrail API. 

## Getting Started

This guide assumes that you are already using Lightrail to power your customer accounts. 

If you don’t already have that set up, start by [signing up](https://www.lightrail.com/app/#/register) for a Lightrail account and using our client libraries to get started with [customer accounts](#TODO add link). 


## Step 1: Initialize Promotion Program

This step is done in the Lightrail webapp. 

Set up at least one [Promotion Program](https://www.lightrail.com/app/#/promotions?_k=ilb8m9) from your Lightrail dashboard. The program specifies some overall attributes of the Promotion such as currency, validity period and value range. For example, you can create a _Back to School_ Promotion Program with values in USD, valid only in the last week of August. 

Save the Promotion Program ID that gets generated (you can find it on the Promotion Program page under "Program Details"). 

## Step 2: Add Promotion to Account

How you determine which accounts should receive a promotion highly depends on your system and your marketing strategies. 

### Create a New Value Store

To add a promotion to an account, you'll call the `valueStores` endpoint under `cards`. 
 
Required parameters:
- The `cardId` for the account card. 
- The `programId` for the Promotion Program you created in Step 1 (recall the Promotion Program sets the details for the promotion: validity period, value range, etc). 
- A `userSuppliedId` to guarantee idempotency, i.e. to ensure that repeating the call with the same parameters will not lead to repeated server-side action. 
- The `currency` of the promotion, for consistency verification: it must match the currency of the Promotion Program. Future versions of Lightrail may make this optional.

Optional parameters:
- An `initialValue` which must be within the range specified in the Promotion Program (**note** if this is not provided, the promotion will be added with zero value).
- A `startDate` and `expires` if you would like the Promotion to have a different validity period than the Promotion Program's default. Note that if specified, these values still need to be within the range set by the Promotion Program.

Here is a sample request and response call: 

```
POST https://api.lightrail.com/v1/cards/{cardId}/valueStores
{
  "userSuppliedId": "72xx81",
  "programId": "program-53xx36",
  "currency": "USD",
  "initialValue": 150
}
```

The response object to this call includes a server-generated `valueStoreId` which you can persist and use in other operations regarding the Value Store such as _cancelling_ or _freezing_ it.

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

Now that the Promotion is attached to the Account, its value will be added to the Account's available credit and will be automatically used for any transactions that meet the criteria set in the Promotion Program. 

### Cancelling, Freezing and Unfreezing a Value Store

You can freeze/unfreeze a Promotion or cancel it altogether. Freezing and unfreezing are normally used for investigating cases of potential fraud.

Required parameters: 
- The `cardId` of the Account Card to which the Promotion is attached 
- The `valueStoreId` of the Value Store representing the Promotion
- A `userSuppliedId` to ensure idempotency

Here are sample requests for these actions:

```
POST https://api.lightrail.com/v1/cards/{cardId}/valueStores/{valueStoreId}/freeze
{
  "userSuppliedId": "case-234"
}

POST https://api.lightrail.com/v1/cards/{cardId}/valueStores/{valueStoreId}/unfreeze
{
  "userSuppliedId": "case-324-resolved"
}

POST https://api.lightrail.com/v1/cards/{cardId}/valueStores/{valueStoreId}/cancel
{
  "userSuppliedId": "case-872"
}
```

 The response will be a transaction object which includes a `transactionId` for reference and also provides a `value` to report the value of the funds affected by the call. Here is an example: 

```json
{
  "transaction": {
    "transactionId": "transaction-7fxx89",
    "value": -50,
    "userSuppliedId": "case-872",
    "dateCreated": "2017-06-05T16:39:06.679Z",
    "transactionType": "CANCELLATION",
    "transactionAccessMethod": "CARDID",
    "valueAvailableAfterTransaction": 0,
    "giftbitUserId": "user-50xx93",
    "codeLastFour": "NKNA",
    "cardId": "card-fbxxda",
    "currency": "USD"
  }
}
```