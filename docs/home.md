# Lightrail Documentation
Welcome to Lightrail. Lightrail is a platform for managing digital value.

Lightrail offers a Drop-in Gift Card solution along with providing the functionality to create custom account credit and gift card integrations. Lightrail enables you to create promotions on-top of your account credit or gift card programs allowing you to build long last relationships with your customers.

Contact us anytime at hello@lightrail.com —we are here to help you solidify your use-case and implementation.

## Drop-in Gift Cards
The Drop-in Gift Card solution empowers you to offer gift cards from your site in days. It is component based, using simple HTML snippets.
When a customer redeems a gift card, the value is applied to an account that belongs to the customer, also managed by Lightrail.
Minor updates must be made to your checkout process to allow the customer's account to be used as a payment option.

Find out more here.

## Accounts
Apply gift cards and points directly to customer accounts in Lightrail. 
Create a seamless purchase experience for your customer.
Learn from their purchase history and drive user engagement using promotions.

Check out our accounts documentation.

## Promotions
Lightrail promotions are rule-based value that are attached to gift cards or accounts.
A promotion could be _$5 off if your purchase is over $25_, or _$10 off if your order contains two pairs of jeans_; the possibilities are endless. 
Promotions allow you to increase customer acquisition and retention. 
By utilizing Lightrail filter and segment capabilities you're able to created targeted incentives programs, allowing you to pinpoint the customer's that mean the most to you. 

Find out more here.

## Within Your Lightrail Account
All of your gift cards, accounts and promotions can be viewed through your Lightrail account. Effortlessly locate an account or giftcard to view its transaction history. 

This enables an out-of-the-box customer service solution for your team.  

### Segment Actions
Your Lightrail account offers powerful search and filter functionality. 
Filter for customer's with certain purchase behaviors and create tagged segments. You can preform segment actions that apply to all gift cards or accounts in that segment. Examples of such actions can be to add a promotion to incentive that segment, or to temporarily freeze the objects, preventing further transactions.      

## Getting Started
[Sign up](https://www.lightrail.com/app/#/register) for a Lightrail account. 

### Test Mode
When you first sign into your account you will be in test mode. Test mode is used for development allowing you to build in safely. It comes with sample data to give you a sense of what your Lightrail account will look like with data and allows you to try out features such as the search and filter capabilities. 

### API Keys and Authorization
Create your API key in the [Integrations](todo get link) section of your account. 

To make an API call Lightrail requires the HTTP header `Authorization` with value `Bearer {{API_KEY}}`.

#### Base URL
The base URL for the Lightrail API is `https://api.lightrail.com/v1/`.

#### First Call
```curl https://www.lightrail.com/v1/ping --header "Authorization: Bearer <apiKey>"```

## Support
- We have sample projects
- We have client-libraries
- Contact us

