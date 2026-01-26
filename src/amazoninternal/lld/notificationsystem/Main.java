package amazoninternal.lld.notificationsystem;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize the core service
        NotificationService notificationService = new NotificationService();

        System.out.println("--- System: Processing Requests ---");

        // 2. Simulate incoming notification requests
        // These calls are non-blocking; the main thread continues immediately
        notificationService.sendNotificationAsync(
                NotificationType.EMAIL,
                "user@example.com",
                "Your order has been shipped!"
        );

        notificationService.sendNotificationAsync(
                NotificationType.SMS,
                "+123456789",
                "Your OTP is 4432"
        );

        notificationService.sendNotificationAsync(
                NotificationType.PUSH,
                "Device_ID_9988",
                "Check out today's deals!"
        );

        System.out.println("--- System: All requests accepted and queued ---");

        // Shutdown the executor gracefully when the application ends
        // In a real app, this would be handled by the framework lifecycle
        notificationService.shutdown();
    }
}