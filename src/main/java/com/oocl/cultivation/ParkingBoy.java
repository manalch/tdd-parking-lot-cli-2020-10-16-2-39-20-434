package com.oocl.cultivation;

import java.util.*;

public class ParkingBoy {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if (hasNoAvailableParkingLot()){
            throw new RuntimeException("Not enough position");
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        parkingLot.parkCar(car);

        return parkingTicket;
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

    private boolean hasNoAvailableParkingLot() {
        return parkingLot.getParkedCars().size() >= parkingLot.getCapacity();
    }

    private boolean isUnrecognizedParkingTicket(ParkingTicket parkingTicket) {
        return !parkingTicketCarMap.containsKey(parkingTicket);
    }

    private boolean hasNoParkingTicket(ParkingTicket parkingTicket) {
        return Objects.isNull(parkingTicket);
    }
}
