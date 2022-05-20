package tl.thread;

import java.util.concurrent.*;
import java.util.function.IntConsumer;

/**
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 *
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 *
 * 假设有这么一个类：
 *
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 *   public void fizz(printFizz) { ... }          // only output "fizz"
 *   public void buzz(printBuzz) { ... }          // only output "buzz"
 *   public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 *   public void number(printNumber) { ... }      // only output the numbers
 * }
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 *
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 *  
 *
 * 提示：
 *
 * 本题已经提供了打印字符串的相关方法，如 printFizz() 等，具体方法名请参考答题模板中的注释部分。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fizz-buzz-multithreaded
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author tong
 */
public class _1195_fizz_buzz_multithreaded {
    public static void main(String[] args) throws InterruptedException {
        int n = 15;
        final FizzBuzz fizzBuzz = new FizzBuzz(n);
        CountDownLatch latch = new CountDownLatch(4);
        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.number(value -> System.out.println(value));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        latch.await();
    }

    static class FizzBuzz {
        private int n;

        private Semaphore sNumber = new Semaphore(0);
        private TransferQueue<Runnable> tqFizz = new LinkedTransferQueue<>();
        private TransferQueue<Runnable> tqBuzz = new LinkedTransferQueue<>();
        private TransferQueue<Runnable> tqFizzbuzz = new LinkedTransferQueue<>();

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    Runnable callback = tqFizz.take();
                    printFizz.run();
                    callback.run();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 != 0 && i % 5 == 0) {
                    Runnable callback = tqBuzz.take();
                    printBuzz.run();
                    callback.run();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    Runnable callback = tqFizzbuzz.take();
                    printFizzBuzz.run();
                    callback.run();
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                boolean c1 = i % 3 == 0;
                boolean c2 = i % 5 == 0;
                if (c1 && c2) {
                    tqFizzbuzz.transfer(() -> sNumber.release());
                    sNumber.acquire();
                } else if (c1) {
                    tqFizz.transfer(() -> sNumber.release());
                    sNumber.acquire();
                } else if (c2) {
                    tqBuzz.transfer(() -> sNumber.release());
                    sNumber.acquire();
                } else {
                    printNumber.accept(i);
                }
            }
        }
    }
}
