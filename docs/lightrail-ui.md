# LightrailUI

Lightrail UI is a javascript library that powers our Drop-in Gift Card solution. LightrailUI makes it easy to embed drop-in components into your page, interact with them, and respond to customer activity.

* [LightrailUI Object] (###ligtrailui-object)
* Components
* 

### LightrailUI

#### Including LightrailUI
To get started using LightrailUI, add the following script to the `<head>` of your pages.
```html
    <script src="https://embed.lightrail.com/dropin/v1/lightrail-ui.js"></script>
```

You can then start using LightrailUI by creating an instance of the LightrailUI object using a server generated [shopper token](#drop-in-gift-cards/shopper-tokens).
```javascript
        var shopperToken = "{shoppertoken}";
        var lightrailUI = new LightrailUI(shopperToken);
```

#### LightrailUI Object
* [components](#lightrail-ui/lightrail-ui/components)
  * [cardPurchaseDialog](#lightrail-ui/lightrail-ui/cardPurchasedialog)
  * [codeRedemption]()
* [getAccountBalance](#lightrail-ui/getaccountbalance(handler))
* [writeAccountBalance](#lightrail-ui/writeaccountbalanceelementidclass)


#### Components

The components namespace contains methods that allow you to create component objects. 
You can then add components to your page, interact with them, and handle updates.

##### Common Component methods
Each component uses the following methods.

##### getOptions()
This will return an object with the option values that have been set, as well as the available options. 
You can also see a full list of options for each component below in [Component Customization](#lightrail-ui/lighrail-ui/component-customization)

##### setOptoins(options)
This set / replace any previously set options in the component. Note, these options aren't automatically passed on to the mounted component.
If you change options you should unmount and re-mount the component, ie: `unmount()` `mount()`

##### on(event, handler)
This allows you to respond to component events with a callback handler function

Common Events Dispatched are 

| event  | description | sample response |
| ------------- | ------------- | ------------- |
| "mount" | The component has been mounted successfully and is now loading.  | {} |
| "ready" | The component has finished loading and initializing and is ready for use.  | {} |
| "error" | There was an error loading or processing data.   | {status: 401, data: {type: "", message: "Unauthorized"}} |



###### mount(element)
The `mount()` method mounts the component and accepts one param, either an Html Element or string using either the `#id`
or `.classname` formats.

Generally mount() should be the last method you call to ensure everything is setup before the component is added to your page.

###### unmount()
Unmount will remove the component from the page and clean up any instances, it is possible to recall mount() on an object to re-add it after.

##### cardPurchaseDialog

The CardPurchase Object 

```javascript
        var options = {};
        var cardPurchaseDialog = lightrailUI.components.cardPurchaseDialog(options);

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

Custom Events

| event  | description | sample response |
| ------------- | ------------- | ------------- |
| "open" | The user hit the submit button and the component is attempting to redeem the users code.  | {} |
| "close" | A previous claim failed and the user hit the Try Again button to re-submit a code.  | {} |
| "purchaseComplete" | This event fires on successful redemption.  | {senderEmail: "user@aol.com", recipientEmail: "user2@aol.com", cardAmountCents: 10000, currency: "USD"} |
| "purchaseError" | There was an error purchasing | {status: 401, data: {type: "", message: "Unauthorized"}} |


##### codeRedemption

The Code Redemption Component is a small form that can be embedded in your redemption page to easily and securely handle the redemption process.
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

Custom Events

| event  | description | sample response |
| ------------- | ------------- | ------------- |
| "submit" | The user hit the submit button and the component is attempting to redeem the users code.  | {} |
| "tryAgain" | A previous claim failed and the user hit the Try Again button to re-submit a code.  | {} |
| "redemption" | Successful redemption.  | {cardAmountCents: 1000, accountBalanceCents: 2000, formattedCardAmount: $10, formattedAccountBalance: $20, currency: "USD"} |

#### getAccountBalance

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


#### writeAccountBalance

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
24. `launch_btn_label="Give a Gift"`
25. `launch_btn_classname="rocketship-button__small"`

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
17. `success_btn_cta_label="Start Shopping Today"`
18. `success_btn_cta_href="landingpage"`
 
![Code redemption theming - 3](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/code-remption-theming-1.png)

**Note:** #17 provides a label to pass for the completed redemption step. #18 is the the href for where the button will redirect users on redemption completion.

## Support
Looking for an example? Check out our [sample app](https://github.com/Giftbit/stripe-integration-sample-webapp) which is a working example of the entire Drop-in Gift Card solution.

Contact us any time at hello@lightrail.com —- we are here to help.
