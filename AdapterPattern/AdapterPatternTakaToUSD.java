/*
Client wants X → Adapter implements X → Adapter uses Y → Converts Y → X

Example:
- X = USD (client wants USD)
- Y = TAKA (adaptee provides Taka)
- Adapter implements USDPrice
- Adapter uses TakaPriceProvider
- Adapter converts TAKA (Y) → USD (X)
*/

// ----- Target Interface: Client wants USD (X) -----
interface USDPrice {
    double getPriceInUSD();
}

// ----- Adaptee: Provides price in Taka (Y) -----
class TakaPriceProvider {
    private double taka;

    public TakaPriceProvider(double taka) {
        this.taka = taka;
    }

    public double getTaka() {
        return taka;
    }
}

// ----- Adapter: Implements USD (X), uses Taka (Y), converts Y → X -----
class TakaToUSDAdapter implements USDPrice {

    private TakaPriceProvider takaProvider;
    private static final double BDT_TO_USD_RATE = 0.0091;

    public TakaToUSDAdapter(TakaPriceProvider takaProvider) {
        this.takaProvider = takaProvider;
    }

    @Override
    public double getPriceInUSD() {
        double taka = takaProvider.getTaka();
        double usd = taka * BDT_TO_USD_RATE;

        System.out.println("Converting TAKA (Y) → USD (X)...");
        System.out.println("TAKA " + taka + " × " + BDT_TO_USD_RATE + " = USD " + usd);

        return usd;
    }
}

// ----- Client: Wants only USD (X) -----
class DollarClient {
    private USDPrice price;

    public DollarClient(USDPrice price) {
        this.price = price;
    }

    public void showPrice() {
        System.out.println("Final Price in USD: " + price.getPriceInUSD());
    }
}

public class AdapterPatternTakaToUSD {
    public static void main(String[] args) {

        // Adaptee provides Taka (Y)
        TakaPriceProvider takaProvider = new TakaPriceProvider(5000);

        // Adapter implements USD (X), uses Taka (Y)
        USDPrice usdAdapter = new TakaToUSDAdapter(takaProvider);

        // Client wants USD (X)
        DollarClient client = new DollarClient(usdAdapter);

        client.showPrice();
    }
}
