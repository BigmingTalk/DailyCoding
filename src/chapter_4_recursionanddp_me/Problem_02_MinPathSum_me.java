package chapter_4_recursionanddp_me;

/**
 * Created by bigming on 16/9/19.
 * 题目: 矩阵的最小路径
 * 难度: **
 * 思路: 第一种方法是用用一个二维表, 第(i,j)项表示走到当前项的最短
 *      路径长度,因为只能向右和向下走,所以只要取左边和上方的较小值
 *      加上其本身值即可.
 *      第二种方法用了空间压缩技术,即只用一维空间即可,其思想其实只是
 *      二维空间的思想上用一维空间覆盖来解决.空间压缩的方法是有局限的,
 *      因为其覆盖了原来的值,所以不能回溯.
 */
public class Problem_02_MinPathSum_me {
    public static int minPathSum1(int[][] m){
        if (m == null || m.length ==0 || m[0] == null || m[0].length ==0){
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++){
            dp[i][0] = dp[i-1][0] + m[i][0];
        }
        for (int j = 0; j < col; j++){
            dp[0][j] = dp[0][j-1] + m[0][j];
        }
        for (int i =1; i < row; i++){
            for (int j = 1; j < col; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + m[i][j];
            }
        }
        return dp[row-1][col-1];
    }

    public static int minPathSum2(int[][] m){
        if (m == null || m.length ==0 || m[0] == null || m[0].length ==0){
            return 0;
        }
        int more = Math.max(m.length, m[0].length);
        int less = Math.min(m.length, m[0].length);
        boolean rowmore = more == m.length;
        int[] arr = new int[less];
        arr[0] = m[0][0];
        for (int i =1; i < less; i++){
            arr[i] = arr[i-1] + (rowmore ? m[0][i] : m[i][0]);
        }
        for (int i = 1; i < more; i++){
            arr[0] = arr[0] + (rowmore ? m[0][i] : m[i][0]);
            for (int j = 1; j < less; j++){
                arr[j] = Math.min(arr[j-1], arr[j])
                        + (rowmore ? m[i][j] : m[j][i]);
            }
        }
        return arr[less - 1];
    }


    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // int[][] m = generateRandomMatrix(3, 4);
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 },
                { 8, 8, 4, 0 } };
        printMatrix(m);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));

    }
}
