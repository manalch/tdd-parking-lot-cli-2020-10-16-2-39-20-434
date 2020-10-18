package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));
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

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.fetch(wrongParkingTicket));
        assertEquals("Unrecognized Parking Ticket " + wrongParkingTicket.hashCode()
                , runtimeException.getMessage());
    }

    @Test
    void should_not_return_a_car_when_parking_boy_is_not_given_any_ticket() {
        parkingTicket = parkingBoy.park(car);

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.fetch(null));
        assertEquals("Please provide your parking ticket"
                , runtimeException.getMessage());
    }

    @Test
    void should_not_return_a_car_when_parking_boy_fetch_a_car_given_the_ticket_have_been_used_already() {
        parkingTicket = parkingBoy.park(car);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertSame(fetchedCar, car);
        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized Parking Ticket " + parkingTicket.hashCode()
                , runtimeException.getMessage());
    }

    @Test
    void should_not_return_any_parking_ticket_when_parking_lot_capacity_is_1_and_occupied_given_a_new_car_to_park() {
        parkingLot.setCapacity(1);

        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        assertNotNull(parkingTicket);
        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.park(new Car()));
        assertEquals("Not enough position"
                , runtimeException.getMessage());
    }

    @Test
    void should_park_to_lot_1_when_parking_boy_park_the_car_given_a_car_and_parking_lot1_is_not_yet_full() {
        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.setCapacity(10);
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setCapacity(10);

        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new ParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket);
        assertTrue(parkingLot1.getParkedCars().contains(car));
        assertFalse(parkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_park_to_lot_2_when_parking_boy_park_the_car_given_a_car_and_parking_lot1_is_full_and_parking_lot2_is_not_full() {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.setCapacity(1);
        parkingLot2.setCapacity(5);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new ParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        ParkingTicket parkingTicket3 = parkingBoy.park(car3);
        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket1);
        assertNotNull(parkingTicket2);
        assertNotNull(parkingTicket3);

        assertFalse(parkingLot1.getParkedCars().contains(car3));
        assertFalse(parkingLot1.getParkedCars().contains(car2));
        assertFalse(parkingLot2.getParkedCars().contains(car1));

        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car2));
        assertTrue(parkingLot2.getParkedCars().contains(car3));
    }

    @Test
    void should_not_park_when_parking_boy_park_the_car_given_a_car_and_parking_lot1_and_parking_lot2_is_full() {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.setCapacity(1);
        parkingLot2.setCapacity(1);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new ParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket1);
        assertNotNull(parkingTicket2);
        assertFalse(parkingLot1.getParkedCars().contains(car2));
        assertFalse(parkingLot2.getParkedCars().contains(car1));
        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car2));

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.park(car3));
        assertEquals("Not enough position"
                , runtimeException.getMessage());

        assertFalse(parkingLot1.getParkedCars().contains(car3));
        assertFalse(parkingLot2.getParkedCars().contains(car3));
    }
}