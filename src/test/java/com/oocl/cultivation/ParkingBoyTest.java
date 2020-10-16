package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingBoyTest {
    @Test
    void should_return_a_parking_ticket_when_parking_boy_park_the_car_into_parking_lot_given_a_car() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }
}
