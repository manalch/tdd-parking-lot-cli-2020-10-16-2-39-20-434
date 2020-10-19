package com.oocl.cultivation;

public interface IParkingStrategy {

    ParkingTicket parkCar(Car car);

    ParkingLot getAvailableParkingLot();
}
