package PaymentService;

public class PaymentService {
    private final String paymentMode;
    public PaymentService(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentService() {
        this.paymentMode = "Cash";
    }

    public String processPayment(){
        return this.paymentMode;
    }
}
