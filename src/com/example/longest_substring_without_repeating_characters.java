package com.example;

import java.util.HashMap;

public class longest_substring_without_repeating_characters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb") + " == 3");
        System.out.println(lengthOfLongestSubstring("abba") + " == 2");
        System.out.println(lengthOfLongestSubstring("abba") + " == 2");
        System.out.println(lengthOfLongestSubstring("") + " == 0");
        System.out.println(lengthOfLongestSubstring(" ") + " == 1");
        System.out.println(lengthOfLongestSubstring("aabaab!bb") + " == 3");
    }

    public static int lengthOfLongestSubstring(String s) {
        //return lengthOfLongestSubstring1(s);
        return lengthOfLongestSubstring2(s);
    }

    public static int lengthOfLongestSubstring1(String s) {
        int max = 0;
        HashMap<Character, Integer> table = new HashMap<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (table.containsKey(c)) {
                start = Math.max(start, table.get(s.charAt(end)));
            }
            table.put(c, end + 1);
            max = Math.max(max,  end - start + 1);
        }
        return max;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int max = 0;
        int[] table = new int[128];
        for (int start = 0, end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (table[c] != 0) {
                start = Math.max(start, table[s.charAt(end)]);
            }
            table[s.charAt(end)] = end + 1;
            max = Math.max(max,  end - start + 1);
        }
        return max;
    }
}
