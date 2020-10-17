package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    private ParkingBoy parkingBoy;
    private Car car;
    private ParkingTicket parkingTicket;
    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
        parkingLot.setCapacity(10);

        car = new Car();
        parkingTicket = new ParkingTicket();
        parkingBoy = new ParkingBoy(parkingLot);
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

    @Test
    void should_not_return_a_car_when_parking_boy_fetch_the_car_given_a_wrong_parking_ticket() {
        parkingTicket = parkingBoy.park(car);
        ParkingTicket wrongParkingTicket = new ParkingTicket();

        Throwable runtimeException = assertThrows(RuntimeException.class,
                () -> parkingBoy.fetch(wrongParkingTicket));
        assertEquals(runtimeException.getMessage()
                , "Unrecognized Parking Ticket " + wrongParkingTicket.hashCode());
    }

    @Test
    void should_not_return_a_car_when_parking_boy_is_not_given_any_ticket() {
        parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(null);

        assertNull(fetchedCar);
    }

    @Test
    void should_not_return_a_car_when_parking_boy_fetch_a_car_given_the_ticket_have_been_used_already() {
        parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertSame(fetchedCar, car);
        Throwable runtimeException = assertThrows(RuntimeException.class,
                () -> parkingBoy.fetch(parkingTicket));
        assertEquals(runtimeException.getMessage()
                , "Unrecognized Parking Ticket " + parkingTicket.hashCode());
    }

    @Test
    void should_not_return_any_parking_ticket_when_parking_lot_capacity_is_1_and_occupied_given_a_new_car_to_park() {
        parkingLot.setCapacity(1);

        ParkingTicket parkingTicket1 = parkingBoy.park(new Car());
        ParkingTicket parkingTicket2 = parkingBoy.park(new Car());

        assertNotNull(parkingTicket1);
        assertNull(parkingTicket2);
    }
}
