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
            
            {"userSuppliedId":"transaction-aaff893f88164f94a47a4af1c3411900-refund"}
    
+ Response 200
    + Attributes
        + transaction (Transaction)
        
    + Body

            {"transaction":{"cardId":"card-d99142eac3bd4abfbe657db000b614f6","cardType":"ACCOUNT_CARD","currency":"USD","dateCreated":"2018-01-29T20:26:20.624Z","giftbitUserId":"user-4646197086af471fa9265fd3d1546ffa","metadata":{"giftbit_initial_transaction_id":"transaction-aaff893f88164f94a47a4af1c3411900"},"parentTransactionId":"transaction-aaff893f88164f94a47a4af1c3411900","transactionAccessMethod":"CARDID","transactionBreakdown":[{"value":100,"valueAvailableAfterTransaction":500,"valueStoreId":"value-fc28fcd3f48140fa90b8ea66a479ccdf"}],"transactionId":"transaction-a65baed608974889856e51e28d6cd5e6","transactionType":"DRAWDOWN_REFUND","userSuppliedId":"transaction-aaff893f88164f94a47a4af1c3411900-refund","value":100,"valueAvailableAfterTransaction":500}}
            
