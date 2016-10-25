package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/25.
 * 题目: 子矩阵的最大累加和问题
 * 难度: **
 * 思路: 是子数组最大累加和问题的一个提升
 */
public class Problem_17_SubMatrixMaxSum_me {
    public static int maxSum(int[][] m){
        if (m == null || m.length == 0 || m[0].length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur;

        for (int i = 0; i < m.length; i++){
            int[] s = new int[m[0].length];
            for (int j = i; j < m.length; j++){
                cur = 0;
                for (int k = 0 ; k < m[0].length; k++){
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 :cur;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
        System.out.println(maxSum(matrix));

    }
}
