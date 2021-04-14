package com.example;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    public static void main(String[] args) {
//        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(1));       // 返回  1
//        cache.put(3, 3);    // 该操作会使得密钥 2 作废
//        System.out.println(cache.get(2));       // 返回 -1 (未找到)
//        cache.put(4, 4);    // 该操作会使得密钥 1 作废
//        System.out.println(cache.get(1));       // 返回 -1 (未找到)
//        System.out.println(cache.get(3));       // 返回  3
//        System.out.println(cache.get(4));       // 返回  4

        String[] options = new String[]{"LRUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
        int[][] params = new int[][]{{10},{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};

        LRUCache cache = new LRUCache(params[0][0]);
        for (int i = 1; i<options.length; i++) {
            if (i == 28) {
                System.out.println("size: " + cache.size + " ,head.next: " + cache.head.next);
            }
            if ("put".equals(options[i])) {
                cache.put(params[i][0], params[i][1]);
            } else if ("get".equals(options[i])) {
                System.out.println(i + ": " + cache.get(params[i][0]));
            }
        }
    }

    int capacity;
    int size;

    Node head;
    Node tail;

    Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node e = map.get(key);
        if (e == null) return -1;
        afterNodeAccess(e);
        return e.value;
    }

    private void afterNodeAccess(Node e) {
        //LinkedHashMap
        Node last = tail;
        if (last == e) return;
        Node p = e, b = p.prev, a = p.next;
        p.next = null;
        if (b == null)
            head = a;
        else
            b.prev = a;
        if (a != null)
            a.next = b;
        else
            last = b;
        if (last == null)
            head = p;
        else {
            p.prev = last;
            last.next = p;
        }
        tail = p;



//        Node dummy = new Node(0, 0);
//        dummy.next = head;
//        head.prev = dummy;
//        if (node.prev != null) {
//            node.prev.next = node.next;
//        }
//        if (node.next != null) {
//            node.next.prev = node.prev;
//            node.next.next = node;
//        }
//        node.prev = tail;
//        tail = node;
//        head = dummy.next;
//        tail.next = null;
//        head.prev = null;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            Node e = map.get(key);
            e.value = value;
            return;
        }
        if (capacity == size) {
            map.remove(head.key);
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            size--;
        }
        Node e = new Node(key, value);
        map.put(key, e);
        size++;
        if (head == null) {
            head = tail = e;
            return;
        }
        tail.next = e;
        e.prev = tail;
        tail = e;
    }

    public static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
