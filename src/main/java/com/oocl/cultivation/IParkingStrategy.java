package com.oocl.cultivation;

public interface IParkingStrategy {
    public ParkingTicket parkCar(Car car);

    public ParkingLot getAvailableParkingLot();
}
