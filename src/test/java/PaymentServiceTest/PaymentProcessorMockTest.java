package PaymentServiceTest;

import PaymentService.PaymentProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import PaymentService.PaymentService;

import static org.mockito.Mockito.when;

public class PaymentProcessorMockTest {


    @Test
   public void whenConstructorInvokedWithInitializer_ThenMockObjectShouldBeCreated(){
        try(MockedConstruction<PaymentService> mockPaymentService =
                    Mockito.mockConstruction(PaymentService.class,(mock, context)-> {
            when(mock.processPayment()).thenReturn("Credit");
        })){
            PaymentProcessor paymentProcessor = new PaymentProcessor();
            Assert.assertEquals(1,mockPaymentService.constructed().size());
            Assert.assertEquals("Credit", paymentProcessor.processPayment());
        }
    }

    @Test
    public void whenConstructorInvokedWithoutInitializer_ThenMockObjectShouldBeCreatedWithNullFields() {
        //MockedConstruction<PaymentService> tries to make a mocked construction of the parameterized class.
        try (MockedConstruction<PaymentService> mockPaymentService = Mockito.mockConstruction(PaymentService.class)) {
            //Then we make a PaymentProcessor, which will use the Mock PaymentService
            PaymentProcessor paymentProcessor = new PaymentProcessor();
            Assert.assertEquals(1, mockPaymentService.constructed().size());
            Assert.assertNull(paymentProcessor.processPayment());
        }
    }

        @Test
        public void whenConstructorInvokedWithParameters_ThenMockObjectShouldBeCreated(){

            //Functional Interface called by lambda.
            try(MockedConstruction<PaymentService> mockPaymentService = Mockito.mockConstruction(PaymentService.class,(mock, context) -> {
                when(mock.processPayment()).thenReturn("Credit");
            })){
                PaymentProcessor paymentProcessor = new PaymentProcessor("Debit");
                Assert.assertEquals(1,mockPaymentService.constructed().size());
                Assert.assertEquals("Credit", paymentProcessor.processPayment());
            }
        }

    @Test
    public void whenDependencyInjectionIsUsed_ThenMockObjectShouldBeCreated(){
        //Use Mockito to make a mock version of  a PaymentService.
        PaymentService mockPaymentService = Mockito.mock(PaymentService.class);
        //Make our mock behavior, tell it when it calls processPayment() that we return "Online Banking"
        when(mockPaymentService.processPayment()).thenReturn("Online Banking");
        //Make a paymentProcessor with the constructor that takes a PaymentService, use our mockPaymentService
        PaymentProcessor paymentProcessor = new PaymentProcessor(mockPaymentService);
        //Check that when paymentProcessor calls processPayment,
        // it follows the mock rule we created that it will return "Online Banking"
        Assert.assertEquals("Online Banking", paymentProcessor.processPayment());
    }


}



