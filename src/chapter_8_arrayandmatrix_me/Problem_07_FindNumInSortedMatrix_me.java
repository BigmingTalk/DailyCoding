package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/17.
 * 题目: 在行列都排好序的N*M的整型矩阵matrix中,给定一个整数K, 判断K是否在matrix中.
 * 难度: *
 * 思路: 从左下角或右上角开始,而不是左上角或右下角开始,这样可以保证每次只朝一个方向上
 *      移动!!!!!!!我服...
 *
 */
public class Problem_07_FindNumInSortedMatrix_me {
    public static boolean isContains(int[][] matrix, int K){
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col > -1){
            if (matrix[row][col] == K){
                return true;
            } else if (matrix[row][col] > K){
                col--;
            } else {
                row++;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                { 0, 1, 2, 3, 4, 5, 6 },
                { 10, 12, 13, 15, 16, 17, 18 },
                { 23, 24, 25, 26, 27, 28, 29 },
                { 44, 45, 46, 47, 48, 49, 50 },
                { 65, 66, 67, 68, 69, 70, 71 },
                { 96, 97, 98, 99, 100, 111, 122 },
                { 166, 176, 186, 187, 190, 195, 200 },
                { 233, 243, 321, 341, 356, 370, 380 }
        };
        int K = 99;
        System.out.println(isContains(matrix, K));
        int L = 1111;
        System.out.println(isContains(matrix, L));
        int M = 188;
        System.out.println(isContains(matrix, M));

    }
}
