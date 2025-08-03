package org.parking.strategy;

import org.parking.model.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket ticket);
}
