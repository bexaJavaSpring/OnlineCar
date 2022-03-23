package uz.online.pdp.service;

public interface CarAbilities {
    void start();

    void drive(double distance);

    void stop();

    boolean fillFuel(double fuelAmount);
}
