package tl;

public class RingArrayQueue {
    int[] arr;
    int pushi;
    int popi;
    int limit;
    int size;

    public RingArrayQueue(int size) {
        this.arr = new int[size];
        this.limit = size;
    }

    public void push(int value) {
        if (size >= limit) {
            throw new IllegalStateException("overflow");
        }
        arr[pushi] = value;
        this.pushi = nextIndex(pushi);
        size++;
    }

    public int pop() {
        if (size == 0) {
            throw new IllegalStateException("empty");
        }
        int res = arr[popi];
        this.popi = nextIndex(popi);
        size--;
        return res;
    }

    private int nextIndex(int i) {
        return i < limit - 1 ? i + 1 : 0;
    }

    public static void main(String[] args) {
        RingArrayQueue queue = new RingArrayQueue(5);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}
