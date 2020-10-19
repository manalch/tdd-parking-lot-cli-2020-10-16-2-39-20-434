package com.oocl.cultivation;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingBoy implements IParkingStrategy {

    private Map<ParkingTicket, Car> parkingTicketCarMap = new ConcurrentHashMap<>();
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        ParkingLot parkingLot = getAvailableParkingLot();
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        parkingLot.addCar(car);

        return parkingTicket;
    }

    @Override
    public ParkingLot getAvailableParkingLot() {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getAvailableParkingLotCount() > 0)
                .findFirst()
                .orElseThrow(() -> new ParkingException("Not enough position"));
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (hasNoParkingTicket(parkingTicket)) {
            throw new ParkingException("Please provide your parking ticket");
        }
        return getCarFromParkingLot(parkingTicket);
    }

    private Car getCarFromParkingLot(ParkingTicket parkingTicket) {
        if (isUnrecognizedParkingTicket(parkingTicket)) {
            throw new ParkingException("Unrecognized Parking Ticket " + parkingTicket.hashCode());
        }
        Car car = parkingTicketCarMap.get(parkingTicket);

        ParkingLot parkingLot = parkingLots.stream()
                .filter(lot -> lot.getParkedCars().contains(car))
                .findAny()
                .orElseThrow(() -> new ParkingException("Your car is missing"));

        parkingTicketCarMap.remove(parkingTicket);
        parkingLot.removeCar(car);

        return car;
    }

    private boolean isUnrecognizedParkingTicket(ParkingTicket parkingTicket) {
        return !parkingTicketCarMap.containsKey(parkingTicket);
    }

    private boolean hasNoParkingTicket(ParkingTicket parkingTicket) {
        return Objects.isNull(parkingTicket);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    Map<ParkingTicket, Car> getParkingTicketCarMap() {
        return parkingTicketCarMap;
    }
}
