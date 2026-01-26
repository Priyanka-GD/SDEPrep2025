package amazoninternal.lld.notificationsystem;

public interface Notification {
    boolean send(String message);
}

class EmailNotification implements Notification {
    public boolean send(String message) {
        System.out.println("Email sent: " + message);
        return true;
    }
}

class SMSNotification implements Notification {
    public boolean send(String message) {
        System.out.println("SMS sent: " + message);
        return true;
    }
}

class PushNotification implements Notification {
    public boolean send(String message) {
        System.out.println("Push sent: " + message);
        return true;
    }
}