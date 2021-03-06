package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        return super.getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getAvailableParkingLotCount() > 0)
                .max(Comparator.comparing(ParkingLot::getLargestAvailablePositionRate))
                .orElseThrow(() -> new RuntimeException("Not enough position"))
                .parkCar(car);
    }
}
