# Lightrail UI

Lightrail UI is a javascript library that powers Lightrail's Drop-in Gift Card solution. Lightrail UI makes it easy to embed drop-in components into your page, interact with them, and respond to customer activity.

## Overview Example
Here's a basic example of how you can add a Card Purchase Dialog to your page using the Lightrail UI library.

*More info on shopper tokens [here](#drop-in-gift-cards/shopper-tokens).*

```html
<html>
    <head>
        <title>Buy Gift Cards | Rocketship</title>
        
        <!-- Include this script in the header of your page -->
        <script src="https://embed.lightrail.com/dropin/v1/lightrail-ui.js"></script>
        
        <!-- Now that you've included Lightrail UI, it's time to use it. -->
        <script>
            //Create an instance of LightrailUI using a server generated shopper token
            var shopperToken = "{{shopperToken}}";
            var lightrailUI = new LightrailUI(shopperToken);
            
            //Create instance of the Card Purchase Dialog component with a custom background color defined in the options object
            var cardPurchaseDialog = lightrailUI.components.cardPurchaseDialog({theme_bg_primary: "hotpink"});
            
            //Oops lets fix that color
            cardPurchaseDialog.setOptions({theme_bg_primary: "#ccc"});
            
            //Add a handler so we can enable our launch button when the dialog is ready
            cardPurchaseDialog.on("ready", function(){
                var launchButton = document.getElementById("launch-button");
                
                launchButton.setAttribute("disabled", "false");
                launchButton.addEventListener("click", function(){
                    cardPurchaseDialog.open();
                });
            });
            
            //This will load/mount the component once the document is finished loading
            cardPurchaseDialog.mount();
        </script>
    </head>
    <body>
        <div class="launch-container">
            <button id="launch-button" disabled="true">Buy a Gift Card!</button>
        </div>
    </body>
</html>
``` 

## Lightrail UI Object

The Lightrail UI object has the following properties
* [components](#lightrail-ui/components-overview)
  * [cardPurchaseDialog()](#lightrail-ui/card-purchase-dialog)
  * [codeRedemption()](#lightrail-ui/code-redemption)
* [fetchAccountBalance()](#lightrail-ui/fetchaccountbalance)
* [displayAccountBalance()](#lightrail-ui/displayaccountbalance)


### Components Overview

The components namespace (`lightrailUI.components`) contains the methods that allow you to create components.
ie:
```javascript
    var newComponent = lightrailUI.components.newComponent();
```

Each component within the namespace has a common set of methods to create, interact with and handle updates.


#### getOptions
`getOptions()`

This will return an object with the option values that have been set, as well as the available options. 
You can also see a full list of options for each component below in [Component Customization](#lightrail-ui/component-customization)

```javascript
    var options = component.getOptions();
    console.log(options);
```

---

#### setOptions
`setOptions({})`

Set / replace the components options object. These options aren't automatically passed on to the mounted component
so if you use this, you should re-mount the component by calling `unmount()` and `mount()`.

```javascript
    var options = component.setOptions({theme_bg_primary: "#eee"});
    console.log(options);
```

---

#### on
`on(event, handler)`

Allows you attach handlers to specific component events.

```javascript
    component.on("ready", function(){
        componentIsReadyDoACustomThing();
    });
```

Events Dispatched by all components:


| Event  | Description | Sample Response |
| ------------- | ------------- | ------------- |
| "mount" | The component has been mounted successfully and is now loading.  | {} |
| "ready" | The component has finished loading and initializing and is ready for use.  | {} |
| "error" | There was an error loading or processing data.   | {status: 401, data: {type: "", message: "Unauthorized"}} |

---

#### mount
`mount(Element | #id | .classname)`

The mount method adds the component to the page and accepts one param, either an Html Element or string for element lookup using either the `#id`
or `.classname` formats.

Generally mount() should be the last method you call to ensure everything is setup before the component is added to your page.

---

#### unmount
`unmount()`

Unmount will remove the component from the page and clean up internal references. 
It will not remove event listeners added by calling the `on()` method though, we will be adding the ability to remove those separately in the future. 
This way, it is possible to `unmount()` the component and then call `mount()` at a later time without having to redefine options or events.

**Note:** Unmount should be called before removing or nulling an object if the user isn't navigating away from the page.
This is to ensure that any iframes etc added to the page will be cleaned up.

---

### Card Purchase Dialog

The Card Purchase Dialog is a dialog that lets users quickly and securely purchase and email gift cards. 

By default it's hidden and can be opened by calling the `open()` method. 
Alternatively, a launch button can be added to your page by adding the correct properties to the options object. *View [customizaion](#lightrail-ui/gift-card-purchase-theming) for more info*

```javascript
        var cardPurchaseDialog = lightrailUI.components.cardPurchaseDialog({theme_bg_primary: "#ccc"});

        cardPurchaseDialog.on("ready", function(error){
            cardPurchaseDialog.open();
        });

        cardPurchaseDialog.on("purchaseComplete", function(response){
            startConfetti();
        });
        
        cardPurchaseDialog.on("close", function(error){
           stopConfetti(); 
        });
        
        cardPurchaseDialog.mount();
```

** Card Purchase Events**

| Event  | Description | Sample Response |
| ------------- | ------------- | ------------- |
| "open" | The user hit the submit button and the component is attempting to redeem the users code.  | {} |
| "close" | A previous claim failed and the user hit the Try Again button to re-submit a code.  | {} |
| "purchaseComplete" | This event fires on successful redemption.  | {senderEmail: "user@aol.com", recipientEmail: "user2@aol.com", cardAmountCents: 10000, currency: "USD"} |
| "purchaseError" | There was an error purchasing | {status: 401, data: {type: "", message: "Unauthorized"}} |


### Code Redemption

The Code Redemption Component contains a small form that can be embedded in your page to easily and securely handle code redemption.

It was designed to be hosted at the claim url that is setup in the drop-in config so the fullcode can be passed in and auto-populated for the user. 

Once a user redeems a gift code, you can respond by handling the "redemption" event dispatched by the component. 
Alternatively, you can use the "success_btn_cta_label" and "success_btn_cta_href" params to setup a redirect button within the component.

```javascript
        var options = {fullcode: 1235813};
        var codeRedemption = lightrailUI.components.codeRedemption(options);

        codeRedemption.on("redemption", function(response){
            writeConfimationToScreen(response.cardAmountCents, currency);
            updateAccountBalanceDisplay(response.accountBalanceCents, currency);
        });
        
        cardPurchaseDialog.mount("#redemption-container");
```

**Code Redemption Events**

| Event  | Description | Sample Response |
| ------------- | ------------- | ------------- |
| "submit" | The user hit the submit button and the component is attempting to redeem the users code.  | {} |
| "tryAgain" | A previous claim failed and the user hit the Try Again button to re-submit a code.  | {} |
| "redemption" | Successful redemption.  | {cardAmountCents: 1000, accountBalanceCents: 2000, formattedCardAmount: $10, formattedAccountBalance: $20, currency: "USD"} |


### fetchAccountBalance

LightrailUI(shopperToken).fetchAccountBalance(handler) allows you to fetch the account balance for the user connected with the shoppertoken. *View [customizaion](#lightrail-ui/gift-card-purchase-theming) for more info*

Usage:
```javascript
        lightrailUI.fetchAccountBalance(function(response){
            var balanceInCents = response.balanceInCents;
            var currency = response.currency;
            var formattedBalance = response.formattedBalance;
        });
```

**Note:** We do some very basic formatting to produce the formattedBalance property right now, if you want to support other currency symbols or custom formatting please use the balanceInCents value along with currency to format the value.


### displayAccountBalance

LightrailUI(shopperToken).displayAccountBalance(Element | id | classname) will write the formattedValue to an element in your page.

ie:
```javascript
    lightrailUI.displayAccountBalance("#account-balance");

    //ie: <p>Balance: <span id="account-balance">$50</span> </p>
```

## Component Customization

This section outlines how to customize **Gift Card Purchase Dialog** and **Code Redemption** Components created by **Lightrail**. 

### Theme properties

The drop in components are customizable via options passed in when you create your component. Each property should be a string color value or other CSS property like `padding`.

Values can be passed in as follows: 

- **Hex value** - `#FFF`, `#ffffff`, `#000000` (3 or 6 characters plus pound symbol)
- **Named color** - `black`, `white`, `firebrick`, `goldenrod`
- **rgb(a)** - `rgba(255, 255, 255, 0.2)`
- **Padding** - `theme_padding="50px 10px 1em 0"`

Should you have any questions regarding theming, please contact hello@lightrail.com

### Gift Card Purchase Theming

**Example:**

```javascript
    var options = {
        theme_bg_primary: "#FF0",
        theme_btn_color_gift_amount_hover: "white"
    };

    var cardPurchseDialog = lightrailUI.components.cardPurchaseDialog(options);
    cardPurchseDialog.mount();
```

Below is a comprehensive list of values accepted by the **Card Purchase Dialog Component**. 

1. `theme_bg_primary`
2. `theme_bg_secondary`
3. `theme_color_primary`
4. `theme_color_secondary`
5. `theme_btn_bg_primary`
6. `theme_btn_color_primary`
7. `theme_btn_bg_primary_disabled`
8. `theme_btn_color_primary_disabled`
9. `theme_btn_bg_secondary`
10. `theme_btn_color_secondary`
11. `theme_btn_bg_gift_amount`
12. `theme_btn_color_gift_amount`
13. `theme_btn_bg_gift_amount_hover`
14. `theme_btn_color_gift_amount_hover`
15. `theme_btn_bg_gift_amount_select`
16. `theme_btn_color_gift_amount_select`
17. `theme_btn_bg_back`
18. `theme_btn_color_back`
19. `theme_error_color`
20. `theme_text_color`
21. `theme_input_color`
22. `theme_label_color`
23. `theme_stripe_placeholder_color`
24. `launch_btn_container: "#launch-button-container"`
25. `launch_btn_label: "Give a Gift"`
26. `launch_btn_classname: "rocketship-button__small"`

_*All overrides are optional_

#### Gift Card Purchase Theming - 1
![Gift card purchase theming - 1](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/gift-card-purchase-theming-1.png)

#### Gift Card Purchase Theming - 2
![Gift card purchase theming - 2](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/gift-card-purchase-theming-2.png)

#### Gift Card Purchase Theming - 3
![Gift card purchase theming - 3](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/gift-card-purchase-theming-3.png)

#### Gift Card Purchase Theming - 4
![Gift card purchase theming - 4](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/gift-card-purchase-theming-4.png)

**Note:** #24 and #25 are special in the sense that you are able to pass in a label for the button text (#24), and if one isn’t provided, we default to "Buy a Gift Card". #25 accepts a button class name that you already use locally in your site, or you can define one to control the look of the button.

### Code Redemption Theming

**Example:**

```javascript
    var options = {
        fullcode: "EMAIL_URL_PARAM_VALUE",
        theme_bg_primary: "#FF0",
        theme_btn_color_gift_amount_hover: "white"
    };

    var codeRedemption = lightrailUI.components.codeRedemption(options);
    codeRedemption.mount("#code-redemption-container");
````

Below is a comprehensive list of values accepted by the **Code Redemption Component**.

1. `theme_bg_primary`
2. `theme_padding`
3. `theme_form_align_bottom (default “absolute” to align to the bottom)`
4. `theme_text_color_header`
5. `theme_text_color_subheader`
6. `theme_input_border_color`
7. `theme_input_bg_color`
8. `theme_input_color`
9. `theme_btn_bg_primary`
10. `theme_btn_color_primary`
11. `theme_btn_bg_primary_hover`
12. `theme_btn_color_primary_hover`
13. `theme_btn_bg_secondary`
14. `theme_btn_color_secondary`
15. `theme_btn_bg_secondary_hover`
16. `theme_btn_color_secondary_hover`
17. `success_btn_cta_label: "Start Shopping Today"`
18. `success_btn_cta_href: "landingpage"`
 
![Code redemption theming - 3](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/code-remption-theming-1.png)

**Note:** #17 provides a label to pass for the completed redemption step. #18 is the the href for where the button will redirect users on redemption completion.

## Support
Looking for an example? Check out our [sample app](https://github.com/Giftbit/stripe-integration-sample-webapp) which is a working example of the entire Drop-in Gift Card solution.

Contact us any time at hello@lightrail.com —- we are here to help.
