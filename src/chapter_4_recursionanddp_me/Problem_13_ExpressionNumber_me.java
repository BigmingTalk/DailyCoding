package chapter_4_recursionanddp_me;

/**
 * Created by bigming on 16/9/26.
 * 题目: 给定一个只由0,1,&,|,^(亦或)五种字符组成的字符串express,
 *      再给定一个布尔值desired.返回express能有多少种组合方式.可以
 *      达到desired的结果.
 * 难度: ***
 * 举例: express='1^0|0|1', desired = false,只有 1^((0|0)|1)和
 *      1^(0|(0|1))的组合可以得到false,返回2.
 *      express='1',desired=false,无组合可以得到false,返回0
 * 思路: 首先应判断express是否合乎题目要求,有三个标准:
 *      1. 表达式长度为奇数
 *      2. 表达式下标为偶数位置的字符一定是'0'或'1'
 *      3. 表达式下标为奇数位置的字符一定是'&'或'|'或'^'
 *      然后暴力递归的方式,以每个分隔符作为分隔,算出所有可能的情况.
 *      改进: 暴力递归中存在很多重复计算,用动态规划的方法,如果express
 *      的长度为N,生成两个大小为N*N的矩阵t和f,t[i][j]表示express[j..i]
 *      组成true的种数,f[j][i]表示expr[j..i]组成false的种数.
 */
public class Problem_13_ExpressionNumber_me {
    public static boolean isValid(char[] exp) {
        if ((exp.length & 1) == 0) {
            return false;
        }
        for (int i = 0; i < exp.length; i = i + 2) {
            if ((exp[i] != '1') && (exp[i] != '0')) {
                return false;
            }
        }
        for (int i = 1; i < exp.length; i = i + 2) {
            if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
                return false;
            }
        }
        return true;
    }

    public static int num1(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }
        return p(exp, desired, 0, exp.length - 1);
    }

    public static int p(char[] exp, boolean desired, int l, int r){
        if (l == r) {
            if (exp[l] == '1') {
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }
        int res = 0;
        if (desired){
            for (int i = l+1; i < r; i+=2){
                switch (exp[i]){
                    case '&':
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                    case '|':
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                    case '^':
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                }
            }
        } else {
            for (int i = l+1; i < r; i+=2){
                switch (exp[i]){
                    case '&':
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                    case '|':
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                    case '^':
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                }
            }
        }
        return res;
    }

    public static int num2(String express, boolean desired){
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }
        int[][] t = new int[exp.length][exp.length];
        int[][] f = new int[exp.length][exp.length];
        t[0][0] = exp[0] == '0' ? 0 : 1;
        f[0][0] = exp[0] == '1' ? 0 : 1;
        for (int i = 2; i < exp.length; i += 2){
            t[i][i] = exp[i] == '0' ? 0 : 1;
            f[i][i] = exp[i] == '0' ? 1 : 0;
            for (int j = i - 2; j >=0; j-=2){
                for (int k = j; k < i; k +=2){
                    if (exp[k + 1] == '&') {
                        t[j][i] += t[j][k] * t[k + 2][i];
                        f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i] + f[j][k] * t[k + 2][i];
                    } else if (exp[k + 1] == '|') {
                        t[j][i] += (f[j][k] + t[j][k]) * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i];
                    } else {
                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
                    }
                }
            }
        }
        return desired ? t[0][t.length - 1] : f[0][f.length - 1];
    }

    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
        System.out.println(num1(express, desired));
        System.out.println(num2(express, desired));

    }

}
