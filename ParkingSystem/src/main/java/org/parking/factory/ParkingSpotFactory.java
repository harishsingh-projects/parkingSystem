package org.parking.factory;

import org.parking.model.ParkingSpot;
import org.parking.model.VehicleType;

public class ParkingSpotFactory {
    public static ParkingSpot createParkingSpot(String spotId, VehicleType type) {
        // Aage agar alag alag spot types chahiye to yahan customize kar sakte hain
        return new ParkingSpot(spotId, type);
    }
}
