package uz.online.pdp.model;

import uz.online.pdp.Role;

public class User {
    private static int staticId = 1;
    public final int id;
    public final Role role;
    private String fullName;
    private String email;
    private String password;
    private double balance;

    public User(Role role, String fullName, String email,String password) {
        this.id = staticId++;
        this.role = role;

        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.balance = 0.0;
    }

    public User(Role role, String fullName, String email, String password, double balance) {
        this.id = staticId++;
        this.role = role;

        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public double withdraw(double sum){
        if (balance < sum) {
            balance -= balance;
            return 0.0;
        }
        else {
            balance -= sum;
            return sum;
        }
    }

    public void fillBalance(double sum){
        if (sum > 0) balance += sum;
    }

    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
