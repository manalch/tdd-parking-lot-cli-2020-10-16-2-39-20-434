package com.oocl.cultivation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private Integer capacity;
    private List<Car> parkedCars = new ArrayList<>();

    public ParkingLot() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Car> getParkedCars() {
        return parkedCars;
    }

    Integer getAvailableParkingLotCount() {
        return capacity - parkedCars.size();
    }

    BigDecimal getLargestAvailablePositionRate() {
        return BigDecimal.valueOf(getAvailableParkingLotCount().doubleValue() / capacity.doubleValue());
    }

    void addCar(Car car) {
        parkedCars.add(car);
    }

    void removeCar(Car car) {
        parkedCars.remove(car);
    }
}
