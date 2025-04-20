Dies ist ein Hausaufgabenprojekt für einen Kurs über verteilte Systeme zur Simulation der Energieerzeugung und -nutzung in Gemeinden und Netzen.  
Dieses Projekt verwendet die Maven-Multimodulstruktur und enthält die folgenden Komponenten:
- energy-producer: simuliert einen gemeinschaftlichen Energieerzeuger
- energy-user: simuliert einen gemeinschaftlichen Energienutzer
- usage-service: Verarbeitet Nachrichten und aktualisiert die Zusammenfassung des Energieverbrauchs.
- percentage-service: Berechnet und speichert die prozentualen Daten für die aktuelle Stunde.
- rest-api: REST-Schnittstelle, die von Spring Boot bereitgestellt wird (liefert derzeit Beispieldaten)
- gui: JavaFX-Client zur Anzeige aktueller und historischer Daten.

### Startsequenz des Moduls (Meilensteinstufen)
1. rest-api: `mvn -pl rest-api spring-boot:run`
2) GUI: `mvn -pl gui javafx:run`



### Fehlerbehebung: JavaFX Runtime Component Problembehebung
### fix(gui): sync mainClass, JavaFX deps & document setup
- **Problemanifestation des Problems  
  Fehler: „Fehlende JavaFX-Laufzeitkomponente“ beim Ausführen von `mvn javafx:run` oder beim direkten Starten der GUI mit der Anwendungskonfiguration.

- **Lösung
    1. definieren es in `<properties>` von `gui/pom.xml`:
       ``xml
          <maven.compiler.release>21</maven.compiler.release
          <maven.compiler.source>21</maven.compiler.source
          <maven.compiler.target>21</maven.compiler.target
          <javafx.version>21</javafx.version
          <javafx.platform>win </javafx.platform>.
       ``
    2. in der Datei `gui/pom.xml` die Elemente `javafx-controls` und `javafx-fxml` einfügen

    3. in der Konfiguration des `javafx-maven-plugin`, stellen Sie sicher:
       ```xml
       <mainClass>at.technikum.energycommunities.gui.GuiApplication</mainClass>
       ```
    4. führen `mvn javafx:run` erneut aus und die GUI wird normal angezeigt.

### Creating Package Structures
- Paket unter rest-api/src/main/java/at.technikum.energycommunities.restApi erstellen .controller、.dto、.service.

**DTO
1. Erstellen CurrentEnergyDto.java und HistoricalEnergyDto.java: Datenfelder definieren und Zeitformatierung mit @JsonFormat sicherstellen.

**Service
2. Fügen EnergyService.java zum Service-Paket hinzu, annotieren es mit @Service und implementieren die beiden Schnittstellenmethoden getCurrentEnergy(), getHistoricalEnergy(...).

**Controller
3. (1)Fügen eine neue EnergyCommunitiesController.java im Controller-Paket mit @RestController, @RequestMapping(„/energy“) hinzu und injizieren EnergyService über den Konstruktor.
   (2)Hinzufügen von @GetMapping zu beiden GET-Schnittstellen, Binden von URL-Parametern und Aufrufen des Dienstes zur Rückgabe von DTOs.

**Konfiguration und Prüfung
4. RestApiApplication in der IDE ausführen (Hauptklasse mit @SpringBootApplication)


**Getestet auf einer Webseite

5. "http://localhost:8080/energy/current"

"http://localhost:8080/energy/historical?start=2025-04-19T10:00:00&end=2025-04-19T12:00:00"