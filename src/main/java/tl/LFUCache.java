package tl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class LFUCache {
    public static void main(String[] args) {
        List<String> result = Arrays.stream("null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,-1,null,null,18,null,null,-1,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,-1,null,null,null,null,29,null,null,null,null,17,22,18,null,null,null,-1,null,null,null,20,null,null,null,-1,18,18,null,null,null,null,20,null,null,null,null,null,null,null".split(","))
                .collect(Collectors.toList());
        String[] options = new String[]{"LFUCache","put","put","get","put","get","get","put","get","get","get"};
        int[][] params = new int[][]{{2},{1,1},{2,2},{1},{3,3},{2},{3},{4,4},{1},{3},{4}};

        LFUCache cache = new LFUCache(params[0][0]);
        for (int i = 1; i<options.length; i++) {
            if ("put".equals(options[i])) {
                cache.put(params[i][0], params[i][1]);
            } else if ("get".equals(options[i])) {
                int res = cache.get(params[i][0]);
                if (!"null".equals(result.get(i)) && res != Integer.parseInt(result.get(i))) {
                    System.out.println(i + ": " + cache.get(params[i][0]));
                }
            }
        }
    }

    int capacity;
    int size;

    Node head;
    Node tail;

    Map<Integer, Node> cache = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        incrumentNode(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            incrumentNode(node);
            return;
        }
        if (size > capacity) {
            node = removeOne();
            cache.remove(node.key);
            size--;
        }
        node = new Node(key, value);
        cache.put(key, node);
        addToHead(node);
        incrumentNode(node);
        size++;
    }

    public void incrumentNode(Node node) {
        node.count += 1;
        Node cur = node.next;
        while (cur != tail && cur.count <= node.count) {
            cur = node.next;
        }
        removeNode(node);
        Node prev = cur.prev;
        prev.next = node;
        node.next = cur;
        cur.prev = node;
        node.prev = prev;
    }

    public Node removeOne() {
        Node cur = head.next;
        int count = cur.count;
        while (cur != tail && cur.count == count) {
            cur = cur.next;
        }
        Node res = cur.prev;
        removeNode(cur.prev);
        return res;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public static class Node {
        int key;
        int value;
        int count;
        Node prev;
        Node next;

        public Node() {}

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */