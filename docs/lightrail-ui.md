# LightrailUI

Lightrail UI is a javascript library that powers our Drop-in Gift Card solution. LightrailUI makes it easy to embed drop-in components into your page, interact with them, and respond to customer activity.

## LightrailUI Overview
To get started using LightrailUI, add the following script to the `<head>` of your pages.
```html
    <script src="https://embed.lightrail.com/dropin/v1/lightrail-ui.js"></script>
```

You can then start using LightrailUI by creating an instance of the LightrailUI object using a server generated [shopper token](#drop-in-gift-cards/shopper-tokens).
```javascript
        var shopperToken = "{shoppertoken}";
        var lightrailUI = new LightrailUI(shopperToken);
```

Next you can instantiate a component or call a method to perform an action. 
Here's an example of adding the Card Purchase Dialog to your page and opening it when ready.
```javascript
        var shopperToken = "{shoppertoken}";
        var lightrailUI = new LightrailUI(shopperToken);
        
        var cardPurchaseDialog = lightrailUI.components.cardPurchaseDialog({theme_bg_primary: "#aaa"});
        cardPurchaseDialog.on("ready", function(error){
            cardPurchaseDialog.open();
        });
        cardPurchaseDialog.mount();
```


## LightrailUI Object

The LightrailUI object has the following properties
* [components](#lightrail-ui/lightrail-ui/components)
  * [cardPurchaseDialog()](#lightrail-ui/lightrail-ui/cardPurchasedialog)
  * [codeRedemption()]()
* [getAccountBalance()](#lightrail-ui/getaccountbalance(handler))
* [writeAccountBalance()](#lightrail-ui/writeaccountbalanceelementidclass)


### Components Overview

The components namespace contains the methods that allow you to create components. 
Each component within the namespace has a common set of methods to create, interact with and handle updates.

Each component uses the following methods.

#### getOptions
`setOptions()`

This will return an object with the option values that have been set, as well as the available options. 
You can also see a full list of options for each component below in [Component Customization](#lightrail-ui/lighrail-ui/component-customization)

```javascript
    var options = component.getOptions();
    console.log(options);
```

#### setOptions
`setOptions({})`

Set / replace the components options object. These options aren't automatically passed on to the mounted component
so if you use this, you should re-mount the component by calling `unmount()` and `mount()`.

```javascript
    var options = component.setOptions({theme_bg_primary: "#eee"});
    console.log(options);
```

#### on
`on(event, handler)`

Allows you attach handlers to specific component events.

```javascript
    component.on("ready", function(){
        onComponentReady();
    });
```

Events Dispatched by all components:


| Event  | Description | Sample Response |
| ------------- | ------------- | ------------- |
| "mount" | The component has been mounted successfully and is now loading.  | {} |
| "ready" | The component has finished loading and initializing and is ready for use.  | {} |
| "error" | There was an error loading or processing data.   | {status: 401, data: {type: "", message: "Unauthorized"}} |



#### mount
`mount(Element | #id | .classname)`

The mount method adds the component to the page and accepts one param, either an Html Element or string for element lookup using either the `#id`
or `.classname` formats.

Generally mount() should be the last method you call to ensure everything is setup before the component is added to your page.

#### unmount()
Unmount will remove the component from the page and clean up internal references. 
It will not remove event listeners added by calling the `on()` method though, we will be adding the ability to remove those separately in the future. 
This way, it is possible to unmount() the component and then call mount() at a later time without having to re-define options or events.

**Note:** Un-mount should be called before removing or nulling an object if the user isn't navigating away from the page.
This is to ensure that any iframes etc added to the page will be cleaned up.


### Card Purchase Dialog

The Card Purchase Dialog is a dialog that lets users quickly and securely purchase and email gift cards. 
When open, it acts as an overlay on-top of the page. 
It can be opened via code, or a launch button can be added to the page with a custom class and label by adding the correct properties to the options object.

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

**Events**

| event  | description | sample response |
| ------------- | ------------- | ------------- |
| "open" | The user hit the submit button and the component is attempting to redeem the users code.  | {} |
| "close" | A previous claim failed and the user hit the Try Again button to re-submit a code.  | {} |
| "purchaseComplete" | This event fires on successful redemption.  | {senderEmail: "user@aol.com", recipientEmail: "user2@aol.com", cardAmountCents: 10000, currency: "USD"} |
| "purchaseError" | There was an error purchasing | {status: 401, data: {type: "", message: "Unauthorized"}} |


### Code Redemption

The Code Redemption Component is a small form that can be embedded in your redemption page to easily and securely handle code redemption.
It was designed to be hosted at the claim url that is setup in the drop-in config. Then the fullcode param can be passed into the components options object, 
and auto-populated for the user. Once a user redeems their gift code, you can take action by handling the "redemption" event, or use the "success_btn_cta_label" and "success_btn_cta_href" params to setup a redirect button within the components success state.

```javascript
        var options = {fullcode: 1235813};
        var codeRedemption = lightrailUI.components.codeRedemption(options);

        codeRedemption.on("redemption", function(response){
            writeConfimationToScreen(response.cardAmountCents, currency);
            updateAccountBalanceDisplay(response.accountBalanceCents, currency);
        });
        
        cardPurchaseDialog.mount("#redemption-container");
```

**Events**

| event  | description | sample response |
| ------------- | ------------- | ------------- |
| "submit" | The user hit the submit button and the component is attempting to redeem the users code.  | {} |
| "tryAgain" | A previous claim failed and the user hit the Try Again button to re-submit a code.  | {} |
| "redemption" | Successful redemption.  | {cardAmountCents: 1000, accountBalanceCents: 2000, formattedCardAmount: $10, formattedAccountBalance: $20, currency: "USD"} |

### getAccountBalance

LightrailUI(shopperToken).getAccountBalance(handler) allows you to fetch the account balance for the logged-in user connected with the shoppertoken.

Usage:
```javascript
        lightrailUI.getAccountBalance(function(response){
            var balanceInCents = response.balanceInCents;
            var currency = response.currency;
            var formattedBalance = response.formattedBalance;
        });
```

**Note:** We do some very basic formatting to produce the formattedBalance property right now, if you want to support other currency symbols or custom formatting please use the balanceInCents value along with currency to format the value.


### writeAccountBalance

LightrailUI(shopperToken).writeAccountBalance(element | id | classname) will fetch the account balance for the user link to the shoppertoken and write the formattedValue to an element in your page.

ie:
```javascript
    lightrailUI.writeAccountBalance("#account-balance");

    //ie: <p>Balance: <span id="account-balance">$50</span> </p>
```


## Component Customization

This document outlines how to customize **Gift Card Purchase Dialog** and **Code Redemption** Components created by **Lightrail**. 

### Theme properties

The drop in components are customizable via options passed in when your create your component. Each property should be a string color value or other CSS property like `padding`.

Values can be passed in as follows: 

- **Hex value** - `#FFF`, `#ffffff`, `#000000` (3 or 6 characters plus pound symbol)
- **Named color** - `black`, `white`, `firebrick`, `goldenrod`
- **rgb(a)** - `rgba(255, 255, 255, 0.2)`
- **Padding** - `theme_padding="50px 10px 1em 0"`

Should you have any questions regarding themeing, please contact hello@lightrail.com

### Gift Card Purchase Theming

**Example: **

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

**Example: **

```javascript
    var options = {
        fullcode: "EMAIL_URL_PARAM_VALUE"
        theme_bg_primary: "#FF0"
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
