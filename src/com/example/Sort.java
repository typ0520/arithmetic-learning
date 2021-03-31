package com.example;

public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 9, 5, 4, 2, 7, 3};
        //bubbleSort(arr);
        //selectSort(arr);
        //insertSort(arr);
        mergeSort(arr, 0, arr.length - 1);
        //quickSort(arr, 0, arr.length - 1);
        for (int n : arr) {
            System.out.print(n + " ");
        }
    }

    //冒泡 O(n^2)
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j++);
                }
            }
        }
    }

    //选择 O(n^2)
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    //插入 O(n^2)
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    //归并 O(logN)
    public static void mergeSort(int[] arr, int L , int R) {
        if (L >= R) return;
        int mid = L + ((R - L) >> 1);
        mergeSort(arr, L, mid);
        mergeSort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M , int R) {
        int[] helper = new int[R - L + 1];
        int i = 0, p1 = L, p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            helper[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            helper[i++] = arr[p1++];
        }
        while (p2 <= R) {
            helper[i++] = arr[p2++];
        }
        for (i = 0; i < helper.length; i++) {
            arr[L + i] = helper[i];
        }
    }

    public static void mergeSort2(int[] arr, int L , int R) {

    }

    //快排 O(logN)
    public static void quickSort(int[] arr, int L, int R) {
        if (L >= R) return;
        int base = arr[L];
        int i = L, j = R;
        while (i < j) {
            while (i < j && arr[j] >= base) j--;
            arr[i] = arr[j];
            while (i < j && arr[i] <= base) i++;
            arr[j] = arr[i];
        }
        arr[i] = base;
        quickSort(arr, L, i - 1);
        quickSort(arr, i + 1, R);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
