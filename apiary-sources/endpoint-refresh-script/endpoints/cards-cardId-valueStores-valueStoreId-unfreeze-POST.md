### Unfreeze ValueStore [POST /cards/{cardId}/valueStores/{valueStoreId}/unfreeze]
Unfreeze a Card's frozen ValueStore, re-enabling the creation of transactions against that ValueStore.

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
                "userSuppliedId":"case-320-resolved"
            }
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {
                "transaction":{
                    "transactionId": "transaction-f7xx82",
                    "value": 50,
                    "userSuppliedId": "ccase-320-resolved",
                    "dateCreated": "2017-06-05T16:37:57.218Z",
                    "transactionType": "UNFREEZE",
                    "transactionAccessMethod": "CARDID",
                    "valueAvailableAfterTransaction": 50,
                    "giftbitUserId": "user-50xx93",
                    "codeLastFour": "NKNA",
                    "cardId": "card-fbxxda",
                    "currency": "XXX"
                }
            }

