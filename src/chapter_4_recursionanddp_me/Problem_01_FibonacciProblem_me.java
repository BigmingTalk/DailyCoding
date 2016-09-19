package chapter_4_recursionanddp_me;

/**
 * Created by bigming on 16/9/19.
 * 题目: 斐波那契额数列系列问题,可以递归,迭代,矩阵递推式等方法求解
 * 难度: ****
 * 思路: 递归时间复杂度为O(2^N),迭代为O(N),矩阵递推式为O(logN).
 * 进阶: 所有i阶递推式的都可以用矩阵递推式推导,只不过对应的矩阵是多维的
 *      而已.
 */
public class Problem_01_FibonacciProblem_me {
    public static int f1(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        return f1(n -1) + f1(n - 2);
    }

    public static int f2(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp =0;
        for (int i = 3; i <= n; i++){
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public static int[][] muliMatrix(int[][] m1, int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];
        for (int i =0; i < m1.length; i++){
            for (int j = 0; j < m2[0].length; j++){
                for (int k = 0; k < m2.length; k++){
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static int[][] matrixPower(int[][] m, int p){
        int[][] res = new int[m.length][m[0].length];
        //先把res设为单位矩阵
        for (int i = 0; i < m.length; i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p!=0; p = p >> 1){
            if ((p & 1) == 1){
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    public static int f3(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n -2);
        return res[0][0] + res[1][0];
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
    }
}
