package tl;

public class er_wei_shu_zu_zhong_de_cha_zhao_lcof {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1, 4, 7, 11, 15},
                new int[]{2, 5, 8, 12, 19},
                new int[]{3, 6, 9, 16, 22},
                new int[]{10, 13, 14, 17, 24},
                new int[]{18, 21, 23, 26, 30},
        };
        System.out.println(new Solution().findNumberIn2DArray(matrix, 17));
    }

    static class Solution {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix == null
                    || matrix.length == 0
                    || matrix[0].length == 0
                    || target < matrix[0][0]
                    || target > matrix[matrix.length - 1][matrix[matrix.length - 1].length - 1]) {
                return false;
            }
            for (int[] row : matrix) {
                if (target >= row[0] && target <= row[row.length - 1]) {
                    int low = 0, high = row.length - 1;
                    while (low <= high) {
                        int middle = (low + high) / 2;
                        int value = row[middle];
                        if (target < value) {
                            high = middle - 1;
                        } else if (target > value) {
                            low = middle + 1;
                        } else {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
}
