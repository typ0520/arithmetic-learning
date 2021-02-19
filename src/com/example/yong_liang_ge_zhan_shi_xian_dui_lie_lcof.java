package com.example;

import java.util.*;

public class yong_liang_ge_zhan_shi_xian_dui_lie_lcof {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(null, null, 3);

        root.left = new TreeNode(null, null, 9);
        root.right = new TreeNode(null, null, 20);

        root.right.left = new TreeNode(null, null, 15);
        root.right.right = new TreeNode(null, null, 7);

        List<List<Integer>> result = levelOrder(root);
        for (List<Integer> item : result) {
            System.out.println(item);
        }
        int a = new StringBuilder("2").charAt(0);
        System.out.println(a - '0');
        int a2 = 1 + (int)Math.pow(10, 2);
        System.out.println(Math.pow(10, 2));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList();
            for (int i = queue.size(); i > 0; i--) {
                root = queue.poll();
                if (leftToRight) {
                    tmp.add(root.val);
                } else {
                    tmp.addFirst(root.val);
                }
                if (root.left != null) queue.add(root.left);
                if (root.right != null) queue.add(root.right);
            }
            leftToRight = !leftToRight;
            result.add(tmp);
        }
        return result;
    }

    static class CQueue {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        public CQueue() {

        }

        public void appendTail(int value) {
            queue.push(value);
        }

        public int deleteHead() {
            return queue.pop();
        }
    }
}
