# Connessione 
|Porta| Tipo |
|-|-|
|3000|TCP|

# Protocollo
| Codice  | Direzione | Funzione  |
|-|-|-|
| 0 | Inviato | Client ha indovinato |
| 1 | Inviato | Client ha inviato un numero più grande |
| -1 | Inviato | Client ha inviato un numero più piccolo |
| -1 | Ricevuto | Client ha richiesto la chiusura del server ed attende |
| -2 | Inviato | Server ha ricevuto il comando di chisura e comunica al client che può chiudersi anche lui |