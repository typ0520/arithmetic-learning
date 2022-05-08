package tl;

import java.util.HashMap;
import java.util.Map;

public class longest_substring_with_at_most_two_distinct_characters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba") + " == 3");
        System.out.println(lengthOfLongestSubstringTwoDistinct("ccaabbb") + " == 5");
    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0, repeat = 0, len = Integer.MIN_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            if (window.get(c) == 2) {
                repeat++;
            }


        }
        return len == Integer.MIN_VALUE ? 0 : len;
    }
}
