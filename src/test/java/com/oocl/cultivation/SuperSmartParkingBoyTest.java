package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SuperSmartParkingBoyTest {

    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;

    @BeforeEach
    void setUp() {
        parkingLot1 = new ParkingLot(10);
        parkingLot2 = new ParkingLot(30);
    }

    @Test
    void should_throw_not_enough_positon_when_getAvailableParkingLot_given_both_parking_lots_are_full() {
        setInitiallyParkedCars(parkingLot1, 10);
        setInitiallyParkedCars(parkingLot2, 30);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2));

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> superSmartParkingBoy.parkCar(new Car()));
        assertEquals("Not enough position"
                , runtimeException.getMessage());
    }

    private void setInitiallyParkedCars(ParkingLot parkingLot, int numberOfCarsParked) {
        IntStream.range(0, numberOfCarsParked)
                .forEach(value -> parkingLot.parkCar(new Car()));
    }
}