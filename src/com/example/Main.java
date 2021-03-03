package com.example;

public class Main {
    public static void main(String[] args) {
        //System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        //System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));

        int[][] matrix = {
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        };

        System.out.println(sumRegion(matrix, 2,1,4,3));
        System.out.println(sumRegion(matrix, 1,1,2,2));
        System.out.println(sumRegion(matrix, 1,2,2,4));
    }

    public static int sumRegion(int[][] matrix, int row1, int col1, int row2, int col2) {
        int sum = 0;
        while (row1 <= row2) {
            for (int i = col1; i <= col2; i++) {
                sum += matrix[row1][i];
            }
            row1++;
        }
        return sum;
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
