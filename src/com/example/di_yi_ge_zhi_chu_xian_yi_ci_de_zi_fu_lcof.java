package com.example;

import java.util.*;

//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
//
//示例:
//
//s = "abaccdeff"
//返回 "b"
//
//s = ""
//返回 " "
// 
//
//限制：
//
//0 <= s 的长度 <= 50000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class di_yi_ge_zhi_chu_xian_yi_ci_de_zi_fu_lcof {
    public static void main(String[] args) {
        Map<String, Character> testCase = new LinkedHashMap<>();
        testCase.put("dddccdbba", 'a');
        //testCase.put("abaccdeff", 'b');
        //testCase.put("", ' ');

        for (Map.Entry<String, Character> entry : testCase.entrySet()) {
            Object result = new Solution().firstUniqChar(entry.getKey());
            if (!result.equals(entry.getValue())) {
                System.out.println(entry.getKey() + ", expect: " + entry.getValue() + " actual: " + result);
            }
        }
    }

    static class Solution {
        public char firstUniqChar(String s) {
            if (s == null) return ' ';
            Map<Character, Boolean> map = new LinkedHashMap<>(s.length());
            for (int i = 0; i < s.length(); i++) {
                Character c = s.charAt(i);
                map.put(c, !map.containsKey(c));
            }
            if (map.isEmpty()) {
                return ' ';
            }
            for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
                if (entry.getValue()) {
                    return entry.getKey();
                }
            }
            return ' ';
        }
    }
}
