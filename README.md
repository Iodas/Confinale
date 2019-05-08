Michael Nadigs Confinale Lösung

Vorbereitung
------------

Bitte stelle sicher, dass du (https://git-scm.com/), maven (https://maven.apache.org/), JDK 1.8 (https://java.com/de/download/) und npm (https://www.npmjs.com/) und angular-cli (``npm install -g @angular/cli``) installiert hast.


Führe dann diese Schritte aus, um die App zu starten:
- Klone dieses Repository mit git, importiere das Projekt als Maven-Projekt in eine IDE (IntelliJ, Eclipse) deiner Wahl
- Öffne eine Konsole und navigiere in den root folder 'application-se-exercise', dann führe den folgenden Befehl aus: `mvn spring-boot:run`. Maven wird jetzt die Dependencies downloaden und die Spring Boot Backend App starten. Dies kann eine Weile dauern. Wenn das Backend gestartet ist, ist es unter ``http://localhost:8080`` aufrufbar. Um Änderungen im Backend-Code zu übernehmen, musst du den Prozess stoppen und mittels `mvn spring-boot:run` erneut starten.
- Navigiere in einer weiteren Konsole in den Ordner "spring-angular-2-cli-starter/src/main/client" and führe erst den Befehl ``npm install`` und dann den Befehl ``npm start`` aus. Das angular.io Frontend wird starten und sollte dann unter ``http://localhost:4200`` verfügbar sein. Änderungen im Frontend Code werden automatisch übernommen.


Wir empfehlen dir, etwas durch den Code zu klicken und dich einzulesen. Wir haben etwas Hello-World Code zur Verfügung gestellt, welcher dir simple Features von Spring Boot und Angular 2 demonstrieren sollten. Wir empfehlen dir spezifisch (aber nicht ausschliesslich) die folgenden Files anzuschauen: 

- Frontend: ``scratch.component.html`` und ``scratch.component.ts``.
- Backend: ``UserController.java`` und ``UserDto.java``. Beachte: Das Backend benutzt Lombok um Boilderplate Code (z.B. `@Getter` und `@Setter`) automatisch zu generieren. Du kannst natürlich auch Vanilla Java ('reines' Java ohne Frameworks) benutzen.


Aufgabe
--------

Programmiere ein WG-Einkaufsprotokol, welches die folgenden Features haben sollte (sortiert nach absteigender Priorität der Features):

- Benutzer der Apps können Einkäufe (Name des Einkäufers, Name des Produktes, Datum, Preis) abspeichern
- Im Frontend wird das Einkaufsprotokoll (also die Liste aller erfassten Einkäufe) angezeigt
- Gespeicherte Einkäufe können (einzeln) gelöscht werden.
- Beim Einkaufsprotokoll wird die Summe der Preise aller getätigten Einkäufe angezeigt.
- Gespeicherte Einkäufe können (einzeln) bearbeitet werden.
- Beim Einkaufsprotokoll wird die Summe der Einkäufe pro Einkäufer angezeigt, am liebsten in einem Kuchendiagramm.
- Eine Funktion soll es ermöglichen, Einkaufssummen-Differenzen zwischen Einkäufern auszugleichen. Dazu sollen die entsprechenden Beträge, die ein spezifischer Einkäufer einem anderen Einkäufer abgeben muss, angezeigt werden und bei Bestätigung die entsprechenden Einträge ins Einkaufsprotokoll gesetzt werden. 

Um es dir etwas einfacher zu machen kannst du auf das User-Handling verzichten: D.h. Benutzer müssen sich nicht einloggen und alle Benutzer der App dürfen alle Einträge sehen, löschen und bearbeiten.

Für die Aufgabe (nach der Installation) hast du etwa vier Stunden Zeit. 

Abgabe
------

Bitte sende uns 
- Einen Link zu einem Git Repository (bei einem beliebigen Hoster) mit deinem Code

- Ein Dokument, in welchem du deine Arbeit kurz beschreibst: Wo gab es Probleme? Womit würdest du weitermachen? Was gefällt dir nicht an deinem Code?

Tipps
-----
- Kommst du bei einem Feature nicht weiter oder hast du mit einer bestimmten Stelle im Code ein Problem, versuche daran nicht zu viel Zeit zu verlieren sondern Beschreibe dein Problem in einem Kommentar und versuche einen Umweg zu finden. Zum Beispiel kannst du, wenn du mit Spring Data und der Datenbank dich nicht zurecht findest, dies entsprechend dokumentieren und eine Datenbank mittels einer statischen Liste in deinem Code simulieren.

- Stelle sicher, dass dein Code bei der Abgabe kompiliert, auch wenn du nicht alle Features vollständig implementiert hast. So können wir das bereits Programmierte testen.

- Test Code ist ein Plus, eine nachvollziehbare Commit History ebenso.

- Uns ist bewusst, dass die Zeit wohl nicht ausreichen wird, um alle Features zu implementieren ;-) Es ist ok, wenn du bei Abgabe nicht alle Features implementiert hast.

- Bei Fragen stehen wir dir gerne zur Verfügung.
