# Lightrail Documentation
Welcome to Lightrail. Lightrail is a platform for managing value.

Lightrail offers a Drop-in Gift Card solution along with providing the functionality to create custom account credit and gift card integrations. Lightrail enables you to create promotions on-top of your account credit or gift card programs allowing you to build long lasting relationships with your customers.

Contact us anytime at hello@lightrail.com —we are here to help you solidify your use-case and implementation.

You're looking at the full reference documentation providing detailed information on each endpoint. 
Many integrations can be done using only our client libraries and the quick starts below.   

## Common Use Cases - Quick Starts
We recommend starting with the following quick start guides. 

### Drop-in Gift Cards
The Drop-in Gift Card solution empowers you to offer gift cards from your site in days. It is component based, using simple HTML snippets.
When a customer redeems a gift card, the value is applied to an account that belongs to the customer, also managed by Lightrail.
Minor updates must be made to your checkout process to allow the customer's account to be used as a payment option.

Find out more here.

### Accounts
Apply gift cards and points directly to customer accounts in Lightrail. 
Create a seamless purchase experience for your customer.
Check out our accounts documentation.

With customer accounts in place you can learn from their purchase history and drive engagement using promotions.

### Promotions
Lightrail promotions are rule-based value that are attached to gift cards or accounts.
Promotions can be used to incentive when customers by, along with what they buy.
For example, a promotion could be a _$10 off boxing week sale_ or _$5 off if your purchase is over $25_; the possibilities are endless.  

Find out more here.

## Getting Started
[Sign up](https://www.lightrail.com/app/#/register) for a Lightrail account. 

### API Keys and Authorization
Create your API key in the [Integrations](https://www.lightrail.com/app/#/account/api) section of your account. 

To make an API call Lightrail requires the HTTP header `Authorization: Bearer {{API_KEY}}`.

#### Base URL
The base URL for the Lightrail API is `https://api.lightrail.com/v1/`.

#### First Call
To test your authorization use our `ping` endpoint.

```curl https://api.lightrail.com/v1/ping --header "Authorization: Bearer <apiKey>"```

### Testing 
When you first sign into your account you will be in test mode. Test mode is used for development allowing you to build in and test functionality. It comes with sample data to give you a sense of what your Lightrail account will look like. 

## Support
We have [client libraries](https://github.com/Giftbit/Lightrail-API-Docs/blob/master/docs/client-libraries.md#client-libraries) in many languages to speed up your integration. 
We also have a [sample](https://github.com/Giftbit/stripe-integration-sample-webapp) Drop-In Gift Card application containing examples in JavaScript and PHP with Java and Ruby coming soon.   

Please contact us any time, we're here to help.
