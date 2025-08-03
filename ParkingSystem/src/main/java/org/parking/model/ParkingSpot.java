package org.parking.model;

public class ParkingSpot {
    private final String spotId;
    private final VehicleType type;
    private boolean isOccupied;

    public ParkingSpot(String spotId, VehicleType type) {
        this.spotId = spotId;
        this.type = type;
        this.isOccupied = false;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    // New method added for clarity and usability
    public boolean isAvailable() {
        return !isOccupied;
    }

    public void occupy() {
        this.isOccupied = true;
    }

    public void free() {
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        return "Spot[ID='" + spotId + "', Type=" + type + ", Occupied=" + isOccupied + "]";
    }
}
