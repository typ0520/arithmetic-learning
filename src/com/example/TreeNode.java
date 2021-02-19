package com.example;

public class TreeNode {
    TreeNode left;
    TreeNode right;
    Integer val;

    public TreeNode(TreeNode left, TreeNode right, Integer val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}