package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/14.
 * 题目: 给定一个矩阵,按照转圈的方式打印它
 * 要求: 额外空间复杂度为O(1)
 * 难度: *
 * 思路: 设计上市分圈打印!这种矩阵处理方式适用很多情况
 *
 */
public class Problem_01_PrintMatrixSpiralOrder_me {
    public static void spiralOrderPrint(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC){
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void printEdge(int[][] m, int tR, int tC, int dR, int dC){
        if (tR == dR){
            for (int i = tC; i <= dC; i++){
                System.out.print(m[tR][i] + " ");
            }
        } else if (tC == dC){
            for (int i = tR; i <= dR; i++){
                System.out.print(m[i][tC] + " ");
            }
        } else {
            int curC = tC;
            int curR = tR;
            while (curC != dC){
                System.out.print(m[tR][curC] + " ");
                curC++;
            }
            while (curR != dR){
                System.out.print(m[curR][dC] + " ");
                curR++;
            }
            while (curC != tC){
                System.out.print(m[dR][curC] + " ");
                curC--;
            }
            while (curR != tR){
                System.out.print(m[curR][tC] + " ");
                curR--;
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        spiralOrderPrint(matrix);

    }
}
