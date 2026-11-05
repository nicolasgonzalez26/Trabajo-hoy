public class ShippingCalculatorProxy implements ShippingCalculator {
    private final ShippingCalculator legacy;
    private final ShippingCalculator smart;
    private final FeatureFlags flags;

    public ShippingCalculatorProxy(ShippingCalculator legacy,
                                   ShippingCalculator smart,
                                   FeatureFlags flags) {
        this.legacy = legacy;
        this.smart = smart;
        this.flags = flags;
    }

    @Override
public double calculate(Order order) {
    boolean useSmart = flags.isEnabled("smart-shipping");
    long start = System.nanoTime();

    double result = useSmart ? smart.calculate(order) : legacy.calculate(order);

    long ms = (System.nanoTime() - start) / 1_000_000;
    System.out.println("[Proxy] respondió " + (useSmart ? "smart" : "legacy")
            + " en " + ms + " ms");
    return result;
}
}