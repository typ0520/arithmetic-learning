package tl;

public class print_in_order {
    public static void main(String[] args) {
        //ReentrantLock
        //synchronized notifyAll
        //Semaphore
        //CountDownLatch

        Foo foo = new Foo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.third(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("third");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.first(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("first");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.second(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("second");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Foo {
        volatile int i = 0;
        public Foo() {

        }

        public void loop(int expect) {
            while (i != expect);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            loop(0);
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            i = 1;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // printSecond.run() outputs "second". Do not change or remove this line.
            loop(1);
            printSecond.run();
            i=2;
        }

        public void third(Runnable printThird) throws InterruptedException {
            // printThird.run() outputs "third". Do not change or remove this line.
            loop(2);
            printThird.run();
            i=0;
        }
    }
}
