package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;

    @BeforeEach
    void setUp() {
        parkingLot1 = new ParkingLot();
        parkingLot2 = new ParkingLot();
        parkingLot1.setCapacity(10);
        parkingLot2.setCapacity(20);
    }

    @Test
    void should_return_larger_available_parking_lot_when_smart_parking_boy_get_available_parking_lot_given_parking_lots() {
        setInitiallyParkedCars(parkingLot1, 5);
        setInitiallyParkedCars(parkingLot2, 10);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2));

        ParkingLot availableParkingLot = smartParkingBoy.getAvailableParkingLot();

        assertSame(parkingLot2, availableParkingLot);
    }

    @Test
    void should_throw_not_enough_positon_when_getAvailableParkingLot_given_both_parking_lots_are_full() {
        setInitiallyParkedCars(parkingLot1, 10);
        setInitiallyParkedCars(parkingLot2, 20);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2));

        Throwable runtimeException = assertThrows(RuntimeException.class
                , smartParkingBoy::getAvailableParkingLot);
        assertEquals("Not enough position"
                , runtimeException.getMessage());
    }

    private void setInitiallyParkedCars(ParkingLot parkingLot, int numberOfCarsParked) {
        for (int i = 0; i < numberOfCarsParked; i++) {
            parkingLot.addCar(new Car());
        }
    }
}