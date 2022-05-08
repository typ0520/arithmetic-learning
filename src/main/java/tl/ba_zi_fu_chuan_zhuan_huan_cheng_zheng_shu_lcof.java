package tl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

  

 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

 在任何情况下，若函数不能进行有效的转换时，请返回 0。

 说明：

 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

 示例 1:

 输入: "42"
 输出: 42
 示例 2:

 输入: "   -42"
 输出: -42
 解释: 第一个非空白字符为 '-', 它是一个负号。
      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 示例 3:

 输入: "4193 with words"
 输出: 4193
 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 示例 4:

 输入: "words and 987"
 输出: 0
 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 因此无法执行有效的转换。
 示例 5:

 输入: "-91283472332"
 输出: -2147483648
 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
      因此返回 INT_MIN (−231) 。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ba_zi_fu_chuan_zhuan_huan_cheng_zheng_shu_lcof {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toHexString(Integer.MAX_VALUE));
        System.out.println(Integer.toHexString(Integer.MIN_VALUE));
        System.out.println("0" + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Long.MAX_VALUE);
        System.out.println("---------------------");

        Map<String, Integer> testCase = new LinkedHashMap<>();
//        testCase.put("2147483647", 2147483647);
//        testCase.put("2147483648", 2147483647);
        testCase.put("-2147483648", -2147483648);
        testCase.put("-2147483649", -2147483648);
        testCase.put("-91283472332", -2147483648);
        testCase.put(" +-100", 0);
        testCase.put(" 4399 word", 4399);

        for (Map.Entry<String, Integer> entry : testCase.entrySet()) {
            int result = new Solution().strToInt(entry.getKey());
            if (result != entry.getValue()) {
                System.out.println(entry.getKey() + ", expect: " + entry.getValue() + " actual: " + result);
            }
        }
    }

    static class Solution {
//        public int strToInt(String str) {
//            if (str == null || (str = str.trim()).length() == 0) return 0;
//            int sign = 1;
//            int i = 0;
//            if (str.charAt(0) == '-') {
//                sign = -1;
//                i = 1;
//            }
//            if (str.charAt(0) == '+') i = 1;
//            int res = 0;
//            for (; i < str.length(); i++) {
//                if ((str.charAt(i) < '0' || str.charAt(i) > '9')) break;
//                int num = str.charAt(i) - '0';
//                if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > 7)) {
//                    return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//                }
//                res = res * 10 + num;
//            }
//            return sign * res;
//        }

        public int strToInt(String str) {
            if (str == null) return 0;
            Automaton automaton = new Automaton();
            for (int i = 0; i < str.length(); i++) {
                automaton.getchar(str.charAt(i));
            }
            return automaton.sign * (int)automaton.ans;
        }

        class Automaton {
            private Map<String, String[]> table = new HashMap<String, String[]>(){{
                //                          ' ',    +/-,    0-9,      other
                put("start",  new String[]{"start", "sign", "number", "end"});
                put("sign",   new String[]{"end",   "end",  "number", "end"});
                put("number", new String[]{"end",   "end",  "number", "end"});
                put("end",    new String[]{"end",   "end",  "end",    "end"});
            }};

            public String status = "start";

            public int sign = 1;
            public long ans;

            public int getcol(char c) {
                if (c == ' ') {
                    return 0;
                } else if (c == '+' || c == '-') {
                    return 1;
                } else if (c >= '0' && c <= '9') {
                    return 2;
                }
                return 3;
            }

            public void getchar(char c) {
                status = table.get(status)[getcol(c)];
                if ("number".equals(status)) {
                    ans = ans * 10 + (c - '0');
                    if (sign == 1) {
                        ans = Math.min(ans, (long)Integer.MAX_VALUE);
                    } else {
                        ans = -Math.max(-ans, (long)Integer.MIN_VALUE);
                    }
                } else if ("sign".equals(status)) {
                    sign = c == '+' ? 1 : -1;
                }
            }
        }
    }
}
