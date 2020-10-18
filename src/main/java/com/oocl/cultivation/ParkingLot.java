package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
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

    public int getAvailableParkingLotCount() {
        return capacity - parkedCars.size();
    }

    public void addCar(Car car) {
        parkedCars.add(car);
    }

    public void removeCar(Car car) {
        parkedCars.remove(car);
    }
}
