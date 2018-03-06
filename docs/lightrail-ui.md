# LightrailUI

## Quickstart
Lightrail UI powers our Drop-in Gift Card solution, allowing you to embed drop-in components into your page, manage them, and respond to customer activity.

The solution is a javascript library that allows you to quickly and easily integrate and work with our front-end components.

### Getting Started
[Sign up](https://www.lightrail.com/app/#/register) for a Lightrail account. 

Note this quickstart assumes you are using Stripe to process payments: if you are using another payment processor and want to build a custom solution, please [contact us](mailto:hello@lightrail.com).

We have preconfigured sample values for your template as a demo to help you get testing quickly. You can use all the default values to start, but if you are interested in running through an active test that includes a sample customer Gift Card redemption, you will need to update the **Email Claim Link** value in order to set up a testing Url which you have access to. Please note: before taking your Drop-in widgets to production, you will want to be sure to update all the values to match your custom implementation.

You can optionally edit the other configuration value of your Drop-in Gift Card [template](https://www.lightrail.com/app/#/cards/dropin) within your Lightrail account to customize the appearance of widgets and gift card emails.
(For development, toggle your Lightrail account to test mode, this will allow you to use Stripe's test credit cards.) 

This is also where you'll connect your Stripe account and provide the URL to a redemption page where customers can redeem their gift cards (see Step 2).

If at any point you want to see a working example of the entire Drop-in Gift Card solution, check out our [sample app](https://github.com/Giftbit/stripe-integration-sample-webapp). 


## Drop-in Widget Customization

This document outlines how to customize the **Gift Card Purchase** and Code **Redemption Widgets** created by **Lightrail**. 

### Customization Properties

The drop in widgets are customizable via `data-attributes` passed to the script tag you add to your site. Each `data-attribute` takes only one string that represents a color value or other CSS property like `padding`.

Values can be passed in as follows: 

- **Hex value** - `#FFF`, `#ffffff`, `#000000` (3 or 6 characters plus pound symbol)
- **Named color** - `black`, `white`, `firebrick`, `goldenrod`
- **rgb(a)** - `rgba(255, 255, 255, 0.2)`
- **Padding** - `data-theme_padding="50px 10px 1em 0"`

**Note:**  There are no overrides for the **Account Balance Widget** since it returns results directly to the parent page to be by styled by your site’s existing CSS.

Should you have any questions regarding this document or the widget customization, please contact hello@lightrail.com

### Gift Card Purchase Theming

**Example: **

```javascript
<script src="cardPurchaseEmbed/index.js"
  data-shoppertoken="GENERATED_TOKEN"
  data-theme_bg_primary="#FF0"
  data-theme_btn_color_gift_amount_hover="white"></script>
```

Below is a comprehensive list of values accepted by the **Card Purchase Widget** embed script. 

1. `data-theme_bg_primary`
2. `data-theme_bg_secondary`
3. `data-theme_color_primary`
4. `data-theme_color_secondary`
5. `data-theme_btn_bg_primary`
6. `data-theme_btn_color_primary`
7. `data-theme_btn_bg_primary_disabled`
8. `data-theme_btn_color_primary_disabled`
9. `data-theme_btn_bg_secondary`
10. `data-theme_btn_color_secondary`
11. `data-theme_btn_bg_gift_amount`
12. `data-theme_btn_color_gift_amount`
13. `data-theme_btn_bg_gift_amount_hover`
14. `data-theme_btn_color_gift_amount_hover`
15. `data-theme_btn_bg_gift_amount_select`
16. `data-theme_btn_color_gift_amount_select`
17. `data-theme_btn_bg_back`
18. `data-theme_btn_color_back`
19. `data-theme_error_color`
20. `data-theme_text_color`
21. `data-theme_input_color`
22. `data-theme_label_color`
23. `data-theme_stripe_placeholder_color`
24. `data-launch_btn_label="Give a Gift"`
25. `data-launch_btn_classname="rocketship-button__small"`

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
<script src="codeRedemptionEmbed/index.js"
  data-shoppertoken="GENERATED_TOKEN"
  data-fullcode="EMAIL_URL_PARAM_VALUE"
  data-theme_padding="50px 20px"></script>
````

Below is a comprehensive list of values accepted by the **Code Redemption Widget** embed script.

1. `data-theme_bg_primary`
2. `data-theme_padding`
3. `data-theme_form_align_bottom (default “absolute” to align to the bottom)`
4. `data-theme_text_color_header`
5. `data-theme_text_color_subheader`
6. `data-theme_input_border_color`
7. `data-theme_input_bg_color`
8. `data-theme_input_color`
9. `data-theme_btn_bg_primary`
10. `data-theme_btn_color_primary`
11. `data-theme_btn_bg_primary_hover`
12. `data-theme_btn_color_primary_hover`
13. `data-theme_btn_bg_secondary`
14. `data-theme_btn_color_secondary`
15. `data-theme_btn_bg_secondary_hover`
16. `data-theme_btn_color_secondary_hover`
17. `data-success_btn_cta_label="Start Shopping Today"`
18. `data-success_btn_cta_href="landingpage"`
 
![Code redemption theming - 3](https://raw.githubusercontent.com/Giftbit/Lightrail-API-Docs/master/docs/assets/code-remption-theming-1.png)

**Note:** #17 provides a label to pass for the completed redemption step. #18 is the the href for where the button will redirect users on redemption completion.

## Support
Looking for an example? Check out our [sample app](https://github.com/Giftbit/stripe-integration-sample-webapp) which is a working example of the entire Drop-in Gift Card solution.

Contact us any time at hello@lightrail.com —- we are here to help.
