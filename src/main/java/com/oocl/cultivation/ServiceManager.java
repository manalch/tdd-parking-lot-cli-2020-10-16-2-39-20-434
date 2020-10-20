package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceManager implements IParkingStrategy {

    private List<IParkingStrategy> parkingStrategies = new ArrayList<>();
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public ServiceManager(List<ParkingLot> parkingLots, List<IParkingStrategy> parkingStrategies) {
        this.parkingLots.addAll(parkingLots);
        this.parkingStrategies.addAll(parkingStrategies);
    }

    public ParkingTicket assignedPark(Car car) {
        return parkingStrategies.stream()
                .findFirst()
                .orElseThrow(() -> new ParkingException("Not enough position"))
                .parkCar(car);
    }

    public Car assignedFetch(ParkingTicket parkingTicket) {
        return parkingStrategies.stream()
                .findFirst()
                .orElseThrow(() -> new ParkingException("Unrecognized Parking Ticket " + parkingTicket.hashCode()))
                .fetchCar(parkingTicket);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getAvailableParkingLotCount() > 0)
                .findFirst()
                .orElseThrow(() -> new ParkingException("Not enough position"))
                .parkCar(car);
    }

    @Override
    public Car fetchCar(ParkingTicket parkingTicket) {
        if (Objects.isNull(parkingTicket)) {
            throw new ParkingException("Please provide your parking ticket");
        }
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.removeCar(parkingTicket))
                .findAny()
                .orElseThrow(() -> new ParkingException("Your car is missing"));
    }
}
