package tl.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 1116. 打印零与奇偶数
 * <p>
 * 现有函数 printNumber 可以用一个整数参数调用，并输出该整数到控制台。
 * <p>
 * 例如，调用 printNumber(7) 将会输出 7 到控制台。
 * 给你类 ZeroEvenOdd 的一个实例，该类中有三个函数：zero、even 和 odd 。ZeroEvenOdd 的相同实例将会传递给三个不同线程：
 * <p>
 * 线程 A：调用 zero() ，只输出 0
 * 线程 B：调用 even() ，只输出偶数
 * 线程 C：调用 odd() ，只输出奇数
 * 修改给出的类，以输出序列 "010203040506..." ，其中序列的长度必须为 2n 。
 * <p>
 * 实现 ZeroEvenOdd 类：
 * <p>
 * ZeroEvenOdd(int n) 用数字 n 初始化对象，表示需要输出的数。
 * void zero(printNumber) 调用 printNumber 以输出一个 0 。
 * void even(printNumber) 调用printNumber 以输出偶数。
 * void odd(printNumber) 调用 printNumber 以输出奇数。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出："0102"
 * 解释：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出："0102030405"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1116_print_zero_even_odd {
    @Test
    public void test1() throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(4);

        IntConsumer intConsumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.print(value);
                if (value >= zeroEvenOdd.n) {
                    System.out.println();
                }
            }
        };
        Thread t1 = new Thread(() -> {
            try {
                zeroEvenOdd.zero(intConsumer);
            } catch (InterruptedException e) {
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                zeroEvenOdd.odd(intConsumer);
            } catch (InterruptedException e) {
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                zeroEvenOdd.even(intConsumer);
            } catch (InterruptedException e) {
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    @Test
    public void test2() throws InterruptedException {
        ZeroEvenOdd2 zeroEvenOdd = new ZeroEvenOdd2(4);

        IntConsumer intConsumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.print(value);
                if (value >= zeroEvenOdd.n) {
                    System.out.println();
                }
            }
        };
        Thread t1 = new Thread(() -> {
            try {
                zeroEvenOdd.zero(intConsumer);
            } catch (InterruptedException e) {
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                zeroEvenOdd.odd(intConsumer);
            } catch (InterruptedException e) {
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                zeroEvenOdd.even(intConsumer);
            } catch (InterruptedException e) {
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    static class ZeroEvenOdd {
        private int n;
        private Semaphore zeroSemaphore = new Semaphore(1);
        private Semaphore evenSemaphore = new Semaphore(0);
        private Semaphore oddSemaphore = new Semaphore(0);

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                zeroSemaphore.acquire();
                printNumber.accept(0);
                if ((i + 1) % 2 == 0) {
                    evenSemaphore.release();
                } else {
                    oddSemaphore.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                evenSemaphore.acquire();
                printNumber.accept(i);
                zeroSemaphore.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                oddSemaphore.acquire();
                printNumber.accept(i);
                zeroSemaphore.release();
            }
        }
    }

    private static class ZeroEvenOdd2 {
        public int n;
        ReentrantLock lock = new ReentrantLock();
        Condition zeroCondition = lock.newCondition();
        Condition oddCondition = lock.newCondition();
        Condition evenCondition = lock.newCondition();
        volatile int flag = 0;

        public ZeroEvenOdd2(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            try {
                lock.lock();
                for (int i = 0; i < n; i++) {
                    if (flag != 0) {
                        zeroCondition.await();
                    }
                    printNumber.accept(0);
                    if ((i + 1) % 2 == 0) {
                        flag = 1;
                        evenCondition.signal();
                    } else {
                        flag = 2;
                        oddCondition.signal();
                    }
                }

            } finally {
                lock.unlock();
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            try {
                lock.lock();
                for (int i = 2; i <= n; i += 2) {
                    if (flag != 1) {
                        evenCondition.await();
                    }
                    printNumber.accept(i);
                    flag = 0;
                    zeroCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            try {
                lock.lock();
                for (int i = 1; i <= n; i += 2) {
                    if (flag != 2) {
                        oddCondition.await();
                    }
                    printNumber.accept(i);
                    flag = 0;
                    zeroCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
