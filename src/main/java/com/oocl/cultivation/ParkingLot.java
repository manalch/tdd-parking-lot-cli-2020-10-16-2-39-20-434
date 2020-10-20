package com.oocl.cultivation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

    private Map<ParkingTicket, Car> parkingTicketCarMap = new ConcurrentHashMap<>();
    private List<Car> parkedCars = new ArrayList<>();
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
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

    BigDecimal getLargestAvailablePositionRate() {
        return BigDecimal.valueOf((double) getAvailableParkingLotCount() / (double) capacity);
    }

    ParkingTicket addCar(Car car) {
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
