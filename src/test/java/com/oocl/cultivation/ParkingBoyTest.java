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
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private Car car1;
    private Car car2;
    private Car car3;
    private ParkingLot standardParkingBoyParkingLot1;
    private ParkingLot standardParkingBoyParkingLot2;
    private ParkingLot smartParkingBoyParkingLot1;
    private ParkingLot smartParkingBoyParkingLot2;
    private ParkingLot superSmartParkingBoyParkingLot1;
    private ParkingLot superSmartParkingBoyParkingLot2;
    private ParkingLot serviceManagerParkingLot1;
    private ParkingLot serviceManagerParkingLot2;
    private ServiceManager serviceManager;
    private ParkingBoy standardParkingBoy;
    private ParkingBoy smartParkingBoy;
    private ParkingBoy superSmartParkingBoy;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
        parkingLot1 = new ParkingLot();
        parkingLot2 = new ParkingLot();
        parkingLot.setCapacity(10);

        car = new Car();
        car1 = new Car();
        car2 = new Car();
        car3 = new Car();

        parkingTicket = new ParkingTicket();
        parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));

        standardParkingBoyParkingLot1 = new ParkingLot();
        standardParkingBoyParkingLot2 = new ParkingLot();
        smartParkingBoyParkingLot1 = new ParkingLot();
        smartParkingBoyParkingLot2 = new ParkingLot();
        superSmartParkingBoyParkingLot1 = new ParkingLot();
        superSmartParkingBoyParkingLot2 = new ParkingLot();
        serviceManagerParkingLot1 = new ParkingLot();
        serviceManagerParkingLot2 = new ParkingLot();

        standardParkingBoyParkingLot1.setCapacity(10);
        standardParkingBoyParkingLot1.setCapacity(10);

        smartParkingBoyParkingLot1.setCapacity(20);
        smartParkingBoyParkingLot2.setCapacity(20);

        superSmartParkingBoyParkingLot1.setCapacity(30);
        superSmartParkingBoyParkingLot1.setCapacity(30);

        serviceManagerParkingLot1.setCapacity(40);
        serviceManagerParkingLot2.setCapacity(40);

        List<ParkingLot> assignedParkingLotsToStandardParkingBoy = Arrays.asList(standardParkingBoyParkingLot1
                , standardParkingBoyParkingLot2);
        List<ParkingLot> assignedParkingLotsToSmartParkingBoy = Arrays.asList(smartParkingBoyParkingLot1
                , smartParkingBoyParkingLot2);
        List<ParkingLot> assignedParkingLotsToSuperSmartParkingBoy = Arrays.asList(superSmartParkingBoyParkingLot1
                , superSmartParkingBoyParkingLot2);
        List<ParkingLot> assignedParkingLotsToServiceManager = Arrays.asList(serviceManagerParkingLot1
                , serviceManagerParkingLot2);

        serviceManager = new ServiceManager(assignedParkingLotsToServiceManager);
        standardParkingBoy = new ParkingBoy(assignedParkingLotsToStandardParkingBoy);
        smartParkingBoy = new SmartParkingBoy(assignedParkingLotsToSmartParkingBoy);
        superSmartParkingBoy = new SuperSmartParkingBoy(assignedParkingLotsToSuperSmartParkingBoy);

        serviceManager.addParkingBoyToManagementList(Arrays.asList(standardParkingBoy
                , smartParkingBoy
                , superSmartParkingBoy));
    }

    @Test
    void should_return_a_parking_ticket_when_parking_boy_park_the_car_into_parking_lot_given_a_car() {
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_fetched_car_when_parking_boy_fetch_the_car_given_a_parking_ticket() {
        parkingTicket = parkingBoy.parkCar(car);

        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);

        assertSame(car, fetchedCar);
    }

    @Test
    void should_return_fetched_cars_when_parking_boy_fetch_the_two_cars_given_parked_with_corresponding_tickets() {
        Car car1 = new Car();
        ParkingTicket parkingTicket1 = parkingBoy.parkCar(car1);

        Car car2 = new Car();
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);

        Car fetchedCar1 = parkingBoy.fetchCar(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetchCar(parkingTicket2);

        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    void should_not_return_a_car_when_parking_boy_fetch_the_car_given_a_wrong_parking_ticket() {
        parkingTicket = parkingBoy.parkCar(car);
        ParkingTicket wrongParkingTicket = new ParkingTicket();

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.fetchCar(wrongParkingTicket));
        assertEquals("Unrecognized Parking Ticket " + wrongParkingTicket.hashCode()
                , runtimeException.getMessage());
    }

    @Test
    void should_not_return_a_car_when_parking_boy_is_not_given_any_ticket() {
        parkingTicket = parkingBoy.parkCar(car);

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.fetchCar(null));
        assertEquals("Please provide your parking ticket"
                , runtimeException.getMessage());
    }

    @Test
    void should_not_return_a_car_when_parking_boy_fetch_a_car_given_the_ticket_have_been_used_already() {
        parkingTicket = parkingBoy.parkCar(car);

        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);

        assertSame(fetchedCar, car);
        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.fetchCar(parkingTicket));
        assertEquals("Unrecognized Parking Ticket " + parkingTicket.hashCode()
                , runtimeException.getMessage());
    }

    @Test
    void should_not_return_any_parking_ticket_when_parking_lot_capacity_is_1_and_occupied_given_a_new_car_to_park() {
        parkingLot.setCapacity(1);

        ParkingTicket parkingTicket = parkingBoy.parkCar(new Car());
        assertNotNull(parkingTicket);
        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.parkCar(new Car()));
        assertEquals("Not enough position"
                , runtimeException.getMessage());
    }

    @Test
    void should_park_to_lot_1_when_parking_boy_park_the_car_given_a_car_and_parking_lot1_is_not_yet_full() {
        parkingLot1.setCapacity(10);
        parkingLot2.setCapacity(10);

        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new ParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket);
        assertTrue(parkingLot1.getParkedCars().contains(car));
        assertFalse(parkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_park_to_lot_2_when_parking_boy_park_the_car_given_a_car_and_parking_lot1_is_full_and_parking_lot2_is_not_full() {
        parkingLot1.setCapacity(1);
        parkingLot2.setCapacity(5);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new ParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket1 = parkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);
        ParkingTicket parkingTicket3 = parkingBoy.parkCar(car3);
        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket1);
        assertNotNull(parkingTicket2);
        assertNotNull(parkingTicket3);

        assertFalse(parkingLot2.getParkedCars().contains(car1));
        assertFalse(parkingLot1.getParkedCars().contains(car2));
        assertFalse(parkingLot1.getParkedCars().contains(car3));

        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car2));
        assertTrue(parkingLot2.getParkedCars().contains(car3));
    }

    @Test
    void should_not_park_when_parking_boy_park_the_car_given_a_car_and_parking_lot1_and_parking_lot2_is_full() {
        parkingLot1.setCapacity(1);
        parkingLot2.setCapacity(1);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new ParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket1 = parkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket1);
        assertNotNull(parkingTicket2);

        assertFalse(parkingLot2.getParkedCars().contains(car1));
        assertFalse(parkingLot1.getParkedCars().contains(car2));

        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car2));

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.parkCar(car3));
        assertEquals("Not enough position"
                , runtimeException.getMessage());

        assertFalse(parkingLot1.getParkedCars().contains(car3));
        assertFalse(parkingLot2.getParkedCars().contains(car3));
    }

    @Test
    void should_park_cars_into_more_capacity_parking_lot_when_smart_parking_boy_park_a_car_given_two_parking_lots() {
        parkingLot1.setCapacity(2);
        parkingLot2.setCapacity(2);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new SmartParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket1 = parkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);
        ParkingTicket parkingTicket3 = parkingBoy.parkCar(car3);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket1);
        assertNotNull(parkingTicket2);
        assertNotNull(parkingTicket3);

        assertFalse(parkingLot2.getParkedCars().contains(car1));
        assertFalse(parkingLot1.getParkedCars().contains(car2));
        assertFalse(parkingLot2.getParkedCars().contains(car3));

        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car2));
        assertTrue(parkingLot1.getParkedCars().contains(car3));
    }

    @Test
    void should_park_all_cars_to_parking_lot2_when_smart_parking_boy_park_a_car_given_parking_lot1_capacity_is_2_and_parking_lot2_capacity_is_10() {
        parkingLot1.setCapacity(2);
        parkingLot2.setCapacity(10);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new SmartParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket1 = parkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);
        ParkingTicket parkingTicket3 = parkingBoy.parkCar(car3);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket1);
        assertNotNull(parkingTicket2);
        assertNotNull(parkingTicket3);

        assertFalse(parkingLot1.getParkedCars().contains(car1));
        assertFalse(parkingLot1.getParkedCars().contains(car2));
        assertFalse(parkingLot1.getParkedCars().contains(car3));

        assertTrue(parkingLot2.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car2));
        assertTrue(parkingLot2.getParkedCars().contains(car3));
    }

    @Test
    void should_not_park_when_smart_parking_boy_park_the_car_given_a_car_and_parking_lot1_and_parking_lot2_is_full() {
        parkingLot1.setCapacity(1);
        parkingLot2.setCapacity(1);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new SmartParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket1 = parkingBoy.parkCar(car1);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket1);
        assertNotNull(parkingTicket2);

        assertFalse(parkingLot2.getParkedCars().contains(car1));
        assertFalse(parkingLot1.getParkedCars().contains(car2));

        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car2));

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.parkCar(car3));
        assertEquals("Not enough position"
                , runtimeException.getMessage());

        assertFalse(parkingLot1.getParkedCars().contains(car3));
        assertFalse(parkingLot2.getParkedCars().contains(car3));
    }

    @Test
    void should_return_car_when_smart_parking_boy_fetch_the_car_on_the_parking_lot_it_is_parked_given_a_parking_ticket() {
        parkingLot1.setCapacity(10);
        parkingLot2.setCapacity(20);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new SmartParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket1 = parkingBoy.parkCar(car1);
        parkingBoy.parkCar(car2);

        Car fetchedCar1 = parkingBoy.fetchCar(parkingTicket1);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertEquals(car1, fetchedCar1);
        assertFalse(parkingLot1.getParkedCars().contains(car1));
        assertFalse(parkingLot2.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car2));
    }

    @Test
    void should_park_to_parking_lot_1_when_parking_boy_park_a_car_given_that_parking_lot_1_has_higher_position_rate() {
        parkingLot1.setCapacity(15);
        parkingLot2.setCapacity(20);
        setInitiallyParkedCars(parkingLot1, 3);
        setInitiallyParkedCars(parkingLot2, 5);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new SuperSmartParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket = parkingBoy.parkCar(car1);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket);
        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertFalse(parkingLot2.getParkedCars().contains(car1));
    }

    @Test
    void should_park_to_parking_lot_1_when_parking_boy_park_a_car_given_that_parking_lot_1_has_the_same_position_rate() {
        parkingLot1.setCapacity(15);
        parkingLot2.setCapacity(30);
        setInitiallyParkedCars(parkingLot1, 5);
        setInitiallyParkedCars(parkingLot2, 10);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new SuperSmartParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket = parkingBoy.parkCar(car1);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket);
        assertTrue(parkingLot1.getParkedCars().contains(car1));
        assertFalse(parkingLot2.getParkedCars().contains(car1));
    }

    @Test
    void should_park_to_parking_lot_2_when_parking_boy_park_a_car_given_that_parking_lot_2_has_higher_position_rate() {
        parkingLot1.setCapacity(5);
        parkingLot2.setCapacity(10);
        setInitiallyParkedCars(parkingLot1, 3);
        setInitiallyParkedCars(parkingLot2, 2);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new SuperSmartParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket = parkingBoy.parkCar(car1);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket);
        assertFalse(parkingLot1.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car1));
    }

    @Test
    void should_not_park_when_super_smart_parking_boy_park_a_car_given_that_both_parking_lot1_and_parking_lot2_is_full() {
        parkingLot1.setCapacity(5);
        parkingLot2.setCapacity(10);
        setInitiallyParkedCars(parkingLot1, 3);
        setInitiallyParkedCars(parkingLot2, 2);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new SuperSmartParkingBoy(assignedParkingLots);

        ParkingTicket parkingTicket = parkingBoy.parkCar(car1);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        assertNotNull(parkingTicket);
        assertFalse(parkingLot1.getParkedCars().contains(car1));
        assertTrue(parkingLot2.getParkedCars().contains(car1));
    }

    @Test
    void should_not_get_any_ticket_when_super_smart_parking_boy_park_a_car_given_that_both_parking_lot1_and_parking_lot2_is_full() {
        parkingLot1.setCapacity(10);
        parkingLot2.setCapacity(20);
        setInitiallyParkedCars(parkingLot1, 10);
        setInitiallyParkedCars(parkingLot2, 20);
        List<ParkingLot> assignedParkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy = new SuperSmartParkingBoy(assignedParkingLots);

        parkingLot1 = parkingBoy.getParkingLots().get(0);
        parkingLot2 = parkingBoy.getParkingLots().get(1);

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> parkingBoy.parkCar(car1));
        assertEquals("Not enough position"
                , runtimeException.getMessage());

        assertFalse(parkingLot1.getParkedCars().contains(car1));
        assertFalse(parkingLot2.getParkedCars().contains(car1));
    }

    @Test
    void should_park_to_parking_lot_of_assigned_parking_boy_by_service_manager_when_smart_parking_boy_is_assigned_to_park_given_a_car() {
        smartParkingBoyParkingLot1.setCapacity(20);
        smartParkingBoyParkingLot2.setCapacity(20);
        setInitiallyParkedCars(smartParkingBoyParkingLot1, 15);
        setInitiallyParkedCars(smartParkingBoyParkingLot2, 10);

        ParkingBoy assignedParkingBoy = serviceManager.assignParkingBoy(smartParkingBoy);

        ParkingTicket parkingTicket = assignedParkingBoy.parkCar(car);

        assertNotNull(parkingTicket);
        assertTrue(smartParkingBoyParkingLot2.getParkedCars().contains(car));

        assertFalse(standardParkingBoyParkingLot1.getParkedCars().contains(car));
        assertFalse(standardParkingBoyParkingLot2.getParkedCars().contains(car));
        assertFalse(smartParkingBoyParkingLot1.getParkedCars().contains(car));
        assertFalse(superSmartParkingBoyParkingLot1.getParkedCars().contains(car));
        assertFalse(superSmartParkingBoyParkingLot2.getParkedCars().contains(car));
        assertFalse(serviceManagerParkingLot1.getParkedCars().contains(car));
        assertFalse(serviceManagerParkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_park_to_parking_lot_of_assigned_parking_boy_by_service_manager_when_standard_parking_boy_is_assigned_to_park_given_a_car() {

        ParkingBoy assignedParkingBoy = serviceManager.assignParkingBoy(standardParkingBoy);

        ParkingTicket parkingTicket = assignedParkingBoy.parkCar(car);

        assertNotNull(parkingTicket);
        assertTrue(standardParkingBoyParkingLot1.getParkedCars().contains(car));

        assertFalse(standardParkingBoyParkingLot2.getParkedCars().contains(car));
        assertFalse(smartParkingBoyParkingLot1.getParkedCars().contains(car));
        assertFalse(smartParkingBoyParkingLot2.getParkedCars().contains(car));
        assertFalse(superSmartParkingBoyParkingLot1.getParkedCars().contains(car));
        assertFalse(superSmartParkingBoyParkingLot2.getParkedCars().contains(car));
        assertFalse(serviceManagerParkingLot1.getParkedCars().contains(car));
        assertFalse(serviceManagerParkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_park_to_parking_lot_of_assigned_parking_boy_by_service_manager_when_super_smart_parking_boy_is_assigned_to_park_given_a_car() {
        superSmartParkingBoyParkingLot1.setCapacity(30);
        superSmartParkingBoyParkingLot2.setCapacity(15);
        setInitiallyParkedCars(superSmartParkingBoyParkingLot1, 10);
        setInitiallyParkedCars(superSmartParkingBoyParkingLot2, 2);

        ParkingBoy assignedParkingBoy = serviceManager.assignParkingBoy(superSmartParkingBoy);

        ParkingTicket parkingTicket = assignedParkingBoy.parkCar(car);

        assertNotNull(parkingTicket);
        assertTrue(superSmartParkingBoyParkingLot2.getParkedCars().contains(car));

        assertFalse(standardParkingBoyParkingLot1.getParkedCars().contains(car));
        assertFalse(standardParkingBoyParkingLot2.getParkedCars().contains(car));
        assertFalse(smartParkingBoyParkingLot1.getParkedCars().contains(car));
        assertFalse(smartParkingBoyParkingLot2.getParkedCars().contains(car));
        assertFalse(superSmartParkingBoyParkingLot1.getParkedCars().contains(car));
        assertFalse(serviceManagerParkingLot1.getParkedCars().contains(car));
        assertFalse(serviceManagerParkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_park_to_service_manager_parking_lot_when_service_manager_parks_a_car_given_a_car() {
        serviceManagerParkingLot1.setCapacity(40);
        serviceManagerParkingLot2.setCapacity(40);
        setInitiallyParkedCars(serviceManagerParkingLot1, 40);

        List<ParkingLot> assignedParkingLotsToServiceManager = Arrays.asList(serviceManagerParkingLot1
                , serviceManagerParkingLot2);

        ServiceManager serviceManager = new ServiceManager(assignedParkingLotsToServiceManager);

        ParkingTicket parkingTicket = serviceManager.parkCar(car);

        assertNotNull(parkingTicket);
        assertTrue(serviceManagerParkingLot2.getParkedCars().contains(car));
    }

    @Test
    void should_throw_unrecognized_parking_ticket_when_assigned_parking_boy_fetch_a_car_given_a_wrong_parking_ticket() {
        ParkingTicket wrongParkingTicket = new ParkingTicket();

        ParkingBoy assignedParkingBoy = serviceManager.assignParkingBoy(superSmartParkingBoy);

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> assignedParkingBoy.fetchCar(wrongParkingTicket));
        assertEquals("Unrecognized Parking Ticket " + wrongParkingTicket.hashCode()
                , runtimeException.getMessage());
    }

    @Test
    void should_throw_message_please_provide_your_parking_ticket_when_assigned_parking_boy_fetch_a_car_given_a_no_parking_ticket() {
        ParkingBoy assignedParkingBoy = serviceManager.assignParkingBoy(superSmartParkingBoy);

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> assignedParkingBoy.fetchCar(null));
        assertEquals("Please provide your parking ticket"
                , runtimeException.getMessage());
    }

    @Test
    void should_throw_not_enough_position_parking_ticket_when_assigned_parking_boy_park_a_car_given_a_no_parking_lot_is_full() {
        superSmartParkingBoyParkingLot1.setCapacity(10);
        superSmartParkingBoyParkingLot2.setCapacity(10);
        setInitiallyParkedCars(superSmartParkingBoyParkingLot1, 10);
        setInitiallyParkedCars(superSmartParkingBoyParkingLot2, 10);

        ParkingBoy assignedParkingBoy = serviceManager.assignParkingBoy(superSmartParkingBoy);

        Throwable runtimeException = assertThrows(RuntimeException.class
                , () -> assignedParkingBoy.parkCar(new Car()));
        assertEquals("Not enough position"
                , runtimeException.getMessage());
    }

    private void setInitiallyParkedCars(ParkingLot parkingLot, int numberOfCarsParked) {
        for (int i = 0; i < numberOfCarsParked; i++) {
            parkingLot.addCar(new Car());
        }
    }
}