package uber;

import java.util.ArrayList;
import java.util.List;

public class PermutationTree {

    public static void main(String[] args) {
        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Generate all permutations
        List<TreeNode> permutations = generateAllPermutations(root);

        // Print all permutations
        for (TreeNode tree : permutations) {
            printTree(tree);
            System.out.println();
        }
    }

    public static List<TreeNode> generateAllPermutations(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
            result.add(null);
            return result;
        }

        List<TreeNode> leftPermutations = generateAllPermutations(root.left);
        List<TreeNode> rightPermutations = generateAllPermutations(root.right);

        for (TreeNode left : leftPermutations) {
            for (TreeNode right : rightPermutations) {
                TreeNode newRoot1 = new TreeNode(root.val);
                newRoot1.left = left;
                newRoot1.right = right;
                result.add(newRoot1);

                TreeNode newRoot2 = new TreeNode(root.val);
                newRoot2.left = right;
                newRoot2.right = left;
                result.add(newRoot2);
            }
        }
        return result;
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

