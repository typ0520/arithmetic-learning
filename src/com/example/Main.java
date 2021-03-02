package com.example;

public class Main {
    public static void main(String[] args) {
        //System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] nums = new int[len / 2 + 1];
        int k = 0, i = 0, j = 0;
        while ((i < nums1.length || j < nums2.length) && k <= len / 2) {
            if (i == nums1.length) {
                nums[k++] = nums2[j++];
            } else if (j == nums2.length) {
                nums[k++] = nums1[i++];
            } else {
                if (nums1[i] <= nums2[j]) {
                    nums[k++] = nums1[i++];
                } else {
                    nums[k++] = nums2[j++];
                }
            }
        }
        if (len % 2 == 0) {
            return (nums[len / 2] + nums[len / 2 - 1]) / 2.0;
        }
        return nums[(len - 1) / 2];
    }
}
