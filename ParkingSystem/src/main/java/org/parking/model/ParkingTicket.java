package org.parking.model;

import java.time.LocalDateTime;

public class ParkingTicket {
    private static int idCounter = 0;

    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot, LocalDateTime entryTime) {
        this.ticketId = generateTicketId();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = entryTime;
        this.exitTime = null;
    }

    private synchronized String generateTicketId() {
        idCounter++;
        return "TCK" + String.format("%05d", idCounter);
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public String toString() {
        return "ParkingTicket[ID=" + ticketId +
                ", Vehicle=" + vehicle +
                ", Spot=" + spot.getSpotId() +
                ", Entry=" + entryTime +
                ", Exit=" + (exitTime != null ? exitTime : "Still Parked") + "]";
    }
}
