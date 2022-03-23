package uz.online.pdp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class PaymentHistory {
    private static int staticId = 1;

    public final int id;
    public final String paymentType;
    public final double sum;
    public final LocalDate date;
    public final LocalTime time;

    public PaymentHistory(String paymentType, double sum, LocalDate date, LocalTime time) {
        id = staticId++;
        this.paymentType = paymentType;
        this.sum = sum;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "PaymentHistory{" +
                "id=" + id +
                ", paymentType='" + paymentType + '\'' +
                ", sum=" + sum +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
