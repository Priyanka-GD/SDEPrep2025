package multithreading.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
/*It means that sometimes HashMap#put() will internally call HashMap#resize()
to make the underlying array bigger.

HashMap#resize() assigns the table field a new empty array with a bigger capacity
and populates it with the old items. While this population happens, the underlying
array doesn't contain all of the old items and calling HashMap#get() with an existing
key may return null.

The following code demonstrates that. You are very likely to get the exception that
 will mean the HashMap is not thread safe. I chose the target key as 65 535 - this way
 it will be the last element in the array, thus being the last element during re-population
 which increases the possibility of getting null on HashMap#get() (to see why,
 see HashMap#put() implementation).*/

public class HashMapThreadSafetyTest {
    public static void main(String[] args) {
        final Map<Integer, String> map = new HashMap<>();

        // Define a key and value
        final Integer targetKey = 65535;  // 65,535 in decimal
        final String targetValue = "v";
        map.put(targetKey, targetValue);

        // Start a new thread to add values to the map
        new Thread(() -> {
            for (int key = 0; key < targetKey; key++) {
                map.put(key, "someValue");
            }
        }).start();

        // Continuously check if the original value has been changed
        while (true) {
            if (!targetValue.equals(map.get(targetKey))) {
                throw new RuntimeException("HashMap is not thread safe.");
            }
        }
    }
}
