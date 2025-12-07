package discord.August27;

import DAO.NumFreq;

import java.io.IOException;
import java.util.*;

public class TopKFrequentElements {
    public static void main(String args[]) throws IOException {
        int nums[] = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int result[] = topKFrequent(nums, k);
        for (int num : result)
            System.out.println(num);
    }

    private static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, NumFreq> freqMap = new HashMap<>();
        for (int num : nums) {
            if (!freqMap.containsKey(num)) {
                freqMap.put(num, new NumFreq(num));
            } else {
                freqMap.get(num).updateFreq();
            }
        }

        PriorityQueue<NumFreq> maxHeap = new PriorityQueue<>();
        for (NumFreq numFreq : freqMap.values()) {
            maxHeap.add(numFreq);
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll().number;
        }

        return result;
    }
}
