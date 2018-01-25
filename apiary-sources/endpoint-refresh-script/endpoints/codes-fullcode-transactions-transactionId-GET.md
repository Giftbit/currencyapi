### Show Transaction Based on Gift Code [GET /codes/{fullcode}/transactions/{transactionId}{?pin}]
Retrieves a particular Transaction by its ID based on the `fullcode`.

---
+ Parameters
    + transactionId (string, required) - {{transaction.transactionId}}
    + fullcode (string, required) - {{card.fullcode}}
    + pin (string, optional) - This is required if the fullcode has a pin.
    

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + transaction (Transaction)
        
    + Body 
    
            {
                "transactionId": "transaction-95xx1d",
                "value": -15875,
                "userSuppliedId": "11xx62-capture",
                "dateCreated": "2017-08-10T00:08:54.697Z",
                "transactionType": "DRAWDOWN",
                "transactionAccessMethod": "CARDID",
                "valueAvailableAfterTransaction": 0,
                "giftbitUserId": "user-08xx8e",
                "cardId": "card-6dxx89",
                "currency": "USD",
                "parentTransactionId": "transaction-a6xx9d",
                "metadata":{
                    "giftbit_initial_transaction_id": "transaction-a6xx9d"
                }
            }

