package org.parking;

import org.parking.factory.VehicleFactory;
import org.parking.factory.ParkingSpotFactory;
import org.parking.model.*;
import org.parking.service.ParkingLotManager;
import org.parking.strategy.DefaultFeeStrategy;
import org.parking.strategy.FeeStrategy;
import org.parking.observer.NotificationService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create floors and add parking spots using Factory
        Floor floor1 = new Floor(1);
        floor1.addSpot(ParkingSpotFactory.createParkingSpot("F1-M1", VehicleType.MOTORCYCLE));
        floor1.addSpot(ParkingSpotFactory.createParkingSpot("F1-M2", VehicleType.MOTORCYCLE));
        floor1.addSpot(ParkingSpotFactory.createParkingSpot("F1-C1", VehicleType.CAR));
        floor1.addSpot(ParkingSpotFactory.createParkingSpot("F1-C2", VehicleType.CAR));
        floor1.addSpot(ParkingSpotFactory.createParkingSpot("F1-B1", VehicleType.BUS));

        List<Floor> floors = new ArrayList<>();
        floors.add(floor1);

        // 2. Initialize fee strategy and ParkingLotManager
        FeeStrategy feeStrategy = new DefaultFeeStrategy();
        ParkingLotManager manager = new ParkingLotManager(floors, feeStrategy);

        // 3. Register notification observer
        NotificationService notificationService = new NotificationService();
        manager.registerObserver(notificationService);

        // 4. Create vehicles using Factory
        Vehicle bike1 = VehicleFactory.createVehicle("BIKE123", VehicleType.MOTORCYCLE);
        Vehicle car1 = VehicleFactory.createVehicle("CAR123", VehicleType.CAR);
        Vehicle bus1 = VehicleFactory.createVehicle("BUS123", VehicleType.BUS);

        // 5. Check-in vehicles
        System.out.println("\n--- Vehicle Check-In ---");
        manager.checkIn(bike1);
        manager.checkIn(car1);
        manager.checkIn(bus1);

        // 6. Attempt to check-in another car (should succeed)
        Vehicle car2 = VehicleFactory.createVehicle("CAR456", VehicleType.CAR);
        manager.checkIn(car2);

        // 7. Attempt to check-in one more car (should fail - no spot) and create reservation
        Vehicle car3 = VehicleFactory.createVehicle("CAR789", VehicleType.CAR);
        manager.checkIn(car3);  // Reservation should be created here

        // 8. Check-out some vehicles to free spots
        System.out.println("\n--- Vehicle Check-Out ---");
        double feeBike = manager.checkOut("BIKE123");
        System.out.println("Bike Parking Fee: ₹" + feeBike);

        double feeCar = manager.checkOut("CAR123");
        System.out.println("Car Parking Fee: ₹" + feeCar);

        // 9. After freeing spot, reserved vehicle should be notified that spot is available
        // (The notification happens inside checkOut)

        // 10. Try checking out a vehicle not parked
        double feeUnknown = manager.checkOut("UNKNOWN");
        System.out.println("Unknown Vehicle Fee: ₹" + feeUnknown);
    }
}
