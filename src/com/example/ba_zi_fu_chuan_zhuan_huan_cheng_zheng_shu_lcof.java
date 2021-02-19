package com.example;

public class ba_zi_fu_chuan_zhuan_huan_cheng_zheng_shu_lcof {
    public static void main(String[] args) {
        //https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/solution/mian-shi-ti-67-ba-zi-fu-chuan-zhuan-huan-cheng-z-4/
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toHexString(Integer.MAX_VALUE));
        System.out.println(Integer.toHexString(Integer.MIN_VALUE));
        System.out.println("0" + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Long.MAX_VALUE);
        //"-91283472332" "20000000000000000000"
        System.out.println(new Solution().strToInt("-91283472332"));
    }

    static class Solution {
        public int strToInt(String str) {
            //init:0 space:1 symbol:2 number:3 end:4
            //0 -> 1,2,3
            //1 -> 2,3
            //2 -> 3
            //3 -> 4
            if (str == null) return 0;
            str = str.trim();
            Boolean greaterThanZero = null;
            boolean discoverNumber = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                boolean isSpace = c == ' ';
                boolean isNumber = c >= '0' && c <= '9';
                boolean isWord = !isSpace && !isNumber;
                if (discoverNumber) {
                    if (!isNumber) {
                        break;
                    }
                    if (sb.length() > 0 || c != '0') {
                        sb.append(c);
                    }
                } else {
                    if (greaterThanZero != null && !isNumber) {
                        break;
                    }
                    if (isNumber) {
                        discoverNumber = true;
                        i--;
                    } else if (c == '+') {
                        greaterThanZero = true;
                    } else if (c == '-') {
                        greaterThanZero = false;
                    } else if (isWord) {
                        break;
                    }
                }
            }
            if (sb.length() > 0) {
                if (greaterThanZero == null) greaterThanZero = true;
                if (greaterThanZero && sb.length() > 10) {
                    return Integer.MAX_VALUE;
                }
                if (!greaterThanZero && sb.length() > 10) {
                    return Integer.MIN_VALUE;
                }
                long sum = 0;
                for (int i = 0; i < sb.length(); i++) {
                    int base = sb.charAt(i) - '0';
                    long times = (long) Math.pow(10, sb.length() - i - 1);
                    sum += (base * times);
                }
                if (greaterThanZero && sum > (long)Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (!greaterThanZero && -sum < (long)Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
                return greaterThanZero ? (int)sum : (int)-sum;
            }
            return 0;
        }
    }
}
