package com.oocl.cultivation;

import java.util.*;

public class ParkingBoy {
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();
        if (Objects.isNull(parkingLot)){
            throw new RuntimeException("Not enough position");
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        parkingLot.addCar(car);

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
        removeCarFromParkingLot(car);

        return car;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    private boolean hasAvailableParkingLot(ParkingLot parkingLot) {
        return parkingLot.getParkedCars().size() < parkingLot.getCapacity();
    }

    private boolean isUnrecognizedParkingTicket(ParkingTicket parkingTicket) {
        return !parkingTicketCarMap.containsKey(parkingTicket);
    }

    private boolean hasNoParkingTicket(ParkingTicket parkingTicket) {
        return Objects.isNull(parkingTicket);
    }

    private ParkingLot getAvailableParkingLot(){
        return parkingLots.stream()
                .filter(this::hasAvailableParkingLot)
                .findFirst()
                .orElse(null);
    }

    private void removeCarFromParkingLot(Car car) {
        parkingLots.stream()
                .filter(lot -> lot.getParkedCars().contains(car))
                .forEach(parkingLot -> parkingLot.removeCar(car));
    }
}
