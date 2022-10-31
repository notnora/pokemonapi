# PokemonAPI

Heisann, Trener!

PokemonAPI er ment som en eksempelapplikasjon for å demonstrere konsepter og features i en Java-applikasjon.
Applikasjonen er satt opp med Spring Boot og er en Rest-applikasjon.

### Kjøre opp applikasjonen lokalt
Man bruker `LocalAppStarter` for å kjøre opp applikasjonen lokalt.
Den benytter seg `local`-profilen.

### H2-database
Konfigurasjonen til h2-databasen er definert i ``appliaction-test.yaml``.
Når man kjører med profil ``local`` kan man også aksessere databasen via
gui-consolen ved å gå inn på ``/h2-console``-endepunktet.

# Fagkveld: Automatiset testing
Slides: [Google Slides](https://docs.google.com/presentation/d/1WEz59x_tzP7-5coKk1Zuu_tbVVdcUwluLvdlCZUNzMw/edit?usp=sharing)

### Endepunkt
Beskrivelse av endepunktene til applikasjonen med `cURL`-eksempler.

Postman-kalle kan importeres fra denne filen: ``/postman/pokeapi.postman_collection.json``

#### Hello, Kanto!
Index-endepunkt
````
curl --location --request GET 'http://localhost:8080/api/index/'
````

#### Create Trainer
Oppretter en trener i databasen

```
curl --location --request GET 'http://localhost:8080/api/trainer/' \
--header 'Content-Type: application/json' \
--data-raw '{
  "trainerName": "nora",
  "pokemonActive": ["bulbasaur", "ivysaur", "venusaur"],
  "pokemonStorage": ["squirtle", "wartortle", "blastoise"]
}'
```

#### Get Trainer
Henter en trener fra databasen
````
curl --location --request GET 'http://localhost:8080/api/trainer/nora'
````

#### Get All Trainers
Henter alle trenere i databasen
```
curl --location --request GET 'http://localhost:8080/api/trainer/all'
```

#### Get all Pokemon in DB
Henter alle pokemon i databasen
```
curl --location --request GET 'http://localhost:8080/api/pokemon/db/all'
```

#### Get all Pokemon
Hent alle pokemon (gjør oppslag mot pokeApi via applikasjonen vår)
```
curl --location --request GET 'http://localhost:8080/api/pokemon/'
```

#### Get Pokmon by Name
Hent en pokemon basert på navnet i parameteren

```
curl --location --request GET 'http://localhost:8080/api/pokemon/snover'
```

## Gjennomgang av tester

### Enhetstest `TrainerServiceTest.java`

Tester enheten `TrainerService`.

#### Setup: 
* For å få testen til å bruke Mockito for mocking og stubbing, må man legge på annotasjonen `@ExtendWith(MockitoExtension.class` over klassedefinisjonen.
* Alle avhengighetene som er tilknyttet `TrainerService` mocker vi vekk. 
Dvs. at vi annoterer klasseinstansieringen med `@Mock`
* Vi legger også til en `ArgumentCaptor` og annoterer den med `@Captor`
* Vi annoterer instansen som skal bruke mocksene med `@InjectMocks`.
Det betyr at `PokeAPIConsumer`-en, `PokemonStorageRepositoryService` og `TrainerRepositoryService`
som blir brukt i`trainerService`, bare er mockede-objekter.

#### Test `createTrainerWithNameOnePartyOneStoragePokemonSuccessfully`
* Vi oppretter all testdata som skal stubbe kall mot de mockede avhengighetene.
* `doReturn().when()` sier at stubsene vi lagde som testdata skal returneres nå `getPokemonFromName()`-metoden kalles.
Merk: Dette må skje før man kjører kallet man skal teste.
* Kallet vi tester, utføres.
* Vi bruker `Mockito.verify()` for å sjekke at metodene blir kalt.
* Vi bruker `assertThat()` for å sjekke at outputen er det vi ønsker.
* Vi bruker `Mockito.verify()` for å sjekke at kallet til metoden blir kjørt
med `ArgumentCaptor`-en vår.
* Vi sjekker at verdien til `ArgumentCaptor`-en er det vi forventer. 

### Komponenttest - `DbComponentTest`
Tester flyten for å hente alle pokemon fra databasen - Bruker en egen testdatabase.

#### Setup
* En egen database blir brukt for testing: `PokemonStoragetestRepository`
* `setUp`-metoden blir annotert med `@BeforeEach` som utfører metoden før hver test.
Her blir databasen tømt før hver test.

#### Test
* Testen starter med å lagre en pokemon i databasen
* Kallet for å hente ut alle pokemon i databasen blir utført
* Sjekker at det kun er en pokemon som er hentet ut
* Sjekker verdien til de forskjellige kolonnene til pokemonen.

### Komponenttest - getPokemonComponentTest
Tester flyten for å hente pokemon fra pokeAPI - Bruker Wiremock for å stubbe ut kall mot pokeApi.

#### Setup
* I `appliaction-test.yaml` definerer vi url-en som vi ønsker at mock serveren til Wiremock skal motta 
med `http://localhost:${wiremock.server.port}`. Her har vi gjort det for pokeApi-endepunktet.
* Wiremock kan hente ut stubs for responser fra `json`-filer. 
Default-sted hvor Wiremock leter etter disse filene er under `resources/mappings` i `test`-mappa.
* I `json`-stuben definerer man `request`-propertien som er det Wiremock matcher på når applikasjonen gjør kall mot mock-serveren.
[Mer om stubing med Wiremock:](https://wiremock.org/docs/stubbing/).
* `priority` bestemmer hvilken priority stubben har dersom det er flere som matcher. Lavt tall = høy prioritet.
* Wiremock tillater også å stubbe responser med Java.
* `withBody` tar bare `String` og `byte[]`, så man må gjøre om objektene sine til json-string

#### Test
`getPokemonByNameFromPokeAPIJSONStubOk`
* Denne bruker `json`-response stuben.
* Kjører metodekall fra controlleren.
* `getAllServeEvents()` henter ut alle `ServeEvent`-ene som Wiremock har gjort.
Her kan man kjøre debugger og se på hva en ServeEvent inneholder.
* Ved å hente ut `ServEvent`-er så kan man gjøre assertions på dem.
* `assertThat` Stopper kjøringen på første feilende test, 
mens `assertSoftly` kjører gjennom alle tester og rapporterer feilende tester på slutten.

`getPokemonByNameFromPokeAPIJavaStubOk`
* Denne bruker java for å stubbe responsen.
* Den lager en pokemon og bruker `stubFor` for å lage stubben.
* Man kan teste `ServeEvent`-ene på samme måte som om man stubber med `json`-stubs.