package chapter_9_others_me;

import sun.security.krb5.KdcComm;

/**
 * Created by bigming on 16/11/25.
 * 题目: 丢棋子问题
 *      一座大楼有0~N层, 地面算作0层, 最高的一层为第N层. 已知棋子
 *      从第0层掉落肯定不会摔碎, 从第i层掉落可能会摔碎, 也可能不会
 *      摔碎(1<=i<=N). 给定整数N作为楼层数, 再给定整数K作为棋子数,
 *      返回如果想找到棋子不会摔碎的最高层数, 即使在最差的情况下扔
 *      的最少次数. 一次只能扔一个棋子.
 * 难度: ***
 * 思路: 假设P(N, K)的返回值是N层楼有K个棋子在最差情况下扔的最少次数.
 *      可以得到P(N,K) = min{max{P(i-1,k-1), p(N-i,k)}(1<=i<=N)} + 1.
 *
 */
public class Problem_32_ThrowChessPiecesProblem_me {
    public static int solution1(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        return Process1(nLevel, kChess);
    }

    // 暴力递归, 时间复杂度为O(N!)
    public static int Process1(int nLevel, int kChess) {
        if (nLevel == 0) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i != nLevel + 1; i++) {
            min = Math.min(min,
                    Math.max(Process1(i - 1, kChess - 1),
                            Process1(nLevel - i, kChess)));
        }
        return min + 1;
    }

    // 用二维数组表示递归过程, 时间复杂度O(N^2 * K), 因为对于每个
    // 位置P(a,b)来说, 需要枚举P(0...a-1, b)和P(0...a-1, b-1)
    public static int solution2(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i != dp.length; i++) {
            for (int j = 2; j != dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k != i + 1; k++) {
                    min = Math.min(min,
                            Math.max(dp[k - 1][j - 1],
                                    dp[i - k][j]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }

    // 空间压缩的方法, 因为dp[N][K]只用到它左边的数据dp[0..N-1][K-1]
    // 和它上面的数据 dp[0..N-1][K], 所以只需要用两个数组来循环就好.
    // 相较于方法二将额外空间复杂度降低了.
    public static int solution3(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[] preArr = new int[nLevel + 1];
        int[] curArr = new int[nLevel + 1];
        for (int i = 1; i != curArr.length; i++) {
            curArr[i] = i;
        }
        for (int i = 1; i != kChess; i++) {
            int[] tmp = preArr;
            preArr = curArr;
            curArr = tmp;
            for (int j = 1; j != curArr.length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k != j + 1; k ++) {
                    min = Math.min(min,
                            Math.max(preArr[k - 1], curArr[j - k]));
                }
                curArr[j] = min + 1;
            }
        }
        return curArr[curArr.length - 1];
    }


    // 在计算动态规划二维表的过程中, 发现计算每一个值时有枚举过程时,
    // 则往往可以通过"四边形不等式"的优化把时间复杂度降一个维度, 可以
    // 从O(N^2 * K)或O(N^3)降到O(N^2).
    public static int solution4(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }

        // 边界数组
        int[] cands = new int[kChess + 1];
        for (int i = 1; i != dp[0].length; i++) {
            dp[1][i] = 1;
            cands[i] = 1;
        }
        for (int i = 2; i < nLevel + 1; i++) {
            for (int j = kChess; j > 1; j--) {
                int min = Integer.MAX_VALUE;
                int minEnum = cands[j];
                int maxEnum = j == kChess ? i / 2 + 1 : cands[j + 1];
                for (int k = minEnum; k < maxEnum + 1; k++) {
                    int cur = Math.max(dp[k - 1][j - 1], dp[i - k][j]);
                    if (cur <= min) {
                        min = cur;
                        cands[j] = k;
                    }
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }

    // 最优解, 以上各种方法解决的问题都是N层楼有K个棋子最少扔多少次,
    // 现在反过来看K个棋子如果可以扔M次, 最多可以解决多少层楼的问题.
    // 用map[i][j]表示i个棋子扔j次最多可以搞定的次数. 递推关系会简单
    // 很多, map[i][j] = map[i][j-1] + map[i-1][j-1] + 1, 表示
    // 碎的情况+没碎的情况+该层的情况, 且当N层是用二分扔LogN+1次就可以
    // 搞定, 作为一种优化方法.
    public static int solution5(int nLevel, int kChess) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        int bsTimes = log2N(nLevel + 1);
        if (kChess >= bsTimes) {
            return bsTimes;
        }
        int[] dp = new int[kChess];
        int res = 0;
        while (true) {
            res++;
            int previous = 0;
            for (int i = 0; i < dp.length; i++) {
                int tmp = dp[i];
                dp[i] = dp[i] + previous + 1;
                previous = tmp;
                if (dp[i] >= nLevel) {
                    return res;
                }
            }
        }
    }

    public static int log2N(int n) {
        int res = -1;
        while (n != 0) {
            res++;
            n >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solution1(21, 2));
        System.out.println(solution2(21, 2));
        System.out.println(solution3(21, 2));
        System.out.println(solution4(21, 2));
        System.out.println(solution5(21, 2));

        System.out.println("==============");

        System.out.println(solution2(105, 2));
        System.out.println(solution3(105, 2));
        System.out.println(solution4(105, 2));
        System.out.println(solution5(105, 2));

        System.out.println("==============");

        System.out.println(solution2(3000, 10));
        System.out.println(solution3(3000, 10));
        System.out.println(solution4(3000, 10));
        System.out.println(solution5(3000, 10));

        System.out.println("==============");

        System.out.println(solution2(6884, 5));
        System.out.println(solution3(6884, 5));
        System.out.println(solution4(6884, 5));
        System.out.println(solution5(6884, 5));

        System.out.println("==============");

        System.out.println(solution2(6885, 5));
        System.out.println(solution3(6885, 5));
        System.out.println(solution4(6885, 5));
        System.out.println(solution5(6885, 5));

        System.out.println("==============");

        int nLevel = 100000000;
        int kChess = 10;
        long start = System.currentTimeMillis();
        System.out.println(solution5(nLevel, kChess));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");

    }






}
