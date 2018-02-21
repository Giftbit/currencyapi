### Deliver Gift Card as Gift Email[POST /turnkey/giftcard/deliver]
This endpoint is used for delivering a gift card to a recipient using Lightrail's Drop-in Gift Card email template.  

---
+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes 
        + cardId (string, required) - The cardId of the gift card to deliver.
        + recipientEmail (string, required) - The email address you'd like to deliver the gift to.
        + message (string, required) - The message you'd like to include in the body of the gift email.
        + senderName (string, required) - The email template includes "From <senderName>".
        
    + Body
    
            {"cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","message":"Hi there, please enjoy this gift.","senderName":"The Rocketship team","recipientEmail":"recipient@example.com"}
        
+ Response 200
    + Attributes
        + success (boolean)
        + params 
            + cardId (string)
            + recipientEmail (string)
            + message (string)
            + senderName (string)
        
    + Body

            {"success":true,"params":{"cardId":"card-9f1a5fb5b5154f90beb1ebfea696c967","recipientEmail":"recipient@example.com","message":"Hi there, please enjoy this gift.","senderName":"The Rocketship team"}}
            
