### Refund Transaction [POST /cards/{cardId}/transactions/{transactionId}/refund]
Refund a Transaction by reversing its effect. 

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + transactionId (string, required) - {{transaction.transactionId}} Must be an existing Transaction with transactionType `DRAWDOWN`.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
            
    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        + metadata (Metadata, optional) - {{metadata}}

    
    + Body 
            
            {
              "userSuppliedId":"transaction-90xxf6-refund",
              "metadata": {
                  "reason": "canceled order."
              }              
            }
    
+ Response 200
    + Attributes
        + transaction (Transaction)
        
    + Body

            {
              "transaction":{
                "transactionId":"transaction-77xx34",
                "value":50,
                "userSuppliedId":"transaction-9exxa3-reverse",
                "dateCreated":"2017-07-31T18:57:44.844Z",
                "transactionType":"DRAWDOWN_REFUND",
                "transactionAccessMethod":"CARDID",
                "valueAvailableAfterTransaction":1299,
                "giftbitUserId":"user-1dxx32",
                "cardId":"card-76xxab",
                "currency":"USD",
                "parentTransactionId":"transaction-9exxa3",
                "metadata":{
                  "giftbit_initial_transaction_id":"transaction-9exxa3"
                }
              }
            }
            
