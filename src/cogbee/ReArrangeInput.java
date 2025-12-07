package cogbee;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReArrangeInput {
    // a-> 3
    // c-> 1
    //b -> 1

    // a, c, a, b
    public static void main(String args[]) {
        String input = "bcaaa";
        System.out.println(getRearrangedStr(input));
    }

    private static String getRearrangedStr(String input) {
        Map<Character, Integer> map = new HashMap<>();

        for (int idx = 0; idx < input.length(); idx++) {
            char ch = input.charAt(idx);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
                (pair1, pair2) -> pair2.freq - pair1.freq
        );

        for (char key : map.keySet()) {
            maxHeap.add(new Pair(key, map.get(key)));
        }
        // a -> 3, b -> 1, c -> 1
        StringBuilder answer = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            Pair currPair = maxHeap.poll();// a -> 2
            int len = answer.length();

            if (len > 0 && answer.charAt(len - 1) == currPair.ch) {
                if (!maxHeap.isEmpty()) {
                    Pair nextPair = maxHeap.poll(); // b->1
                    answer.append(nextPair.ch);
                    nextPair.freq--;
                    if (nextPair.freq > 0)
                        maxHeap.add(nextPair);
                } else {
                    return "Invalid String";
                }
                maxHeap.add(currPair);
            } else {
                answer.append(currPair.ch);
                currPair.freq--;
                if (currPair.freq > 0)
                    maxHeap.add(currPair);
            }
        }
        return answer.toString();
    }

}

class Pair {
    char ch;
    int freq;

    public Pair(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}
