package chapter_4_recursionanddp_me;

/**
 * Created by bigming on 16/9/27.
 * 题目: N皇后问题是指在N*N的棋盘上要摆N个皇后,要求任何两个皇后不同行,不同列,又不在同一
 *      斜线上,给定一个整数n,返回n皇后的摆法有多少种.
 * 举例: n=1,返回1, n=2或3,怎么摆都不行,返回0,n=8,返回92.
 * 思路: 可以用人工智能算法和遗传算法都进行求解,不过这里先用一个通用的解法解决.即逐行来
 *      排查. 第二种是用位运算来加速,具体可以看书本解答.
 */
public class Problem_17_NQueens_me {
    public static int num1(int n){
        if (n < 1){
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    public static int process1(int i, int[] record, int n){
        if (i == n){
            return 1;
        }
        int res = 0;
        for (int j =0; j < n; j++){
            if (isValid(record, i, j)){
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j){
        for (int k =0; k < i; k++){
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i -k)){
                return false;
            }
        }
        return true;
    }


    public static int num2(int n){
        if (n < 1 || n >32){
            return 0;
        }
        int upperLim = n ==32 ? -1 : (1<<n) - 1;
        return process2(upperLim, 0, 0, 0);
    }

    public static int process2(int upperLim, int colLim, int leftDiaLim,
                               int rightDiaLim) {
        if (colLim == upperLim){
            return 1;
        }
        int pos = 0;
        int mostRightOne = 0;
        pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
        int res = 0;
        while (pos != 0){
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(upperLim, colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 8;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }

}
