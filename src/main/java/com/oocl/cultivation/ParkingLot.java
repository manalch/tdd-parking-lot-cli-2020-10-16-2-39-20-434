package com.oocl.cultivation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int capacity;
    private List<Car> parkedCars = new ArrayList<>();

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

    void addCar(Car car) {
        parkedCars.add(car);
    }

    void removeCar(Car car) {
        parkedCars.remove(car);
    }
}
