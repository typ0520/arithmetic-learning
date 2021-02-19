package com.example;

import java.util.*;

public class yong_liang_ge_zhan_shi_xian_dui_lie_lcof {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(null, null, 1);

        root.left = new TreeNode(null, null, 2);
        root.right = new TreeNode(null, null, 3);

        root.left.left = new TreeNode(null, null, 4);
        root.left.right = new TreeNode(null, null, 5);
        root.right.right = new TreeNode(null, null, 6);

        root.left.right.left = new TreeNode(null, null, 7);
        root.left.right.right = new TreeNode(null, null, 8);

        List<List<Integer>> result = levelOrder(root);
        for (List<Integer> item : result) {
            System.out.println(item);
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
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
