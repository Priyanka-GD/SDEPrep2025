package amazoninternal.codingassessment;

import java.util.*;

public class KFrequentElementInStream {
    private final Map<String, Integer> frequencyMap = new HashMap<>();

    public void addPurchase(String itemId) {
        frequencyMap.put(itemId, frequencyMap.getOrDefault(itemId, 0) + 1);
    }

    public List<String> getTopK(int k) {
        if (k <= 0)
            return Collections.emptyList();

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue())
                        ? b.getKey().compareTo(a.getKey()) // Tie-breaker: alphabetical
                        : Integer.compare(a.getValue(), b.getValue())
        );

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            // If heap size exceeds k, remove the element with the lowest frequency
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Convert heap to list and reverse (since it's a min-heap)
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        Collections.reverse(result);
        return result;
    }
}

/*
* addPurchase(itemId): O(1)
* getTopK(k): O(N log k)
* Total Space: O(N + k)
* O(N): To store the frequencies of every unique item in the HashMap.
* This is the primary memory consumer.
* O(k): To store the elements in the PriorityQueue during the extraction process.
* O(k): For the result list returned to the user.
* */