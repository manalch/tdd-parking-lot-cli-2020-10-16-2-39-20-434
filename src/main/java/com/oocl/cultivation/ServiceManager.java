package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceManager implements IParkingStrategy, IFetchingStrategy {
    private List<ParkingBoy> managementList = new ArrayList<>();
    private List<ParkingLot> parkingLots;

    public ServiceManager(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void addParkingBoyToManagementList(ParkingBoy parkingBoy) {
        managementList.add(parkingBoy);
    }

    public void addParkingBoyToManagementList(List<ParkingBoy> parkingBoyList) {
        managementList.addAll(parkingBoyList);
    }

    public ParkingBoy assignParkingBoy(ParkingBoy assignedParkingBoy) {
        return managementList.stream()
                .filter(parkingBoy -> parkingBoy.equals(assignedParkingBoy))
                .findFirst()
                .orElseThrow(() -> new ParkingException("Parking boy not in management list"));
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();

        return parkingLot.addCar(car);
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getAvailableParkingLotCount() > 0)
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
}
