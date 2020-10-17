package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingBoy {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        parkingLot.parkCar(car);
        return hasAvailableSlots() ? parkingTicket : null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (hasNoParkingTicket(parkingTicket)){
            throw new RuntimeException("Please provide your parking ticket");
        }
        if (isUnrecognizedParkingTicket(parkingTicket)){
            throw new RuntimeException("Unrecognized Parking Ticket " + parkingTicket.hashCode());
        }
        Car car = parkingTicketCarMap.get(parkingTicket);
        parkingTicketCarMap.remove(parkingTicket);
        parkingLot.fetchCar(car);
        return car;
    }

    private boolean hasAvailableSlots() {
        return parkingLot.getParkedCars().size() <= parkingLot.getCapacity();
    }

    private boolean isUnrecognizedParkingTicket(ParkingTicket parkingTicket) {
        return !parkingTicketCarMap.containsKey(parkingTicket);
    }

    private boolean hasNoParkingTicket(ParkingTicket parkingTicket) {
        return Objects.isNull(parkingTicket);
    }
}
