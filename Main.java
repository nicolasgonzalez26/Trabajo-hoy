public class Main {
    public static void main(String[] args) {
        FeatureFlags flags = new FeatureFlags();
        flags.enable("smart-shipping");   // flag encendido

        ShippingCalculator proxy = new ShippingCalculatorProxy(
                new LegacyShippingCalculator(),
                new SmartShippingCalculator(),
                flags);

        Checkout checkout = new Checkout(proxy);
        Order order = new Order("o1", "c1", "Medellín", 100, 4);
        System.out.println("Total: " + checkout.total(order)); // 100 + 15 = 115
    }
}