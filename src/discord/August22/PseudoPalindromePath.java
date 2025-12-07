package discord.August22;

import DAO.TreeNode;

import java.io.IOException;

public class PseudoPalindromePath {
    static int count = 0;

    public static void main(String args[]) throws IOException {
        //Integer arr[] = {2, 3, 1, 3, 1, null, 1};
        //Output: 2
        Integer arr[] = {2, 1, 1, 1, 3, null, null, null, null, null, 1};
        TreeNode root = TreeNode.buildTree(arr);
        getFreq(new int[10], root);
        System.out.println("Count of Paths : " + count);
    }


    private static void getFreq(int arrFreq[], TreeNode node) {
        if (node == null)
            return;
        if (node != null) {
            arrFreq[node.val]++;
            getFreq(arrFreq, node.left);
            getFreq(arrFreq, node.right);
            if (node.left == null && node.right == null) {
                if (checkForPossiblePsuedoPalindrome(arrFreq))
                    count++;
            }
            arrFreq[node.val]--;
        }
    }

    private static boolean checkForPossiblePsuedoPalindrome(int[] arrFreq) {
        int charCount = 0;
        for (int idx = 0; idx < 10; idx++) {
            if (arrFreq[idx] % 2 != 0)
                charCount++;
        }
        return charCount >= 2 ? false : true;
    }

}
