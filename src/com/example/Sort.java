package com.example;

public class Sort {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 9, 5, 4, 2, 7, 3};
        quickSort(nums, 0, nums.length - 1);
        for (int n : nums) {
            System.out.print(n + " ");
        }
    }

    //冒泡 O(n^2)

    //选择 O(n^2)

    //插入 O(n^2)

    //快排 O(logN)
    public static void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int base = nums[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && nums[j] >= base) j--;
            nums[i] = nums[j];
            while (i < j && nums[i] <= base) i++;
            nums[j] = nums[i];
        }
        nums[i] = base;
        quickSort(nums, l, i - 1);
        quickSort(nums, i + 1, r);
    }
}
