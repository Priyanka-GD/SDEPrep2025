package discord;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/rabbits-in-forest/
public class RabbitsInForests {
    public int numRabbits(int[] answers) {
        if (answers.length == 0)
            return 0;

        // HashMap to keep track of the counts of each answer
        Map<Integer, Integer> answerCountMap = new HashMap<>();
        int totalRabbits = 0;

        // For each rabbit's answer
        for (int answer : answers) {
            if (answer == 0) {
                totalRabbits += 1; // 2 rabbits for color 0
                continue;
            }
            // 34 test cases passed without the else block -- input [0,0,1,1,1] didn't pass
            // If this answer hasn't been accounted for yet
            if (!answerCountMap.containsKey(answer)) {
                // Account for the rabbit giving this answer, plus the others it claims to have
                answerCountMap.put(answer, 0);// 1 : 0
                totalRabbits += (answer + 1);// rabbits = 2 + 2 = 4
            } else {
                answerCountMap.put(answer, answerCountMap.get(answer) + 1);// 1 : 1
                // If we've counted the number of rabbits with this answer, remove it
                if (answerCountMap.get(answer) == answer) { // since answer == answerCountMap.get(1), remove
                    answerCountMap.remove(answer);
                }
            }

        }
        return totalRabbits;
    }
}
