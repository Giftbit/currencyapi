### Retrieve Account Cards or Gift Cards for Contact [GET /cards{?contactId}{?cardType}{?currency}]
Retrieve a paginated list of a Contact's Cards.

---
+ Parameters 
    + contactId (string, required) - The Lightrail Contact ID.
    + cardType (string, required) - {{card.cardType}}
    + currency (string, optional) - {{currency}} Only needed if the Contact has Account Cards in multiple currencies.

+ Request (application/json)
    + Headers
    
            {{header.authorization}}
    
+ Response 200
    + Attributes
        + cards (array[Card])
        + pagination (Pagination)

    + Body
    
            {RESPONSE_REPLACEMENT:getCardsForContact2.response.body}