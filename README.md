## Beskrivelse av appen

For å gjøre prosjektet mer engasjerende og morsomt å jobbe med for vår egen del, valgte vi å lage et spill. Vi landet på å lage en noe forenklet versjon av det kjente og kjære 90-talls spillet Snake, hvor brukeren styrer en slange rundt på et spillbrett. 


En bruker starter et spill ved å skrive inn navnet sitt, før de deretter trykker på start-knappen. Da initialiseres et nytt spill, og en slange dukker opp på spillbrettet med et eple. Slangen beveger seg hvert halve sekund, og brukeren må styre slangen til eplet med styringsknappene. Hvis et eple spises, får spilleren et poeng og slangen blir lengre. Spillet er over dersom slangen krasjer i veggen eller seg selv. Da dukker navnet til brukeren samt scoren hen fikk opp på poengtavlen. Denne oppdateres mellom hver spiller ved at nye scores legges til dersom de er gode nok til å være på topp ti. 


## Klassediagram: 

<img width="549" alt="image" src="https://github.com/user-attachments/assets/14edb91a-6a23-4140-8b5c-5aeac481a463">



## Bruk av pensum i appen

Vi har fått bruk for mange deler av pensum i utviklingen av appen vår.
### Interface
Siden spillet vårt er basert i et GridPane, ble gettere for koordinater med x- og y-verdier noe vi stadig måtte forholde oss til. Vi skjønte fort at mange av klassene måtte ha fellesmetoder for dette, derfor lagde vi et interface kalt SnakeInterface hvor vi la inn disse fellesmetodene som abstrakte metoder. På denne måten kunne vi definere et sett med «regler» for klassene som implementerer SnakeInterface, og skape en form for forutsigbarhet og sikkerhet om at disse metodene var på plass. Apple og XY-value implementerer SnakeInterface. 

### Filbehandling
For at appen vår skulle klare å lese og skrive til fil, bestemt vi oss for å lagre scoren til ulike spillere i en poengtavle. For å få til dette opprettet vi den nye klassen Highscore, som inneholder et Map med entries som består av brukerens navn (key) og hens score (value). Her definerte vi også logikk for formatet vi ønsket poengtavlen på; i synkende rekkefølge, skal kun kunne holde 10 toppscores og en person kan kun ha én score på listen (sin beste). I tillegg skrev vi metoder for å få skrevet dette til fil med en PrintWriter, samt å få lest fra fil for å få oppdatert poengtavlen mellom hvert spill med en Scanner. 

### Innkapsling 
Vi har hensiktsmessig innkapsling på alle felt og klasser, i form av synlighetsmodifikatorer som styrer tilgjengeligheten til klasser, felter, metoder og konstruktører. Vi har brukt private på alle feltene, og generelt sett brukt public på standardmetodene som aksesserer feltene samt klassene. Dette sikrer uavhengighet mellom koden i de ulike klassene. 

### Validering og unntakshåndtering
Vi har validering der det trengs for å sikre gyldig tilstand, blant annet for å sjekke at slangen og eplet alltid har gyldige koordinater. Når et uønsket tilfelle oppstår, som at slangen har kjørt inn i veggen (prøver å kjøre utenfor sine gyldige koordinater), utløses et unntak i form av IllegalArgumentException. Vi har også brukt try-catch, eksempelvis da vi skulle skrive til og lese fra fil. Alt dette har gjort det enklere for oss å lokalisere hvor feil har oppstått underveis, samt bidratt til å sikre gyldig tilstand. 

### Assosiasjoner
Appen vår består av mange objekter koblet sammen i et nettverk av assosiasjoner. Snake-instanser består for eksempel av en liste av XY-verdier (1-n), eller at Apple-instanser har en XY-verdi (1-1).

### Lambdauttrykk og streams
Vi tok i bruk lambdauttrykk og streams fremfor store og komplekse for-loops, for eksempel da vi skulle sortere Map-et i Highscore-klassen i stigende rekkefølge og hente ut det første elementet (personen med dårligst score). 

### Delegering 
Kontrolleren delegerer oppgaver til andre klasser, når den binder sammen brukergrensesnittet og klassene i modellen vår. 


## Model-View-Controller prinsipper (MVC)
Gjennom hele prosjektet har vi forsøkt å holde oss til Model-View-Controller-prinsippet, altså å holde brukergrensesnittet, kontrolleren og modellen så separat som mulig. Brukergrensesnittet er definert i FXML-filene laget med Scenebuilder og modellen vår består av fem klasser og et grensesnitt, hvor logikken vår for de ulike objektene ligger. Kontrolleren binder disse to partene sammen og holde de «oppdatert» i forhold til hverandre. 
Logikken for selve spillet ble hovedsakelig skrevet i modellen i «Game»-klassen, men vi opplevde ofte at det var enkelt å bare skrive logikk inn i kontrolleren. Siden dette bryter med MVC-prinsippet, så vi stadig etter måter vi kunne flytte koden over i modellen vår istedenfor, slik at vi heller kunne kalle på disse metodene fra kontrolleren. Ofte lyktes vi med dette, men likevel er det noe logikk gjenværende i Controller-klassen. Visse deler virket veldig vanskelig å ha andre steder siden de var så tett knyttet til FXML-elementene. Vi har eksempelvis en if-setning og en try-catch i Controller, som er knyttet til Timeline og GameOver-skjermen. Dette burde nok ha blitt flyttet over i en annen klasse dersom MVC-prinsippet skulle vært helt oppfylt. 

## Testing
Vi lagde fire testklasser; «XYvalueTest», «AppleTest», «FileTesting» og «SnakeMovementTest». Fellestrekket for disse er at vi først testet grunnleggende scenarioer, før vi deretter prøvde å sette opp tilstander som kunne føre til merkelig oppførsel. Her brukte vi JUnit-testing for å sjekke at ulike verdier i spesifikke scenarioer faktisk var som forventet og at koden vår fungerte som tenkt. For eksempel sjekket vi at filhåndteringen fungerte som ønsket ved å generere en highscorelist med 10 scores. Så sjekket vi at en score høyere enn den dårligste ble lagt til på listen, mens en score lavere enn den dårligste ikke ble lagt til. Å teste hele koden grundig ville krevd mange tester og mye tid, noe vi ikke hadde. Derfor testet vi kun det vi anså som mest essensielt for appens funksjonalitet og scenarioer vi opplevde feilet under utviklingen. 

