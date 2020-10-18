package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceManagerTest {

    private ServiceManager serviceManager;
    private ParkingBoy standardParkingBoy;
    private ParkingBoy smartParkingBoy;
    private ParkingBoy superSmartParkingBoy;

    @BeforeEach
    void setUp() {
        ParkingLot assignedParkingLotToServiceManager = new ParkingLot();
        ParkingLot assignedParkingLotToStandardParkingBoy = new ParkingLot();
        ParkingLot assignedParkingLotToSmartParkingBoy = new ParkingLot();
        ParkingLot assignedParkingLotToSuperSmartParkingBoy = new ParkingLot();

        serviceManager = new ServiceManager(Collections.singletonList(assignedParkingLotToServiceManager));
        standardParkingBoy = new ParkingBoy(Collections.singletonList(assignedParkingLotToStandardParkingBoy));
        smartParkingBoy = new SmartParkingBoy(Collections.singletonList(assignedParkingLotToSmartParkingBoy));
        superSmartParkingBoy = new SuperSmartParkingBoy(Collections.singletonList(assignedParkingLotToSuperSmartParkingBoy));
    }

    @Test
    void should_return_assigned_smart_parking_boy_when_parking_boy_is_assigned_given_a_smart_parking_boy() {
        serviceManager.addParkingBoyToManagementList(Arrays.asList(standardParkingBoy, smartParkingBoy, superSmartParkingBoy));

        ParkingBoy assignedParkingBoy = serviceManager.assignParkingBoy(smartParkingBoy);

        assertSame(smartParkingBoy, assignedParkingBoy);
    }

    @Test
    void should_throw_fail_to_do_task_when_parking_boy_when_parking_boy_assigned_cant_do_his_task() {
        serviceManager.addParkingBoyToManagementList(standardParkingBoy);

        assertThrows(RuntimeException.class, () -> {
            serviceManager.assignParkingBoy(superSmartParkingBoy);
        });
    }
}