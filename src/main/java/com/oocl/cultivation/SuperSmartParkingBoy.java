package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy implements IParkingStrategy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();
        ParkingTicket parkingTicket = new ParkingTicket();
        super.getParkingTicketCarMap().put(parkingTicket, car);
        parkingLot.addCar(car);

        return parkingTicket;
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return super.getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getAvailableParkingLotCount() > 0)
                .max(Comparator.comparing(ParkingLot::getLargestAvailablePositionRate))
                .orElseThrow(() -> new RuntimeException("Not enough position"));
    }
}
