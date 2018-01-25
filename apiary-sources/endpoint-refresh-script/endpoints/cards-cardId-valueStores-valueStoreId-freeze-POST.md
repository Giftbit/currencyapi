### Freeze ValueStore [POST /cards/{cardId}/valueStores/{valueStoreId}/freeze]
Freeze a Card's ValueStore, preventing all transactions against that ValueStore until unfrozen. 

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
                "userSuppliedId":"case-320"
            }
    
+ Response 200
    + Attributes
        + transaction (Transaction)

    + Body

            {
                "transaction":{
                    "transactionId": "transaction-3fxxe1",
                    "value": -50,
                    "userSuppliedId": "case-320",
                    "dateCreated": "2017-06-05T16:36:56.321Z",
                    "transactionType": "FREEZE",
                    "transactionAccessMethod": "CARDID",
                    "valueAvailableAfterTransaction": 0,
                    "giftbitUserId": "user-50xx93",
                    "codeLastFour": "NKNA",
                    "cardId": "card-fbxxda",
                    "currency": "XXX"
                }
            }

