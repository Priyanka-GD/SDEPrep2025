package amazoninternal.lld.notificationsystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NotificationService {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private static final int MAX_RETRIES = 3;

    public void sendNotificationAsync(NotificationType type, String recipient, String message) {
        executor.submit(() -> {
            Notification service = NotificationFactory.getNotificationService(type);
            int attempts = 0;
            boolean success = false;

            while (attempts < MAX_RETRIES && !success) {
                attempts++;
                success = service.send( message);

                if (!success && attempts < MAX_RETRIES) {
                    System.out.println("Retrying... Attempt " + (attempts + 1));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }

            if (!success) {
                System.err.println("Failed to send notification after " + MAX_RETRIES + " attempts.");
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
