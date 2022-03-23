package uz.online.pdp.service;

import uz.online.pdp.model.Car;

public interface CarCrud {
    boolean showCars();

    boolean addCar(Car car);

    boolean removeCar(int carId);

    boolean updateCar(int carId, String newModel, String newBrand);
}
