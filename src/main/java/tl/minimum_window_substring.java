package tl;

import java.util.HashMap;
import java.util.Map;

//76. 最小覆盖子串
//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
//注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
//
// 
//
//示例 1：
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//示例 2：
//
//输入：s = "a", t = "a"
//输出："a"
// 
//
//提示：
//
//1 <= s.length, t.length <= 105
//s 和 t 由英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-window-substring
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class minimum_window_substring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {
        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, valid = 0, start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
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
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
