package discord.Sep6;

import java.io.IOException;

public class RansomNote {
    public static void main(String args[]) throws IOException {
        String ransomNote = "aa";
        String magazine = "ab";
        System.out.println(canConstruct(ransomNote, magazine));
    }

    private static boolean canConstruct(String ransomNote, String magazine) {
        int magazineCharCount[] = new int[26];

        for (char ch : magazine.toCharArray())
            magazineCharCount[ch - 'a']++;

        for (char ch : ransomNote.toCharArray()) {
            if (magazineCharCount[ch - 'a'] > 0)
                magazineCharCount[ch - 'a']--;
            else
                return false;
        }
        return true;
    }
}
