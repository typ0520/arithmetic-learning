package com.example;

import java.util.*;

public class TreeNodeTest {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(null, null, 1);
        root.left = new TreeNode(null, null, 2);
        root.right = new TreeNode(null, null, 3);
        root.left.left = new TreeNode(null, null, 4);
        root.left.right = new TreeNode(null, null, 5);
        root.right.right = new TreeNode(null, null, 6);
        root.left.right.left = new TreeNode(null, null, 7);
        root.left.right.right = new TreeNode(null, null, 8);

        System.out.println("BFS:");
        bfsTraverse(root);
        System.out.println("\n前序: ");
        preOrderTraverse1(root);
        System.out.println();
        preOrderTraverse2(root);

        System.out.println("\n中序: ");
        inOrderTraverse1(root);
        System.out.println();
        inOrderTraverse2(root);

        System.out.println("\n后序: ");
        postOrderTraverse1(root);
        System.out.println();
        postOrderTraverse2(root);
    }

    //广度优先遍历（Breadth First Search，BFS,实际上就是逐层查找，又叫层次遍历，宽度优先搜索或横向优先搜索）
    public static void bfsTraverse(TreeNode root) {
//        List<TreeNode> queue = new ArrayList<>();
//        queue.add(root);
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        //root = queue.remove(0);

        while (!queue.isEmpty()) {
            root = queue.pop();
            System.out.print(root.val + "  ");
            if (root.left != null) {
                queue.add(root.left);
            }
            if (root.right != null) {
                queue.add(root.right);
            }
        }
    }

    //深度优先遍历（Depth First Search，DFS，前序遍历)
    public static void preOrderTraverse1(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "  ");
        preOrderTraverse1(root.left);
        preOrderTraverse1(root.right);
    }

    //深度优先遍历（Depth First Search，DFS，前序遍历)
    public static void preOrderTraverse2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                System.out.print(root.val + "  ");
                stack.push(root);
                root = root.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                root = node.right;
            }
        }
    }

    //深度优先遍历（Depth First Search，DFS，中序遍历)
    public static void inOrderTraverse1(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse1(root.left);
        System.out.print(root.val + "  ");
        inOrderTraverse1(root.right);
    }

    //深度优先遍历（Depth First Search，DFS，中序遍历)
    public static void inOrderTraverse2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.val + "  ");
            root = root.right;
        }
    }

    //深度优先遍历（Depth First Search，DFS，后序遍历)
    public static void postOrderTraverse1(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraverse1(root.left);
        postOrderTraverse1(root.right);
        System.out.print(root.val + "  ");
    }

    //深度优先遍历（Depth First Search，DFS，后序遍历)
    public static void postOrderTraverse2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.getLast();
            stack.removeLast();
            if (node != null) {
                stack.addLast(node);
                stack.addLast(null);
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                if (node.left != null) {
                    stack.addLast(node.left);
                }
            } else {
                node = stack.removeLast();
                System.out.print(node.val + "  ");
            }
        }
    }
}