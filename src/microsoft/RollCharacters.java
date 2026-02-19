package microsoft;

import java.util.ArrayList;
import java.util.List;

public class RollCharacters {
    public static void main(String[] args) {
        System.out.println(rollCharacters("abz", new int[]{3, 2, 1}));
        System.out.println(rollCharacters("vwxyz", new int[]{1, 2, 3, 4, 5}));
        System.out.println(rollCharacters("abz", new int[]{3}));
    }
    /*public static String rollCharacters(String str, int[] rolls){
        StringBuilder stringBuilder = new StringBuilder();
        List<Character> list = new ArrayList<>();
        for(char ch : str.toCharArray()){
            list.add(ch);
        }
        for(int roll : rolls){
            for(int idx = 0; idx < roll; idx++){
                char currCh = list.get(idx);
                if(currCh == 'z') {
                    currCh = 'a';
                } else {
                    currCh = (char) (currCh + 1); // Cast required here
                }
                list.set(idx, currCh);
            }
        }
        for(char ch : list){
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }*/
        public static String rollCharacters(String str, int[] rolls) {
            int n = str.length();
            // rollCount[i] will store how many times str[i] should be rolled
            int[] rollCount = new int[n];

            // 1. Mark the frequency of rolls
            // If rolls[i] = 3, it means index 0, 1, and 2 get a +1
            for (int roll : rolls) {
                // We only care about rolls within the string bounds
                int limit = Math.min(roll, n);
                if (limit > 0) {
                    rollCount[limit - 1]++;
                }
            }

            // 2. Accumulate rolls from right to left
            // A roll of 5 affects index 4 AND everything before it.
            // A roll of 3 affects index 2 AND everything before it.
            for (int i = n - 2; i >= 0; i--) {
                rollCount[i] += rollCount[i + 1];
            }

            // 3. Apply the rolls to the characters
            char[] chars = str.toCharArray();
            for (int i = 0; i < n; i++) {
                int totalRolls = rollCount[i];
                // Use modulo 26 to handle character wrapping
                int originalPos = chars[i] - 'a';
                int newPos = (originalPos + totalRolls) % 26;
                chars[i] = (char) ('a' + newPos);
            }

            return new String(chars);
        }
    }

