package amazonoa;

import java.util.*;

public class LongestSelfSufficientSubstring {

    public static int findLongestSelfSufficientSubstring(String S) {
        int n = S.length();
        if (n <= 1) return 0; // No proper substring possible

        // Store the first and last occurrence of each character
        Map<Character, int[]> charBounds = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            charBounds.putIfAbsent(c, new int[]{i, i});
            charBounds.get(c)[1] = i; // Update the last occurrence
        }

        // Define variables for the sliding window
        int maxLength = 0;
        int left = 0, right = 0;

        while (right < n) {
            char currentChar = S.charAt(right);
            int[] bounds = charBounds.get(currentChar);

            // Expand the window to include all occurrences of the current character
            left = Math.min(left, bounds[0]);
            right = Math.max(right, bounds[1]);

            // Check if this is a valid substring
            if (right - left + 1 >= n) {
                char leftChar = S.charAt(left);
                int[] leftCharBound = charBounds.get(leftChar);
                left = leftCharBound[1] + 1;
            }

            maxLength = Math.max(maxLength, right - left + 1);


            // Move to the next character
            right++;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // Test cases
        String S = "amazonservices";
        System.out.println(findLongestSelfSufficientSubstring(S)); // Output: 11

        String S2 = "aaa";
        System.out.println(findLongestSelfSufficientSubstring(S2)); // Output: 0

        String S3 = "abc";
        System.out.println(findLongestSelfSufficientSubstring(S3)); // Output: 2
    }
}
