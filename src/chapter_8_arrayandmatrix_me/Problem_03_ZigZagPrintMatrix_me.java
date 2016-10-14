package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/14.
 * 题目: 给定一个矩阵,要求按照之字形的方式打印这个矩阵,例如
 *      1  2  3  4
 *      5  6  7  8
 *      9  10 11 12
 *      之字形打印的结果为: 1 2 5 9 6 3 4 7 10 11 8 12
 * 要求: 额外空间复杂度为O(1)
 * 难度: *
 * 思路: 用上坐标和下坐标来确定对角线, 上坐标先横着走,然后竖着走,
 *      下坐标相反.然后由上坐标和下坐标确定一条对角线,打印对角线的
 *      值,打印的顺序每次都相反.
 */
public class Problem_03_ZigZagPrintMatrix_me {
    public static void printMatrixZigZag(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR != endR + 1){
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }


    public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f){
        if (f){
            while (tR != dR + 1){
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while (dR != tR - 1){
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }

}
