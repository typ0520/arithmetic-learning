package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class find_all_anagrams_in_a_string {
    public static void main(String[] args) {
        //s: "cbaebabacd" p: "abc"
        List<Integer> result = findAnagrams("cbaebabacd", "abc");
        for (Integer num : result) {
            System.out.print(num + " ");
        }
    }

    public static List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, valid = 0, start = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            while (right - left >= t.length()) {
                if (valid == need.size()) {
                    result.add(left);
                    System.out.println(s.substring(left, right - 1));
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (windows.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }
            }
        }
        return result;
    }
}
