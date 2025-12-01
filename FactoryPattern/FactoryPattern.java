interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}

class NotificationFactory {

    public static Notification createNotification(String type) {

        if (type == null || type.isEmpty()) {
            return null;
        }

        switch (type) {
            case "SMS":
                return new SMSNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type");
        }
    }
}

public class FactoryPattern {
    public static void main(String[] args) {

        Notification email = NotificationFactory.createNotification("EMAIL");
        email.send("Hello Factory Pattern!");

        Notification sms = NotificationFactory.createNotification("SMS");
        sms.send("Hello SMS!");

        Notification push = NotificationFactory.createNotification("PUSH");
        push.send("Hello Push Notification!");
    }
}
