package microsoft;

import java.util.*;
public class ErrorLog {
    public static void main(String[] args) {
        List<String> logs = Arrays.asList("e1", "e2", "e3", "e1", "e2", "e1", "e4");
        int k = 3;

        // 1. Count frequencies using a simple Map
        Map<String, Integer> counts = new HashMap<>();
        for (String log : logs) {
            counts.put(log, counts.getOrDefault(log, 0) + 1);
        }

        // 2. Use a PriorityQueue to keep only the top K
        // Min-heap: smallest frequency at the top so we can remove it
        PriorityQueue<String> heap = new PriorityQueue<>((a, b) -> {
            int freqA = counts.get(a);
            int freqB = counts.get(b);
            if (freqA == freqB) {
                return b.compareTo(a); // Alphabetical tie-breaker (reverse for min-heap)
            }
            return freqA - freqB;
        });

        for (String log : counts.keySet()) {
            heap.offer(log);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 3. Build the result list (will be in reverse order from heap)
        List<String> res = new ArrayList<>();
        while (!heap.isEmpty()) {
            res.add(heap.poll());
        }
        Collections.reverse(res);
        System.out.println(res);
    }
}
