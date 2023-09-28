# Java Socket programmering

Dette er vores eksempel på hvordan man kan lave en multi-user chat app programmeret i Java.

Vores protokol fungere på følgende måde:

1. Server lytter på port 8080
2. En bruger tilslutter sig til serveren
3. Brugeren skal indtaste sit navn
4. Serveren registrerer at brugeren er tilsluttet med navn
5. Bruger sender besked
6. Server modtager besked, og broadcaster til andre brugere.

![Visuel version af diagrammet](https://i.imgur.com/iLn0roz.png)

## Hvad gør vores program?
Vores program gør det nemt for brugere at kunne kommunikere og have en samtale med andre brugere. Vores brugere skal blot indtaste deres brugernavn og derfra har de fri adgang til det åbne forum.

### Hvad er programmet lavet med?
Vores program er programmeret i java og javas socket og server biblioteker.
