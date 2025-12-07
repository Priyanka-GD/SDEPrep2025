package uber;

import java.io.IOException;

/* A string is considered to be magical if all of its characters are the same.
You are given a string str of length N containing only lower-case alphabets a to z . You are allowed to do at max k operations on it. In each operation, you can replace any character of the given string with any other character. After at max k operations, what can be the maximum length of its substring which is also a magical string.

Constraints
1 <= N <= 5 * 10^5
str[i] = 'a' to 'z'
0 <= k <= 5 * 10^5

Example
str = "abaab"
k = 1
Output = 4
Here we can replace 2nd character with 'a'. Resultant string will be "aaaab" and here maximum length of magical substring is 4 ("aaaa")

 */

/*public class MagicalString {
    public static void main(String[] args) throws IOException {
        String input = "abaab";
        int k = 1;
        int countOfSubString = findMaxCountOfSubstring(input, k);
        System.out.println("Count of substrings: " + countOfSubString); // Output should match the expected count
    }
    public static int findMaxCountOfSubstring(String s, int k)
    {
        int len = s.length();
        int maxCount = 0;
        for(int i = 0; i < len; i++)
        {
            char ch = s.charAt(i);
            int count = 1, limit = k;
            for(int j = i + 1; j < len; j++)
            {
                if(limit < 0)
                    break;
                if(ch == s.charAt(j))
                    count++;
                else if(ch != s.charAt(j) && limit > 0)
                {
                    count++;
                    limit--;
                }
            }
            maxCount = Math.max(maxCount,count);
        }
        return maxCount;
    }
}*/
import java.util.HashMap;
import java.util.Map;

public class MagicalString {
    public static void main(String[] args) throws IOException {
        String input = "abaab";
        int k = 1;
        int countOfSubString = findMaxCountOfSubstring(input, k);
        System.out.println("Count of substrings: " + countOfSubString); // Output should match the expected count
    }

    public static int findMaxCountOfSubstring(String s, int k) {
        int len = s.length();
        int maxCount = 0;
        int left = 0;
        Map<Character, Integer> freqMap = new HashMap<>();

        for (int right = 0; right < len; right++) {
            char currentChar = s.charAt(right);
            freqMap.put(currentChar, freqMap.getOrDefault(currentChar, 0) + 1);

            // Calculate the number of replacements needed to make all characters in the window same
            int replacementsNeeded = right - left + 1 - freqMap.get(currentChar);

            // If replacements needed exceed k, move the left pointer to make replacements feasible
            while (replacementsNeeded > k) {
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                if (freqMap.get(leftChar) == 0) {
                    freqMap.remove(leftChar);
                }
                left++;
                replacementsNeeded = right - left + 1 - (freqMap.containsKey(currentChar) ? freqMap.get(currentChar) : 0);
            }

            // Update the maxCount with the current window size
            maxCount = Math.max(maxCount, right - left + 1);
        }

        return maxCount;
    }
}
