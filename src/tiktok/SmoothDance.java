package tiktok;

import java.io.IOException;

public class SmoothDance {
    public static void main(String args[])throws IOException {
        int n = 5;
        String s = "01010";
        int k = 2;
        int maxLen = countForMaxDanceLen(n,s,k);

       System.out.println("Maximum Length " +  maxLen);
    }

    private static int countForMaxDanceLen(int n, String s, int k) {
        int right = 0, left = 0, countZero = 0, maxLen = -1;
        while(right < n)
        {
            char ch = s.charAt(right);

            if(ch == '0')
                countZero++;

            if(countZero > k)
            {
                if(s.charAt(left) == '0')
                    countZero--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
