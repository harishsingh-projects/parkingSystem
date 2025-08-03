package org.parking.model;

import java.time.LocalDateTime;

public class Reservation {
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final LocalDateTime reservationTime;

    public Reservation(Vehicle vehicle, ParkingSpot spot, LocalDateTime reservationTime) {
        this.vehicle = vehicle;
        this.spot = spot;
        this.reservationTime = reservationTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }
}
