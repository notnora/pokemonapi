# PokemonAPI

PokemonAPI er ment som en eksempelapplikasjon for å demonstrere konsepter og features i en Java-applikasjon.
Applikasjonen er satt opp med Spring Boot og er en Rest-applikasjon.

### Kjøre opp applikasjonen lokalt
Man bruker `LocalAppStarter` for å kjøre opp applikasjonen lokalt.
Den benytter seg av 2 profiler: `test` og `local`.

### H2-database
Konfigurasjonen til h2-databasen er definert i ``appliaction-test.yaml``.
Når man kjører med profil ``local`` kan man også aksessere databasen via
gui-consolen ved å gå inn på ``/h2-console``-endepunktet.

# Fagkveld: Automatiset testing
Slides: [Google Slides](https://docs.google.com/presentation/d/1WEz59x_tzP7-5coKk1Zuu_tbVVdcUwluLvdlCZUNzMw/edit?usp=sharing)

### Endepunkt

#### Create Trainer
```
skaljd
```