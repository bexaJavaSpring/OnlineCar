package uz.online.pdp.model;

public class OilMark {
    private static int staticId = 1;
    public final int id;
    private String mark;
    private double cost;

    public OilMark(String mark, double cost) {
        id = staticId++;

        this.mark = mark;
        this.cost = cost;
    }

    public static int getStaticId() {
        return staticId;
    }

    public static void setStaticId(int staticId) {
        OilMark.staticId = staticId;
    }

    public int getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OilMark{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", cost=" + cost +
                '}';
    }
}
