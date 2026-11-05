import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartShippingCalculatorTest {
    @Test void cobraPorKiloEnBogota() {
        Order o = new Order("o1", "c1", "Bogotá", 100, 4);
        assertEquals(10.0, new SmartShippingCalculator().calculate(o));
    }

    @Test void sumaRecargoFueraDeBogota() {
        Order o = new Order("o1", "c1", "Medellín", 100, 4);
        assertEquals(15.0, new SmartShippingCalculator().calculate(o));
    }

    @Test void checkoutFuncionaConCualquierCalculador() {
        Checkout checkout = new Checkout(o -> 7.0);
        assertEquals(107.0,
                checkout.total(new Order("o1", "c1", "Medellín", 100, 4)));
    }
}