package uz.online.pdp.service;

public interface AdminService extends  CarCrud,OilMarkCrud , PaymentTypeCrud{
    boolean showSpecificPaymentTypeHistory(int paymentTypeId);

}
