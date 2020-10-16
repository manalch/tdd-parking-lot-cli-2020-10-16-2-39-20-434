package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class ParkingBoyTest {
    private ParkingBoy parkingBoy;
    private Car car;
    private ParkingTicket parkingTicket;

    @BeforeEach
    void setUp() {
        parkingBoy = new ParkingBoy();
        car = new Car();
        parkingTicket = new ParkingTicket();
    }

    @Test
    void should_return_a_parking_ticket_when_parking_boy_park_the_car_into_parking_lot_given_a_car() {
        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_fetched_car_when_parking_boy_fetch_the_car_given_a_parking_ticket() {
        parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertSame(car, fetchedCar);
    }

    @Test
    void should_return_fetched_cars_when_parking_boy_fetch_the_two_cars_given_parked_with_corresponding_tickets() {
        Car car1 = new Car();
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);

        Car car2 = new Car();
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);

        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }
}
