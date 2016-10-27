package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/27.
 * 题目: 给定一个N x N的矩阵matrix,在这个矩阵中,只有0和1两种值,
 *      返回边框全是1的最大正方形的边长长度.
 * 难度: **
 * 思路: 用right和down数组统计其开始向右和向下一共有多少个连续的1
 */
public class Problem_21_MaxOneBorderSize_me {

    /**
     * 这种解法是错误的..
    public static class Pair{
        public int x;
        public int y;

        public  Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int getMaxSize(int[][] m){
        Pair[][] pm = new Pair[m.length][m[0].length];
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                pm[i][j] = new Pair(0,0);
            }
        }

        pm[0][0].x = m[0][0] == 0 ? 0 : 1;
        pm[0][0].y = m[0][0] == 0 ? 0 : 1;

        for (int i = 1; i < m[0].length; i++){
            if (m[0][i] == 1){
                pm[0][i].y = 1;
                pm[0][i].x = pm[0][i - 1].x + 1;
            } else {
                pm[0][i].x = 0;
                pm[0][i].y = 0;
            }
        }

        for (int j = 1; j < m.length; j++){
            if (m[j][0] == 1){
                pm[j][0].x = 1;
                pm[j][0].y = pm[j - 1][0]. y+ 1;
            } else {
                pm[j][0].x = 0;
                pm[j][0].y = 0;
            }
        }

        for (int i = 1; i < m.length; i++){
            for (int j = 1; j < m[0].length; j++){
                if (m[i][j] == 1){
                    pm[i][j].x = pm[i][j - 1].x + m[i][j];
                    pm[i][j].y = pm[i - 1][j].y + m[i][j];
                } else {
                    pm[i][j].x = 0;
                    pm[i][j].y = 0;
                }
            }
        }

        printMatrix(pm);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m[0].length; j++){
                if (pm[i][j].x == pm[i][j].y){
                    max = Math.max(max, pm[i][j].x);
                }
            }
        }
        return max;
    }

    public static void printMatrix(Pair[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print("(" + matrix[i][j].x + "," + matrix[i][j].y + ") ");
            }
            System.out.println();
        }
    }

     **/

    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i != -1; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i != -1; i--) {
            if (m[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        for (int i = r - 2; i != -1; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    public static int getMaxSize(int[][] m) {
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
        for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
            if (hasSizeOfBorder(size, right, down)) {
                return size;
            }
        }
        return 0;
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down){
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size
                        && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size){
                    return true;
                }
            }
        }
        return false;
    }


    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
    }


    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(7, 8);
        printMatrix(matrix);
        System.out.println(getMaxSize(matrix));
    }
}
