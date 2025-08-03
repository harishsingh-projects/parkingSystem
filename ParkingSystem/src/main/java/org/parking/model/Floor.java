package org.parking.model;

import java.util.*;

public class Floor {
    private final int floorNumber;
    private final Map<VehicleType, List<ParkingSpot>> spotMap;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spotMap = new HashMap<>();
        for (VehicleType type : VehicleType.values()) {
            spotMap.put(type, new ArrayList<>());
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void addSpot(ParkingSpot spot) {
        spotMap.get(spot.getType()).add(spot);
    }

    public List<ParkingSpot> getSpotsByType(VehicleType type) {
        return spotMap.get(type);
    }

    public Optional<ParkingSpot> getAvailableSpot(VehicleType type) {
        for (ParkingSpot spot : spotMap.get(type)) {
            if (spot.isAvailable()) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Floor #" + floorNumber + ", Spots: " + spotMap;
    }
}
