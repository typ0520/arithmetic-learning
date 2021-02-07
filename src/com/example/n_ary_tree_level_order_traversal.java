package com.example;

import java.util.*;

public class n_ary_tree_level_order_traversal {
    public static void main(String[] args) {
        Node root = new Node();
        root.val = 1;
        root.children = new ArrayList<>(){{
            add(new Node(){{
                val = 3;
                children = new ArrayList<>(){{
                    add(new Node(){{
                        val = 5;
                    }});
                    add(new Node(){{
                        val = 6;
                    }});
                }};
            }});
            add(new Node(){{
                val = 2;
            }});
            add(new Node(){{
                val = 4;
            }});
        }};
        levelOrder(root);
    }

    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        Map<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(root, 1);
        while (!queue.isEmpty()) {
            root = queue.pop();
            int level = levelMap.get(root);
            if (result.size() < level) {
                result.add(new ArrayList<>());
            }
            List<Integer> levelList = result.get(level - 1);
            levelList.add(root.val);
            System.out.print(root.val + "  ");
            if (root.children != null) {
                for (Node node : root.children) {
                    queue.add(node);
                    levelMap.put(node, level + 1);
                }
            }
        }
        return result;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}

