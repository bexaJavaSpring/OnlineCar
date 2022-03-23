package uz.online.pdp.service;

import uz.online.pdp.model.PaymentType;

public interface PaymentTypeCrud {

    boolean showPaymentTypes();

    boolean addPaymentType(PaymentType paymentType);

    boolean removePaymentType(int paymentTypeId);

    boolean updatePaymentType(int paymentTypeId, int newFee);
}
