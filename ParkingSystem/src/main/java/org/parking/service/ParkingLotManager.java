package org.parking.service;

import org.parking.model.*;
import org.parking.strategy.FeeStrategy;
import org.parking.observer.Subject;
import org.parking.observer.Observer;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLotManager implements Subject {
    private final List<Floor> floors;
    private final Map<String, ParkingTicket> activeTickets;
    private final FeeStrategy feeStrategy;
    private final List<Observer> observers = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public ParkingLotManager(List<Floor> floors, FeeStrategy feeStrategy) {
        this.floors = floors;
        this.activeTickets = new HashMap<>();
        this.feeStrategy = feeStrategy;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public synchronized ParkingTicket checkIn(Vehicle vehicle) {
        for (Floor floor : floors) {
            Optional<ParkingSpot> spotOpt = floor.getAvailableSpot(vehicle.getType());
            if (spotOpt.isPresent()) {
                ParkingSpot spot = spotOpt.get();
                spot.occupy();

                ParkingTicket ticket = new ParkingTicket(vehicle, spot, LocalDateTime.now());
                activeTickets.put(vehicle.getLicensePlate(), ticket);

                notifyObservers("[Notification] Vehicle " + vehicle.getLicensePlate() +
                        " parked at spot " + spot.getSpotId());

                return ticket;
            }
        }
        // No spot available, create reservation if not already reserved
        boolean alreadyReserved = reservations.stream()
                .anyMatch(r -> r.getVehicle().getLicensePlate().equals(vehicle.getLicensePlate()));

        if (!alreadyReserved) {
            // Note: spot=null means waiting for any spot compatible with vehicle
            reservations.add(new Reservation(vehicle, null, LocalDateTime.now()));
            notifyObservers("[Notification] No available spot. Reservation created for vehicle: " + vehicle.getLicensePlate());
        } else {
            notifyObservers("[Notification] Vehicle " + vehicle.getLicensePlate() + " already has a reservation.");
        }
        return null;
    }

    public synchronized double checkOut(String licensePlate) {
        ParkingTicket ticket = activeTickets.remove(licensePlate);
        if (ticket == null) {
            notifyObservers("[Notification] No active ticket found for vehicle: " + licensePlate);
            return 0.0;
        }
        ParkingSpot freedSpot = ticket.getSpot();
        ticket.setExitTime(LocalDateTime.now());
        freedSpot.free();

        // Notify one reserved vehicle if spot matches vehicle type
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            Vehicle reservedVehicle = reservation.getVehicle();
            if (reservedVehicle.getType() == freedSpot.getType()) {
                notifyObservers("[Notification] Spot " + freedSpot.getSpotId() + " is now available for reserved vehicle: " + reservedVehicle.getLicensePlate());
                iterator.remove();
                break; // notify only one reservation at a time
            }
        }

        double fee = feeStrategy.calculateFee(ticket);
        notifyObservers("[Notification] Vehicle " + licensePlate + " exited. Fee: ₹" + fee);
        return fee;
    }
}
