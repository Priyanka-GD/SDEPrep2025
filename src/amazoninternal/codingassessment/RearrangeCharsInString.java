package amazoninternal.codingassessment;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * Let n = input.length(), k = number of distinct chars.
 * Building the map: O(n)
 * Building the heap: O(k)
 * Each loop iteration:
 * poll / add on heap ⇒ O(log k)
 * Total characters appended = n
 * So:
 * Time Complexity: O(n log k)
 * Space Complexity: O(k) for map + heap (plus O(n) for output string, which is expected).
 */

public class RearrangeCharsInString {
    public static void main(String args[]) {
        String input = "aab";
        System.out.println("Re-arranged String : " + getRearrangedCharsInString(input));
    }

    private static String getRearrangedCharsInString(String input) {
        Map<Character, CharFreq> map = new HashMap<>();
        for (char ch : input.toCharArray()) {
            map.putIfAbsent(ch, new CharFreq(ch));
            map.get(ch).updateFreq();
        }

        PriorityQueue<CharFreq> maxHeap = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        maxHeap.addAll(map.values());
        StringBuilder str = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            CharFreq charFreq = maxHeap.poll();
            int len = str.length();

            if (len > 0 && str.charAt(len - 1) == charFreq.ch) {
                if (!maxHeap.isEmpty()) {
                    CharFreq nextCharFreq = maxHeap.poll();
                    str.append(nextCharFreq.ch);
                    nextCharFreq.freq--;
                    if (nextCharFreq.freq > 0) {
                        maxHeap.add(nextCharFreq);
                    }
                } else {
                    return "";
                }

                maxHeap.add(charFreq);
            } else {
                str.append(charFreq.ch);
                charFreq.freq--;
                if (charFreq.freq > 0) {
                    maxHeap.add(charFreq);
                }
            }
        }
        return str.toString();
    }
}

class CharFreq {
    char ch;
    int freq;

    public CharFreq(char ch) {
        this.ch = ch;
    }

    public void updateFreq() {
        this.freq++;
    }
}
