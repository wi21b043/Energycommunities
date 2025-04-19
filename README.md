Dies ist ein Hausaufgabenprojekt für einen Kurs über verteilte Systeme zur Simulation der Energieerzeugung und -nutzung in Gemeinden und Netzen.  
Dieses Projekt verwendet die Maven-Multimodulstruktur und enthält die folgenden Komponenten:
- energy-producer: simuliert einen gemeinschaftlichen Energieerzeuger
- energy-user: simuliert einen gemeinschaftlichen Energienutzer
- usage-service: Verarbeitet Nachrichten und aktualisiert die Zusammenfassung des Energieverbrauchs.
- percentage-service: Berechnet und speichert die prozentualen Daten für die aktuelle Stunde.
- rest-api: REST-Schnittstelle, die von Spring Boot bereitgestellt wird (liefert derzeit Beispieldaten)
- gui: JavaFX-Client zur Anzeige aktueller und historischer Daten.

## Startsequenz des Moduls (Meilensteinstufen)
1. rest-api: `mvn -pl rest-api spring-boot:run`
2) GUI: `mvn -pl gui javafx:run`