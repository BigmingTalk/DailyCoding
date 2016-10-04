package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/10/4.
 * 题目: 给定一个整数N, 求由"0"字符和"1"字符组成的长度为N的所有字符串中,满足"0"字符
 *      的左边字符一定是"1"字符的字符串数量.
 * 举例: N = 1, 只有"1"符合
 *      N = 2, 有"10"和"11"符合
 *      N = 3, 有"101","110"和"111"符合.
 * 思路: 用p(i)表示0~i-1位置上的字符已经确定,这一段符合要求且第i-1位置的字符为'1'时,
 *      如果穷举i~N-1位置上的所有情况会产生多少种符合要求的字符串. 可以得出递推公司为
 *      p(i) = p(i+1) + p(i+2), 且有p(N-1) = 2, p(N) = 1.
 *
 */
public class Problem_16_ZeroLeftOneStringNumber_me {
    // 递归,时间复杂度为O(2^N)
    public static int getNum1(int n){
        if (n < 1){
            return 0;
        }
        return process(1, n);
    }

    public static int process(int i, int n){
        if (i == n-1){
            return 2;
        }
        if (i == n){
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    // 将递归改为迭代, 时间复杂度O(N),额外空间复杂度O(1)
    public static int getNum2(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for (int i = 2; i < n+ 1; i ++){
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }

    // 用矩阵乘法的方式将时间复杂度降为O(NlogN)
    public static int getNum3(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2){
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n-2);
        return 2 * res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p){
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p>>=1){
            if ((p & 1) != 0){
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(tmp, tmp);
        }
        return res;
    }

    public static int[][] multiMatrix(int[][] m1, int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m2[0].length; i++){
            for (int j = 0; j < m1.length; j++){
                for (int k = 0; k < m2.length; k++){
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i != 20; i++) {
            System.out.println(getNum1(i));
            System.out.println(getNum2(i));
            System.out.println(getNum3(i));
            System.out.println("===================");
        }

    }
}
