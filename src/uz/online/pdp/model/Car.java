package uz.online.pdp.model;

public class Car {
    private static int staticId = 1;
    public final int id;
    private String brand;
    private String model;

    private double fuelAmount;
    private double fuelPerKm;
    private boolean isStarted;
    private final double maxCapacity;

    public Car(){
        this.id = staticId++;
        this.maxCapacity = 50;
    }

    public Car(String brand, String model, double initialFuelAmount, double fuelPerKm,
               double maxCapacity){
        this.id = staticId++;

        this.brand = brand;
        this.model = model;
        this.fuelAmount = initialFuelAmount;
        this.fuelPerKm = fuelPerKm;
        isStarted = false;
        this.maxCapacity = maxCapacity;
    }

    public void start(){
        if(fuelAmount > 0){
            if (!this.isStarted) {
                this.isStarted = true;
                System.out.println("Started...");
            }
        }
        else System.out.println("Fill fuel!");
    }

    public void drive(double distance){
        if(!this.isStarted){
            return ;
        }

        if(this.fuelAmount >= distance * this.fuelPerKm){
            System.out.println("Driving...");
            this.fuelAmount -= distance * this.fuelPerKm;

            if(this.fuelAmount == 0) this.isStarted = false;
        }
        else if(this.fuelAmount > 0){
            System.out.println("Driving...");

            double goneKm = this.fuelAmount / this.fuelPerKm;
            this.fuelAmount -= this.fuelAmount;
            System.out.print(fuelAmount);

            double neededKm = distance - goneKm;
            System.out.printf("There is not enough fuel for ", neededKm);
            System.out.println("Fill fuel!");
            this.isStarted = false;
        }
        else{
            System.out.println("Fill fuel!");
            this.isStarted = false;
        }
    }

    public void stop(){
        if(this.isStarted){
            this.isStarted = false;
            System.out.println("Stopped...");
        }
    }

    public boolean fillFuel(double fuelAmount){
        if(fuelAmount <= 0 || this.fuelAmount > this.maxCapacity) return false;

        if(!(this.fuelAmount + fuelAmount > this.maxCapacity)){
            this.fuelAmount += fuelAmount;
        }
        else{
            this.fuelAmount = this.maxCapacity;
        }
        return true;
    }

    public static int getStaticId() {
        return staticId;
    }

    public static void setStaticId(int staticId) {
        Car.staticId = staticId;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public double getFuelPerKm() {
        return fuelPerKm;
    }

    public void setFuelPerKm(double fuelPerKm) {
        this.fuelPerKm = fuelPerKm;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", fuelAmount=" + fuelAmount +
                ", fuelPerKm=" + fuelPerKm +
                ", isStarted=" + isStarted +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
