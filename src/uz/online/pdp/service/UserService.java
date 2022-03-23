package uz.online.pdp.service;

public interface UserService {
    boolean showCars();

    boolean chooseCar(int carId);

    void start();

    void stop();

    void drive(double distance);

    boolean fillTank(int paymentTypeId, int oilMarkId, int litreQuantity); // 3-holat

    boolean fillBalance(int payTypeId, double sum);

    boolean showOilMarks();

    boolean showPaymentTypes();
}
