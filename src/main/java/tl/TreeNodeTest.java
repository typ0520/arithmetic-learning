package tl;

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
        bfsTraverse1(root);
        System.out.println();
        bfsTraverse2(root);

        System.out.println("----------------------");
        System.out.println("DFS:");
        System.out.println("\n前序: ");
        preOrderTraverse1(root);
        System.out.println();
        preOrderTraverse2(root);
        System.out.println();
        preOrderTraverse3(root);

        System.out.println("\n中序: ");
        inOrderTraverse1(root);
        System.out.println();
        inOrderTraverse2(root);
        System.out.println();
        inOrderTraverse3(root);

        System.out.println("\n后序: ");
        postOrderTraverse1(root);
        System.out.println();
        postOrderTraverse2(root);
        System.out.println();
        postOrderTraverse3(root);
    }

    //广度优先遍历（Breadth First Search，BFS,实际上就是逐层查找，又叫层次遍历，宽度优先搜索或横向优先搜索）
    public static void bfsTraverse1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            root = queue.poll();
            System.out.print(root.val + "  ");
            if (root.left != null) {
                queue.add(root.left);
            }
            if (root.right != null) {
                queue.add(root.right);
            }
        }
    }

    //广度优先遍历（Breadth First Search，BFS,实际上就是逐层查找，又叫层次遍历，宽度优先搜索或横向优先搜索）
    public static List<List<Integer>> bfsTraverse2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0 ; i--) {
                root = queue.poll();
                System.out.print(root.val + "  ");
                tmp.add(root.val);
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            maxDepth++;
            res.add(tmp);
        }
        System.out.println("");
        for (List<Integer> item : res) {
            System.out.println(item);
        }
        System.out.println("最大深度: " + maxDepth);
        return res;
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
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                System.out.print(root.val + "  ");
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                root = node.right;
            }
        }
    }

    //深度优先遍历（Depth First Search，DFS，前序遍历)
    public static void preOrderTraverse3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pollLast();
            System.out.print(root.val + "  ");
            if (root.right != null) stack.add(root.right);
            if (root.left != null) stack.add(root.left);
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
        Stack<TreeNode> stack = new Stack<>();
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

    //深度优先遍历（Depth First Search，DFS，中序遍历)
    public static void inOrderTraverse3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pollLast();
            if (root != null) {
                if (root.right != null) stack.add(root.right);
                stack.add(root);
                stack.add(null);
                if (root.left != null) stack.add(root.left);
            } else {
                root = stack.pollLast();
                System.out.print(root.val + "  ");
            }
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

    }

    //深度优先遍历（Depth First Search，DFS，后序遍历)
    public static void postOrderTraverse3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pollLast();
            if (root != null) {
                stack.add(root);
                stack.add(null);
                if (root.right != null) stack.add(root.right);
                if (root.left != null) stack.add(root.left);
            } else {
                root = stack.pollLast();
                System.out.print(root.val + "  ");
            }
        }
    }
}