package at.technikum.energycommunities.producer;

import java.time.LocalDateTime;

/**
 * Allgemeine DTO für Energienachrichten:
 * Wird als JSON serialisiert und an RabbitMQ gesendet.
 *
 * @param type        Nachrichtentyp: "PRODUCER" oder "USER"
 * @param association Zugehörigkeit/Typ: z.B. "COMMUNITY"
 * @param kwh         In dieser Minute erzeugte/verwendete Energie (kWh)
 * @param datetime    Zeitpunkt des Ereignisses
 */
public record EnergyMessage(
        String type,
        String association,
        double kwh,
        LocalDateTime datetime
) {}
