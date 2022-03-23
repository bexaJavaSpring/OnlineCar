package uz.online.pdp.service;

import uz.online.pdp.model.OilMark;

public interface OilMarkCrud {
    boolean showOilMarks();

    boolean addOilMark(OilMark oilMark);

    boolean removeOilMark(int oilMarkId);

    boolean updateOilMark(int oilMarkId, String newMark, double newCost);
}
