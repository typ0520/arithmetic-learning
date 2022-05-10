package tl.thread;

import org.junit.jupiter.api.Test;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tong
 */
public class _1114_print_in_order {
    @Test
    public void test_foo1() throws InterruptedException {
        Foo1 foo1 = new Foo1();
        Thread t1 = new Thread(() -> {
            try {
                foo1.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                foo1.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                foo1.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t3.start();
        t2.start();
        t1.start();
        t1.join();
        t2.join();
        t3.join();
    }

    @Test
    public void test_foo2() throws InterruptedException {
        Foo2 foo1 = new Foo2();
        Thread t1 = new Thread(() -> {
            try {
                foo1.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                foo1.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                foo1.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t3.start();
        t2.start();
        t1.start();
        t1.join();
        t2.join();
        t3.join();
    }

    static class Foo1 {
        private final AtomicInteger atomicInteger = new AtomicInteger(5);
        public Foo1() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            while (!atomicInteger.compareAndSet(5, 0)) {
                // printFirst.run() outputs "first". Do not change or remove this line.
            }
            printFirst.run();
            atomicInteger.set(1);
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (!atomicInteger.compareAndSet(1, 2)) {
                // printSecond.run() outputs "second". Do not change or remove this line.
            }
            printSecond.run();
            atomicInteger.set(3);
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (!atomicInteger.compareAndSet(3, 4)) {
                // printThird.run() outputs "third". Do not change or remove this line.
            }
            printThird.run();
            atomicInteger.set(5);
        }
    }

    static class Foo2 {
        private final Semaphore s1 = new Semaphore(0);
        private final Semaphore s2 = new Semaphore(0);

        public Foo2() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            s1.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // printSecond.run() outputs "second". Do not change or remove this line.
            s1.acquire();
            printSecond.run();
            s2.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            // printThird.run() outputs "third". Do not change or remove this line.
            s2.acquire();
            printThird.run();
        }
    }
}
