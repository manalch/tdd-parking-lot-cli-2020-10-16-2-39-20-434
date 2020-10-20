package com.oocl.cultivation;

import java.util.List;
import java.util.Objects;

public class ParkingBoy implements IParkingStrategy, IFetchingStrategy {

    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();

        return parkingLot.addCar(car);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableParkingLot)
                .findFirst()
                .orElseThrow(() -> new ParkingException("Not enough position"));
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

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
