package com.oocl.cultivation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private int capacity;
    private List<Car> parkedCars = new ArrayList<>();
    private Map<ParkingTicket, Car> parkingTicketCarMap = new ConcurrentHashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Car> getParkedCars() {
        return parkedCars;
    }

    int getAvailableParkingLotCount() {
        return capacity - parkedCars.size();
    }

    boolean hasAvailableParkingLot() {
        return getAvailableParkingLotCount() > 0;
    }

    BigDecimal getLargestAvailablePositionRate() {
        return BigDecimal.valueOf((double) getAvailableParkingLotCount() / (double) capacity);
    }

    ParkingTicket parkCar(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        parkedCars.add(car);

        return parkingTicket;
    }

    Car removeCar(ParkingTicket parkingTicket) {
        if (isUnrecognizedParkingTicket(parkingTicket)) {
            throw new ParkingException("Unrecognized Parking Ticket " + parkingTicket.hashCode());
        }
        Car car = parkingTicketCarMap.get(parkingTicket);
        parkingTicketCarMap.remove(parkingTicket);
        parkedCars.remove(car);

        return car;
    }

    private boolean isUnrecognizedParkingTicket(ParkingTicket parkingTicket) {
        return !parkingTicketCarMap.containsKey(parkingTicket);
    }
}
