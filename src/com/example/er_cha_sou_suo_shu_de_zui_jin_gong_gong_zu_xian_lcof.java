package com.example;

import java.util.LinkedList;

public class er_cha_sou_suo_shu_de_zui_jin_gong_gong_zu_xian_lcof {
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(null, null, 6);
//        root.left = new TreeNode(null, null, 2);
//        root.right = new TreeNode(null, null, 8);
//        root.left.left = new TreeNode(null, null, 0);
//        root.left.right = new TreeNode(null, null, 4);
//        root.right.left = new TreeNode(null, null, 7);
//        root.right.right = new TreeNode(null, null, 9);
//        root.left.right.left = new TreeNode(null, null, 3);
//        root.left.right.right = new TreeNode(null, null, 5);
//        System.out.println(new Solution().lowestCommonAncestor(root, root.left, root.left.right));

        TreeNode root = new TreeNode(null, null, 2);
        root.left = new TreeNode(null, null, 1);
        System.out.println(new Solution().lowestCommonAncestor(root, root.left, root));
    }

    static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.addLast(root);
            if ((int)p.val > (int)q.val) {
                TreeNode temp = p;
                p = q;
                q = temp;
            }
            while (!queue.isEmpty()) {
                root = queue.removeFirst();
                if (p == root.left && q == root.right) {
                    return root;
                }
                if (p == root.left && q == root) {
                    return root;
                }
                if (p == root && q == root.right) {
                    return root;
                }
                if (root.left != null) {
                    queue.addLast(root.left);
                }
                if (root.right != null) {
                    queue.addLast(root.right);
                }
            }
            return null;
        }
    }
}
