package com.example;

public class Fib {
    public static void main(String[] args) {
        System.out.println(fib1(20));
        System.out.println(fib2(20));
        System.out.println(fib3(20));
        System.out.println(fib4(20));
    }

    static int fib1(int n) {
        if (n == 1 || n == 2) return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    static int fib2(int n) {
        if (n < 1) return -1;
        return fib2_helper(n, new int[n + 1]);
    }

    static int fib2_helper(int n, int[] memo) {
        if (n == 1 || n == 2) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = fib2_helper(n - 1, memo) + fib2_helper(n - 2, memo);
        return memo[n];
    }

    static int fib3(int n) {
        if (n < 1) return -1;
        int[] memo = new int[n + 1];
        memo[1] = memo[2] = 1;
        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    static int fib4(int n) {
        if (n < 1) return -1;
        int prev = 1, cur = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + cur;
            prev = cur;
            cur = sum;
        }
        return cur;
    }
}
