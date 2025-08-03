package org.parking.strategy;

import org.parking.model.ParkingTicket;

import java.time.Duration;

public class DefaultFeeStrategy implements FeeStrategy {

    @Override
    public double calculateFee(ParkingTicket ticket) {
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());
        long hours = Math.max(1, duration.toHours());

        double ratePerHour;
        switch (ticket.getVehicle().getType()) {
            case MOTORCYCLE:
                ratePerHour = 10.0;
                break;
            case CAR:
                ratePerHour = 20.0;
                break;
            case BUS:
                ratePerHour = 50.0;
                break;
            default:
                ratePerHour = 15.0;
                break;
        }
        return ratePerHour * hours;
    }
}
