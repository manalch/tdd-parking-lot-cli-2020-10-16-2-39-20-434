package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot(10);
    }

    @Test
    void should_return_10_capacity_when_it_is_set_given_10_capacity() {
        parkingLot = new ParkingLot(10);

        assertEquals(10, parkingLot.getCapacity());
    }

    @Test
    void should_return_avaiable_count_when_getAvailableParkingLotCount_is_called_given_capacity_and_parkedCars() {
        parkingLot = new ParkingLot(3);
        parkingLot.parkCar(new Car());

        assertEquals(2, parkingLot.getAvailableParkingLotCount());
    }

    @Test
    void should_return_available_position_rate_when_getLargestAvailablePositonRate_is_called_given_available_count_and_capacity() {
        parkingLot = new ParkingLot(10);
        parkingLot.parkCar(new Car());

        BigDecimal largestAvailablePositionRate = parkingLot.getLargestAvailablePositionRate();

        assertEquals(BigDecimal.valueOf(0.9), largestAvailablePositionRate);
    }

    @Test
    void should_park_car_when_parking_lot_add_a_car_given_a_car() {
        Car car = new Car();

        parkingLot.parkCar(car);

        assertTrue(parkingLot.getParkedCars().contains(car));
    }
}