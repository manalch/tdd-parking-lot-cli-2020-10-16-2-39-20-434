package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyTest {

    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;

    @BeforeEach
    void setUp() {
        parkingLot1 = new ParkingLot(10);
        parkingLot2 = new ParkingLot(30);
    }

    @Test
    void should_return_larger_available_parking_lot_when_smart_parking_boy_get_available_parking_lot_given_parking_lots() {
        setInitiallyParkedCars(parkingLot1, 2);
        setInitiallyParkedCars(parkingLot2, 15);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2));

        ParkingLot availableParkingLot = superSmartParkingBoy.getAvailableParkingLot();

        assertSame(parkingLot1, availableParkingLot);
    }

    @Test
    void should_throw_not_enough_positon_when_getAvailableParkingLot_given_both_parking_lots_are_full() {
        setInitiallyParkedCars(parkingLot1, 10);
        setInitiallyParkedCars(parkingLot2, 30);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2));

        Throwable runtimeException = assertThrows(RuntimeException.class
                , superSmartParkingBoy::getAvailableParkingLot);
        assertEquals("Not enough position"
                , runtimeException.getMessage());
    }

    private void setInitiallyParkedCars(ParkingLot parkingLot, int numberOfCarsParked) {
        for (int i = 0; i < numberOfCarsParked; i++) {
            parkingLot.addCar(new Car());
        }
    }
}