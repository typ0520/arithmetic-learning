package com.example;

import java.util.*;

public class sum3 {
    public static void main(String[] args) {
        //int[] nums = new int[]{-1,0,1,2,-1,-4};
        //int[] nums = new int[]{0,0,0};
        int[] nums = new int[]{-2,0,1,1,2};
        List<List<Integer>> result = new Solution().threeSum(nums);
        for (List<Integer> item : result) {
            for (Integer num : item) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }

    static class Solution {
//        public List<List<Integer>> threeSum(int[] nums) {
//            Set<String> set = new HashSet<>();
//            List<List<Integer>> result = new ArrayList<>();
//            for (int i = 0; i < nums.length - 2; i++) {
//                for (int j = i + 1; j < nums.length - 1; j++) {
//                    for (int k = j + 1; k < nums.length; k++) {
//                        if (nums[i] + nums[j] + nums[k] == 0) {
//                            List<Integer> list = new ArrayList<>();
//                            list.add(nums[i]);
//                            list.add(nums[j]);
//                            list.add(nums[k]);
//                            //Collections.sort(list);
//                            String key = list.get(0) + "" + list.get(1) + "" + list.get(2);
////                            if (!set.contains(key)) {
////                                set.add(key);
////                                result.add(list);
////                                System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(2));
////                            }
//                            System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(2));
//                        }
//                    }
//                }
//            }
//            return result;
//        }

        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);
            for (int k = 0; k < nums.length - 2; k++) {
                if (nums[k] > 0) break;
                if (k > 0 && nums[k] == nums[k - 1]) continue;
                int i = k + 1;
                int j = nums.length - 1;
                while (i < j) {
                    int sum = nums[k] + nums[i] + nums[j];
                    if (sum < 0) {
                        i++;
                        while (i < j && nums[i - 1] == nums[i]) i++;
                    } else if (sum > 0) {
                        j--;
                        while (i < j && nums[j] == nums[j + 1]) j--;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[k]);
                        list.add(nums[i]);
                        list.add(nums[j]);
                        result.add(list);
                        i++;
                        while (i < j && nums[i - 1] == nums[i]) i++;
                        j--;
                        while (i < j && nums[j] == nums[j + 1]) j--;
                    }
                }
            }
            return result;
        }
    }
}
