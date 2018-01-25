
### Retrieve fullcode for Gift Card [GET /cards/{cardId}/fullcode]
<a name="get-fullcode-anchor"></a>

Retrieve the `fullcode` (also referred to as Gift Code) associated with a Gift Card. 


---
+ Parameters 
    + cardId (string) - {{card.cardId}} Note, Card must have cardType = `GIFT_CARD`.
    
+ Request (application/json)
    + Headers
    
            {{header.authorization}}

+ Response 200
    + Attributes 
        + fullcode (Fullcode)

    + Body
    
            {
                "fullcode":{
                    "code" : "5JBUR-9V922-5EDL8-RP5R5-YYEVY"
                }
            }

