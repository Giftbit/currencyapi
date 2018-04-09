# Drop-in Gift Cards  

## Quickstart
Lightrail's Drop-in Gift Card solution makes it easy to offer gift cards to your customers. 
Integrating into Lightrail is an easy process that can be completed in no more than a few hours.

The solution is component based, using elements generated from our client-side library Lightrail UI.
When your customers receive a gift card, they can easily redeem and apply the gift card value to their account.
Your checkout process requires a simple update to accept payment from your customer accounts.

### Getting Started
[Sign up](https://www.lightrail.com/app/#/register) for a Lightrail account. 

Note this quickstart assumes you are using Stripe to process payments: if you are using another payment processor and want to build a custom solution, please [contact us](mailto:hello@lightrail.com).

We have preconfigured sample values for your [Drop-in Gift Card template](https://www.lightrail.com/app/#/cards/dropin) as a demo to help you get testing quickly. You can use all the default values to start, but if you are interested in running through an active test that includes a sample customer Gift Card redemption, you will need to update the **Email Claim Link** value in order to set up a testing url which you have access to. Please note: before taking your Drop-in components to production, you will want to be sure to update all the values to match your custom implementation.

You can optionally edit the other configuration values of your Drop-in [template](https://www.lightrail.com/app/#/cards/dropin) within your Lightrail account to customize the appearance of components and gift card emails.
Note: For development, toggle your Lightrail account to test mode, this will allow you to use [Stripe's test credit cards](https://stripe.com/docs/testing#cards).

Also, before going live or to see test Stripe charges show up in your Stripe account, you'll need to connect your Stripe account on your account's integrations [page](https://www.lightrail.com/app/#/account/api). 

If at any point you want to see a working example of the entire Drop-in Gift Card solution, check out our [sample app](https://github.com/Giftbit/stripe-integration-sample-webapp).

### Authentication
Create your Lightrail API key from the [Integrations](https://www.lightrail.com/app/#/account/api) section of your Lightrail account. 
Your Lightrail API key is used to complete the server side requests from checkout, and also to generate Shopper Tokens which are passed into the Lightrail UI library for component authentication.  

#### Shopper Tokens
Shopper Tokens act like customer-specific API tokens to be used client-side in the drop-in components. 
They are based on a unique customer identifier from your e-commerce system: the `shopperId`. This is what links the customer from your system to their account in Lightrail. 

Shopper Tokens must be generated server side using one of our [client libraries](https://www.lightrail.com/docs/#client-libraries/client-libraries). (If you are working in a language that we don't currently offer a client library for, please [contact us](mailto:hello@lightrail.com) to discuss creating your own tokens.) 

You'll also need your shared secret key from the [Integrations](https://www.lightrail.com/app/#/account/api) section of your account.  

##### Generating a Shopper Token:

First install the client library in the language of your choice:
```javascript
npm install lightrail-client
```

```php
composer require lightrail/lightrail
```

```java
<!-- add to your maven POM file -->
<dependency>
  <groupId>com.lightrail</groupId>
  <artifactId>lightrail-client</artifactId>
  <version>2.0.1</version>
</dependency>
```

```ruby
gem install lightrail_client
```

Creating a Shopper Token in server side code (this will be passed to the client-side components in Steps 1, 2, & 3): 
```javascript
const lightrail = require("lightrail-client");
 
lightrail.configure({
    apiKey: process.env.LIGHTRAIL_API_KEY,
    sharedSecret: process.env.LIGHTRAIL_SHARED_SECRET
});
const shopperToken = lightrail.generateShopperToken({shopperId: "customer-id-from-your-system"});
```

```php
// if using an autoload import statement the Lightrail library will load automatically

\Lightrail\Lightrail::$apiKey = getenv("LIGHTRAIL_API_KEY");
\Lightrail\Lightrail::$sharedSecret = getenv("LIGHTRAIL_SHARED_SECRET");
$shopperToken = \Lightrail\LightrailShopperTokenFactory::generate(array("shopperId" => "customer-id-from-your-system"));
```

```java
import com.lightrail.*

// NOTE the Java client is currently inconsistent with other client libraries, it will be updated soon. 
// For shopper token creation, pass in the shopperId from your system and the validity of the token in seconds
Lightrail.apiKey = System.getenv("LIGHTRAIL_API_KEY");
Lightrail.clientSecret = System.getenv("LIGHTRAIL_SHARED_SECRET");
String shopperToken = LightrailClientTokenFactory.generate("customer-id-from-your-system", 600);
```

```ruby
require 'lightrail_client'

Lightrail.api_key = ENV["LIGHTRAIL_API_KEY"]
Lightrail.shared_secret = ENV["LIGHTRAIL_SHARED_SECRET"]
shopper_token = Lightrail::ShopperTokenFactory.generate({shopper_id: "customer-id-from-your-system"})
```

Note, usage of Lightrail UI components must be on authenticated pages as it requires a `shopperId`.
You may decide whether you'd like your customers to be signed in to purchase gift cards. 
If you'd like to allow gift card purchase from an unauthenticated page simply generate a Shopper Token with `shopperId: ""`. 

Also, you can enable customers to pay using saved credit cards. See [below](#drop-in-gift-cards/enable-your-customers-to-pay-using-saved-credit-cards) details. 

### Step 1: Selling Gift Cards
The Gift Card Purchase Component allows your customers to purchase gift cards from your site. 
Lightrail powers the entire gift card purchase and delivery flow. 

![Gift Card Purchase Component](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/purchase-widget.png)

What you see here is our fictional brand called Rocketship. Once set up with our Drop-in solution, you will see your branding instead.

Adding the Gift Card Purchase Component:
```html
<html>
    <head>
        <!-- Include the Lightrail UI javascript library in your page header -->
        <script src="https://embed.lightrail.com/dropin/v1/lightrail-ui.js"></script>
    </head>
    <body>
        <div id="launch-button-container">
            <!-- Container to inject launch button into -->
        </div>
        <script>
            // Server generated shopperToken
            var shopperToken = "{{shopperToken}}";
            var lightrailUI = new LightrailUI(shopperToken);
        
            //Tell the component to inject a launch button in #launch-button-container
            var options = {
               launch_btn_container: "#launch-button-container",
               launch_btn_label: "Buy Gift Card",
               launch_btn_classname: "ltrl_button"
            };
            
            //Initialize the component and mount / add it to the page
            var cardPurchaseDialog = lightrailUI.components.cardPurchaseDialog(options);
            cardPurchaseDialog.mount();
        </script>
    </body>
</html>
```

Alternatively, you could use your own button:
```html
    <div class="launch-button-container">
        <button id="buy-card-launch-button">Buy Gift Card</button>
    </div>
    <script>
        //...
        document.getElementById("buy-card-launch-button").addEventListener("click", function(){
            cardPurchaseDialog.open();
        });
    </script>
```

The gift card is automatically delivered to the recipient in a branded email. The email includes a button to apply the gift card to the recipient's account.

Note you will need to pass a server-generated Shopper Token into the above snippet - details [above](#drop-in-gift-cards/shopper-tokens). 

#### Enable Your Customers to Pay Using Saved Credit Cards
Allow your customers to pay with the credit cards you've saved for them in Stripe. This will give the customer the option to pay using an existing card, if they have one, or to enter a new one. 

<br/>

![Purchase Using Saved Credit Card](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/saved-credit-cards.png)

To enable this, you need to *provide the customer ID from Stripe* when you generate the Shopper Token. 

How to pass in the `stripeCustomerId` during Shopper Token generation to enable the use of saved credit cards:
```javascript
const options = {
    metadata: {stripeCustomerId: "cus_abcd1234"}
};
const shopperToken = lightrail.generateShopperToken({shopperId: "customer-id-from-your-system"}, options);

```

```php
$shopperToken = \Lightrail\LightrailShopperTokenFactory::generate(array("shopperId" => "customer-id-from-your-system"), array("metadata" => array("stripeCustomerId" => "cus_abcd1234")));
```

```java
// Not yet supported. Please let us know at hello@lightrail.com if you require this.  
```

```ruby
shopper_token = Lightrail::ShopperTokenFactory.generate({shopper_id: "customer-id-from-your-system"}, {metadata: {stripeCustomerId: "cus_abcd1234"}})
```

### Step 2: Redeeming Gift Cards

The Gift Card Redemption Component enables your customers to redeem gift cards to their account.

<br/>

![Gift Card Redemption Component](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/redemption-widget.png)

When the recipient clicks the "apply to account" button in the email, they are taken to the redemption page indicated in your [Drop-in template](https://www.lightrail.com/app/#/cards/template).

Adding the Gift Card Redemption Component:
```html
<html>
    <head>
        <!-- Include the Lightrail UI javascript library in your page header -->
        <script src="https://embed.lightrail.com/dropin/v1/lightrail-ui.js"></script>
    </head>
    <body>
            <div id="redemption-component"></div>
            
            <!-- Then add the following snippet to your redemption page -->
            <script>
                // Server generated shopperToken
                var shopperToken = "{{shopperToken}}";
                var lightrailUI = new LightrailUI(shopperToken);
    
                //Initialize our redemption component with a fullcode param so that it's auto-populated for the user
                //It's a good idea to ensure your sites log-in / registration flows won't break this
                var fullcodeValue = getUrlParamValue("fullcode");
                var options = {
                    fullcode: fullcodeValue
                };
                //Create the component and mount it inside of the redemption-component div
                var redemption = lightrailUI.components.codeRedemption(options);
                redemption.mount("#redemption-component");
            </script>
        </body>
</html>
```

When a gift card is redeemed, the Redemption Component applies the gift card amount to the customer's account. If the customer does not have an account in Lightrail already, a new account will be created automatically.

The Redemption Component uses a Shopper Token generated by your system to identify the customer and automatically apply the gift card to their account (see [above](#drop-in-gift-cards/shopper-tokens) for details about Shopper Tokens). Note, ensure your redemption page lives behind the login wall of your site so that the correct Shopper Token is generated by your system to identify the customer. 

Next, your existing checkout process needs to be modified to allow the customer to pay with their account, which now contains the balance of the gift card they received. 

### Step 3: Checkout

#### Fetch and Display Account Balance

Displaying Balances:
```html
<html>
    <head>
        <!-- Include the Lightrail UI javascript library in your page header -->
        <script src="https://embed.lightrail.com/dropin/v1/lightrail-ui.js"></script>
    </head>
    <body>
        <p>Your Account Balance: <span id="account-balance"></span></p>
        <script>
                // Server generated shopperToken
                var shopperToken = "{{shopperToken}}";
                var lightrailUI = new LightrailUI(shopperToken);
                
                //Fetch and Display a simply formatted balance in the account-balance span
                lightrailUI.displayAccountBalance("#account-balance");
                
                //Alternatively, use fetchAccountBalance for more control
                lightrailUI.fetchAccountBalance(function(balance){
                    var formattedBalance = yourCustomFormatter(balance.balanceInCents, balance.currency);
                    document.getElementById("account-balance").innerHTML = formattedBalance;
                });
        </script>
    </body>
</html>
```

This can be used anywhere it makes sense to display an account balance to your customer. 
In the checkout, it gives your customer the information to choose whether or not to apply their account credit to their purchase.

In our [sample webapp](https://github.com/Giftbit/stripe-integration-sample-webapp/blob/master/shared/views/checkout.html), the customer simply selects a checkbox to use their account credit.

#### Accept Payment
You will need to add a custom script to your checkout page to apply your customer's account credit to their purchase and accept a secondary payment method (such as a credit card) to cover any remaining balance. 

This script will need to do the following: 

- Let the customer choose whether to use their account credit
- Adjust the split point accordingly: i.e. how much can be charged to their Lightrail account and how much to charge to their credit card
- Call a charge simulation method on your server (see next section)
- Post the charge (see next section)

For an example, we recommend that you take a look at the [checkout page of our sample webapp](https://github.com/Giftbit/stripe-integration-sample-webapp/blob/master/shared/views/checkout.html). The sample checkout handles the logic of splitting the transaction between the customer's account credit and Stripe; it also loads a Stripe Elements form to handle the credit card portion of the payment if needed. (Templating in the example is done with Mustache but is not required.)

#### Lightrail Stripe Clients

Install the Lightrail Stripe client in the language of your choice to handle the split tender transactions:
```javascript
npm install lightrail-stripe
```

```php
composer require lightrail/lightrail-stripe

```

```java
<!-- your maven POM file -->
<dependency>
  <groupId>com.lightrail</groupId>
  <artifactId>lightrail-stripe-client</artifactId>
  <version>2.0.1</version>
</dependency>
```

```ruby
gem install lightrail_stripe
```

#### Post the Transaction (server side)
The transaction is handled by backend methods using one of our client libraries (or methods that you [write yourself](https://github.com/Giftbit/Lightrail-API-Docs/blob/drop-in-gift-cards/use-cases/stripe-split.md)). You'll need to set up two endpoints to handle submissions from the custom form you added to [accept payment](#drop-in-gift-cards/accept-payment) to: 
1. simulate charges (check the customer's account balance),
1. post the charge and redirect your customer to a success page. 

##### Simulate a Charge / Balance Check
When your customer chooses to use their account credit, you need to see whether the account can cover the requested amount. In our [sample webapp](https://github.com/Giftbit/stripe-integration-sample-webapp/blob/master/shared/views/checkout.html), this is done by having the frontend call a `/simulate` endpoint that makes use of a method from one of our client libraries to simulate posting a charge to a Lightrail account. 


Node example of simulate balance check:
```javascript
/**
 * REST endpoint that simulates the charge and returns JSON.
 */
function simulate(req, res) {
    const splitTenderParams = {
        userSuppliedId: uuid.v4(),
        nsf: false,
        shopperId: req.body.shopperId,
        currency: req.body.currency,
        amount: req.body.amount
    };

    // Try to charge the whole thing to lightrail, and we'll use the amount that would actually get
    // charged when we do the real transaction.
    const lightrailShare = splitTenderParams.amount;

    lightrailStripe.simulateSplitTenderCharge(splitTenderParams, lightrailShare)
        .then(transaction => {
            res.send(transaction.lightrailTransaction);
        })
        .catch(err => {
            // You'll want to actually handle any errors that come back
            console.error("Error simulating transaction", err);
            res.status(500).send("Internal error");
        });
}
```
Use the Lightrail transaction value that comes back to set the parameters for actually posting the charge. 

##### Post the Charge
Our client libraries make it easy to post a split-tender charge where part of the transaction is covered by a Lightrail account, and part is covered by Stripe.  

Node example of how to handle posting a charge:
```javascript
/**
 * REST endpoint that performs the charge and returns HTML.
 */
function charge(req, res) {
    const splitTenderParams = {
        amount: req.body.orderTotal,      // From your cart/order object
        currency: req.body.currency,      // From your cart/order/store config
        source: req.body.source,          // Stripe payment 'source' or 'customer'
        shopperId: req.body.shopperId,    // Lightrail contact identifier; see above
        userSuppliedId: req.body.orderId       // Unique transaction identifier for idempotency
    };

    // Validate the amount to actually charge to Lightrail
    const lightrailShare = req.body.lightrailAmount;
    if (lightrailShare < 0) {
        res.status(400).send("Invalid value for Lightrail's share of the transaction");
    }

    // Use the Lightrail-Stripe integration library to create the split tender charge
    lightrailStripe.createSplitTenderCharge(splitTenderParams, lightrailShare, stripe)
        .then(splitTenderCharge => {
            // Redirect to your success page
            res.render("checkoutComplete.html", {
                lightrailTransactionValue: splitTenderCharge.lightrailTransaction ? splitTenderCharge.lightrailTransaction.value / -100 : 0,
                stripeChargeValue: splitTenderCharge.stripeCharge ? splitTenderCharge.stripeCharge.amount / 100 : 0
            });
        })
        .catch(err => {
            // You'll want to actually handle any errors that come back
            console.error("Error creating split tender transaction", err);
            res.status(500).send("Internal error");
        });
}
```

At this point, the charge has been posted to both Lightrail and Stripe. You can handle post-checkout flow as you otherwise would. 

## Customization and Advanced UI
The appearance of components is fully customizable. Using Lightrail UI you can also attach event listeners to the components to respond to customer activity. For more information on this usage, see [Lightrail UI](#lightrail-ui).

## Support
Looking for an example? Check out our [sample app](https://github.com/Giftbit/stripe-integration-sample-webapp) which is a working example of the entire Drop-in Gift Card solution.

Contact us any time at hello@lightrail.com —- we are here to help.
