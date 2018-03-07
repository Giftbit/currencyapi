# LightrailUI

## Lightrail UI 
Lightrail UI is a javascript library that powers our Drop-in Gift Card solution. LightrailUI makes it easy to embed drop-in components into your page, interact with them, and respond to customer activity.

* [LightrailUI Object] (###ligtrailui-object)
* Components
* 

### Including LightrailUI
To include LightrailUI in your page add the following script to the head of your page.
```html
    <script type="text/javascript" src="https://embed.lightrail.com/dropin/v1/lightrail-ui.js"></script>
```

### LightrailUI(shopperToken)
Use LightrailUI to create an instance of the LightrailUI object with your [shoppertoken](#drop-in-gift-cards/shopper-tokens). Once created you can use LightrailUI to to get a users account balance and add components to your page(s).

The LightrailUI Object
* getAccountBalance()
* writeAccountBalance()
* [components](#lightrail-ui/lightrail-ui/components)
* * cardPurchaseDialog()
* * codeRedemption()

### getAccountBalance(handler)

### writeAccountBalance(element|id|class)

### Components

### cardPurchaseDialog(options)

### codeRedemption(options)


## Drop-in Component Theming

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
<script>
    var lightrailUI = new LightrailUI({shopperToken});
    
    var options = {
        theme_bg_primary: "#FF0",
        theme_btn_color_gift_amount_hover: "white"
    };

    var cardPurchseDialog = lightrailUI.components.cardPurchaseDialog(options);
    cardPurchseDialog.mount();
</script>
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
<script>
    var lightrailUI = new LightrailUI({shopperToken});
    
    var options = {
        fullcode: "EMAIL_URL_PARAM_VALUE"
        theme_bg_primary: "#FF0"
        theme_btn_color_gift_amount_hover: "white"
    };

    var codeRedemption = lightrailUI.components.codeRedemption(options);
    codeRedemption.mount("#code-redemption-container");
</script>
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
