### Cancel ValueStore [POST /cards/{cardId}/valueStores/{valueStoreId}/cancel]
Permanently cancels a Card's ValueStore.

---
+ Parameters
    + cardId (string, required) - {{card.cardId}}
    + valueStoreId (string, required) - The Lightrail ValueStore ID.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}

    + Attributes
        + userSuppliedId (string, required) - {{userSuppliedId}}
        
    + Body 
    
            {
                "userSuppliedId":"case-122"
            }
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {
                "transaction":{
                    "transactionId": "transaction-7fxx89",
                    "value": -50,
                    "userSuppliedId": "case-122",
                    "dateCreated": "2017-06-05T16:39:06.679Z",
                    "transactionType": "CANCELLATION",
                    "transactionAccessMethod": "CARDID",
                    "valueAvailableAfterTransaction": 0,
                    "giftbitUserId": "user-50xx93",
                    "codeLastFour": "NKNA",
                    "cardId": "card-fbxxda",
                    "currency": "XXX"
                }
            }
