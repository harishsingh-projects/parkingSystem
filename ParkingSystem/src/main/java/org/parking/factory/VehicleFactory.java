package org.parking.factory;

import org.parking.model.Vehicle;
import org.parking.model.VehicleType;

public class VehicleFactory {
    public static Vehicle createVehicle(String licensePlate, VehicleType type) {
        // Abhi simple Vehicle bana rahe hain, future me subclasses bhi add kar sakte hain
        return new Vehicle(licensePlate, type);
    }
}

