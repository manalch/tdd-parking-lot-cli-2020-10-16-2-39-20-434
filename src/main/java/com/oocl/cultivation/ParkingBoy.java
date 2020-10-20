package com.oocl.cultivation;

import java.util.List;
import java.util.Objects;

public class ParkingBoy implements IParkingStrategy {

    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableParkingLot)
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
