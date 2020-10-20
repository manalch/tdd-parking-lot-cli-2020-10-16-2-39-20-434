package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceManagerTest {

    private ServiceManager serviceManager;
    private ParkingBoy standardParkingBoy;
    private ParkingBoy smartParkingBoy;
    private ParkingBoy superSmartParkingBoy;

    @BeforeEach
    void setUp() {
        ParkingLot assignedParkingLotToServiceManager = new ParkingLot(10);
        ParkingLot assignedParkingLotToStandardParkingBoy = new ParkingLot(10);
        ParkingLot assignedParkingLotToSmartParkingBoy = new ParkingLot(10);
        ParkingLot assignedParkingLotToSuperSmartParkingBoy = new ParkingLot(10);

        standardParkingBoy = new ParkingBoy(Collections.singletonList(assignedParkingLotToStandardParkingBoy));
        smartParkingBoy = new SmartParkingBoy(Collections.singletonList(assignedParkingLotToSmartParkingBoy));
        superSmartParkingBoy = new SuperSmartParkingBoy(Collections.singletonList(assignedParkingLotToSuperSmartParkingBoy));
        serviceManager = new ServiceManager(Collections.singletonList(assignedParkingLotToServiceManager)
                , Collections.singletonList(standardParkingBoy));
    }

    @Test
    void should_return_parking_ticket_when_assign_parking_boy_park_car() {
        assertNotNull(serviceManager);
        assertNotNull(standardParkingBoy);
        assertNotNull(smartParkingBoy);
        assertNotNull(superSmartParkingBoy);
    }
}