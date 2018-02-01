## Simulate and Balance-Check [/cards/{cardId}/balance/]
Use these endpoints to simulate a Transaction and get the available balance of a Card in the context of a would-be Transaction. 
Depending on the context of your use-case you can do this either based on the `cardId` or the `fullcode`. 
Note that only Gift Cards have a `fullcode`.

---


{% include 'endpoints/cards-cardId-transactions-dryRun-POST.md' %}

{% include 'endpoints/codes-fullcode-transactions-dryRun-POST.md' %}

{% include 'endpoints/cards-cardId-details-GET.md' %}

{% include 'endpoints/codes-fullcode-details-GET.md' %}