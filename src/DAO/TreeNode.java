package DAO;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    protected TreeNode(int val) {
        this.val = val;
    }

    protected TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode buildTree(Integer[] root) {
        if (root == null || root.length == 0) {
            return null;
        }

        TreeNode rootNode = new TreeNode(root[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(rootNode);
        int i = 1;

        while (i < root.length) {
            TreeNode currentNode = queue.poll();
            if (root[i] != null) {
                currentNode.left = new TreeNode(root[i]);
                queue.offer(currentNode.left);
            }
            i++;
            if (i < root.length && root[i] != null) {
                currentNode.right = new TreeNode(root[i]);
                queue.offer(currentNode.right);
            }
            i++;
        }
        return rootNode;
    }
}
