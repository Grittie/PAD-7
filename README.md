# Team PAD-7

## Authors

- Lars Grit
- Toine Donker
- ~~Ana Garcia Montez~~
- Luka Kothuis
- Sem Wester
- Kamal Kouidar

## Documentation

Je kan de Documentatie [hier](./docs/index.md) vinden.

## Project Beschrijving

Ontwikkel een product waarin de NAO robot centraal staat en een nuttige interactie
aan kan gaan met de doelgroep, maak daarbij gebruik van een externe factor.

## Opdrachtgever

We hebben HvA gekozen als opdrachtgever omdat Kamal deel uitmaakt van het opendagteam en dus ervaring heeft met studiekiezers en bezoekers. 
We kunnen de robot tijdens de opendag veel taken laten uitvoeren dankzij deze ervaring!

## Project Idee

~~De NAO is een leerroute presentator die op een actieve
educatieve manier de studiekiezer en begeleiders informeren over leerroutes binnen HBO-ICT. Hij moet op een bijna
menselijke wijze presenteren.~~

De NAO is een Studiekeuzecheck die gebruikers een reeks vragen stelt, waardoor de NAO kan bepalen wat de beste studiekeuze voor hen zou kunnen zijn. 
Wanneer de NAO deze keuze heeft gemaakt, wordt er een afbeelding gegenereerd die de studiekiezer kan scannen met zijn telefoon via een QR-code.

## Setup project:
- 1: Download java jdk
- 2: download depencies
  - java-naoqi-sdk
  - json-simple
  - org.eclipse.paho.client.mqttv3
- 3: Controleer of u de juiste hostname gebruikt, verander de hostname (indien nodig) in main.java.
- 4: Run main.java & ImgurAPI.py (Maakt niet uit welke eerst). Voor ImgurAPI zie documentatie voor opzet.
- 5: Zet de box aan het stroom
- 6: druk op een van de knoppen om te beginnen met de studycheck!