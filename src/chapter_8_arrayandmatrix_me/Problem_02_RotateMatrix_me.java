package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/14.
 * 题目: 将正方形矩阵顺时针转动90度
 * 要求: 额外空间复杂度为O(1)
 * 难度: *
 * 思路: 还是矩阵分圈的思路,对于每一圈又分为几组,对于一组只要转圈交换即可
 */
public class Problem_02_RotateMatrix_me {

    public static void rotate(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR < dR){
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }


    // 对于每一圈的打印
    public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC){
        int times = dC - tC; // times 就是总的组数
        int tmp = 0;
        // 一次循环就是一组调整
        for (int i = 0; i != times; i++){
            tmp = m[tR][tC + i];
            m[tR][tC + i] = m[dR - i][tC];
            m[dR - i][tC] = m[dR][dC - i];
            m[dR][dC - i] = m[tR + i][dC];
            m[tR + i][dC] = tmp;
        }
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
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
