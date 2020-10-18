package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager extends ParkingBoy {

    private List<ParkingBoy> managementList = new ArrayList<>();

    public ServiceManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
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
                .orElseThrow(RuntimeException::new);
    }
}
