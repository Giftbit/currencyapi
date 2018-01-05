# Welcome

Welcome to Lightrail. Lightrail is a platform for managing value.

Lightrail offers a [Drop-in Gift Card solution](#drop-in-gift-cards/drop-in-gift-cards) and also provides the functionality to create custom account credit and gift card integrations. Lightrail enables you to create promotions on top of your account credit or gift card programs allowing you to build long lasting relationships with your customers.

Contact us anytime at hello@lightrail.com — we are here to help you solidify your use-case and implementation.
 
We recommend beginning with our quickstarts below as they support many integrations and utilize our [client libraries](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/client-libraries.md#client-libraries). For more complex integrations, see our full [reference documentation](https://www.lightrail.com/docs/reference).

## Common Use Cases - Quickstarts
We recommend starting with the following quickstart guides. 

#### Drop-in Gift Cards
The [Drop-in Gift Card solution](#drop-in-gift-cards/drop-in-gift-cards) empowers you to offer gift cards from your site in days. It is component based, using simple HTML snippets. When a customer redeems a gift card, the value is applied to an account that belongs to the customer, also powered by Lightrail. Minor updates must be made to your checkout process to allow the customer's account to be used as a payment option.

To get started check out our [Drop-in Gift Card quickstart](#drop-in-gift-cards/quickstart).

#### Accounts
Use Lightrail to apply gift cards and points directly to customer accounts. This allows you to create a seamless purchase experience for your customers.

Check out our [accounts documentation](#accounts/accounts) to get started.

With customer accounts in place you can learn your customers' purchase history and drive engagement using promotions.

#### Promotions
A Lightrail promotion is rule-based value that is attached to gift cards or accounts. Promotions can be used to incentivize customers "when" to buy, along with "what" to buy. For example, a promotion could be a _$10 off boxing week sale_ or _$5 off if your purchase is over $25_; the possibilities are endless.

Check out our [promotions documentation](#promotions/promotions) for more information.

## Getting Started
[Sign up](https://www.lightrail.com/app/#/register) for a Lightrail account. 

#### API Keys
Create your API key in the [Integrations](https://www.lightrail.com/app/#/account/api) section of your account. 

#### Client Library Authorization
If using a client library, you'll need to pass your Lightrail API key in. Authorization details for each library is explained in the library's README. 

#### API Authorization
To make an API call Lightrail requires the HTTP header `Authorization: Bearer {{API_KEY}}`.

#### Testing
When you first sign into your account you will be in test mode. Test mode is used for development allowing you to build in and test functionality. It comes with sample data to give you a sense of what your Lightrail account will look like. 

## Support
We have [client libraries](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/client-libraries.md#client-libraries) in many languages to speed up your integration. We also have a sample [Drop-In Gift Card application]((https://github.com/Giftbit/stripe-integration-sample-webapp)) containing examples in JavaScript and PHP with Java and Ruby coming soon.   

Please [contact us](mailto:hello@lightrail.com) any time, we're here to help.
