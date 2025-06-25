package at.technikum.energycommunities.user;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Community Energy Message DTO – identisch zur Definition im Producer-Modul.
 * Implementiert Serializable, damit SimpleMessageConverter sie akzeptiert.
 *
 * @param type        Nachrichtentyp: "PRODUCER" oder "USER"
 * @param association Assoziationstyp: "COMMUNITY"
 * @param kwh         Energie (kWh), die in dieser Minute produziert/genutzt wurde
 * @param datetime    Zeitstempel des Ereignisses
 */
public record EnergyMessage(
        String type,
        String association,
        double kwh,
        LocalDateTime datetime
) implements Serializable {}
