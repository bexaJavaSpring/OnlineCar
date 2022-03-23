package uz.online.pdp.model;

public class PaymentType {
    private static int staticId = 1;

    public final int id;
    private String name;
    private double balance;

    public PaymentType(String name ){
        id = staticId++;

        this.name = name;
        this.balance = 0.0;
    }

    public static int getStaticId() {
        return staticId;
    }

    public static void setStaticId(int staticId) {
        PaymentType.staticId = staticId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
