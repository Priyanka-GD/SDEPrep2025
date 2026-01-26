package amazoninternal.lld.notificationsystem;

class NotificationFactory {
    public static Notification getNotificationService(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailNotification();
            case SMS -> new SMSNotification();
            case PUSH -> new PushNotification();
        };
    }
}
