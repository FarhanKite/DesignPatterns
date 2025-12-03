import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class PriceChangeEvent {
    private final int oldPrice;
    private final int newPrice;

    public PriceChangeEvent(int oldPrice, int newPrice) {
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public int getOldPrice() { return oldPrice; }
    public int getNewPrice() { return newPrice; }
}

interface Observer {
    void update(PriceChangeEvent event);
}

interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(PriceChangeEvent event);
}

class Product implements Subject {
    // Thread-safe list (best practice in industry)
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    private int price;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(PriceChangeEvent event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public void setPrice(int newPrice) {
        if (newPrice != this.price) {
            PriceChangeEvent event =
                new PriceChangeEvent(this.price, newPrice);
            this.price = newPrice;
            notifyObservers(event);
        }
    }
}

class MobileApp implements Observer {
    @Override
    public void update(PriceChangeEvent event) {
        System.out.println(
            "[Mobile] Price changed from " +
            event.getOldPrice() + " to " +
            event.getNewPrice()
        );
    }
}

class WebApp implements Observer {
    @Override
    public void update(PriceChangeEvent event) {
        System.out.println(
            "[Web] Price changed from " +
            event.getOldPrice() + " to " +
            event.getNewPrice()
        );
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        Product product = new Product();

        Observer mobile = new MobileApp();
        Observer web = new WebApp();

        product.attach(mobile);
        product.attach(web);

        product.setPrice(100);
        product.setPrice(150);
        product.setPrice(200);
    }
}
