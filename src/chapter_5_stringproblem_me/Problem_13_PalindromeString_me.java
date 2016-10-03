package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/10/3.
 * 题目: 添加最少字符使字符串整体都是回文字符串,返回最终的回文字符串
 * 举例: str = "ABA",不需要添加字符,返回"ABA"
 *      str = "AB",可以返回"ABA"或"BAB"
 * 进阶题目: 如果再给定str的最长回文子序列字符串strlps,返回添加最少字符
 *          情况下,让str整体都是回文字符串的一种结果.进阶问题比原问题多
 *          了一个参数,请做到时间复杂度比原问题的实现低.
 * 举例: str ="A1B21C",返回"AC1B2B1CA"或"CA1B2B1AC".总之,只要是添加的
 *      字符最少,只返回其中一种结果即可.
 * 难度: ***
 * 思路: 用动态规划的方法,dp[i][j]的含义代表子串str[i..j]最少添加几个字符
 *      可以使str[i..j]整体都是回文串,然后找出递推关系.最后由dp数组推倒出
 *      回文字符串.
 *      基金问题采用剥洋葱的方法,一层一层的找出来,然后生成字符串即可.
 *
 */
public class Problem_13_PalindromeString_me {
    public static int[][] getDP(char[] str){
        int[][] dp = new int[str.length][str.length];
        // 遍历方向是从左往右, 从下往上,dp[0][str.length-1]就是答案
        for (int j = 1; j < str.length; j++){
            dp[j-1][j] = str[j-1] == str[j] ? 0 : 1;
            for (int i = j - 2; i > -1; i--){
                if (str[i] == str[j]){
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp;
    }

    public static String getPalindrome1(String str){
        if (str == null || str.length() < 2){
            return str;
        }
        char[] chas = str.toCharArray();
        int[][] dp = getDP(chas);
        char[] res = new char[chas.length + dp[0][chas.length - 1]];
        int i = 0;
        int j = chas.length - 1;
        int resl = 0;
        int resr = res.length - 1;
        while (i <= j){
            if (chas[i] == chas[j]){
                res[resl++] = chas[i++];
                res[resr--] = chas[j--];
            } else if (dp[i][j-1] < dp[i+1][j]){
                res[resl++] = chas[j];
                res[resr--] = chas[j--];
            } else {
                res[resl++] = chas[i];
                res[resr--] = chas[i++];
            }
        }
        return String.valueOf(res);
    }

    // 进阶问题采用剥洋葱的方法来做
    public static String getPalindrome2(String str, String strlps){
        if (str == null || str.equals("")){
            return "";
        }
        char[] chas = str.toCharArray();
        char[] lps = strlps.toCharArray();
        char[] res = new char[2 * chas.length - lps.length];
        int chasl = 0;
        int chasr = chas.length - 1;
        int lpsl = 0;
        int lpsr = lps.length - 1;
        int resl = 0;
        int resr = res.length - 1;
        int tmpl = 0;
        int tmpr =0;
        while (lpsl <= lpsr){
            tmpl = chasl;
            tmpr = chasr;
            while (chas[chasl] != lps[lpsl]){
                chasl++;
            }
            while (chas[chasr] != lps[lpsr]){
                chasr--;
            }
            set(res, resl, resr, chas, tmpl, chasl, chasr, tmpr);
            resl += chasl - tmpl + tmpr - chasr;
            resr -= chasl - tmpl + tmpr - chasr;
            res[resl++] = chas[chasl++];
            res[resr--] = chas[chasr--];
            lpsl++;
            lpsr--;
        }
        return String.valueOf(res);
    }

    public static void set(char[] res, int resl, int resr, char[] chas, int ls,
                           int le, int rs, int re){
        for (int i = ls; i < le; i++){
            res[resl++] = chas[i];
            res[resr--] = chas[i];
        }
        for (int i = re; i > rs; i--){
            res[resl++] = chas[i];
            res[resr--] = chas[i];
        }
    }

    public static void main(String[] args) {
        String str = "AB1CD2EFG3H43IJK2L1MN";
        System.out.println(getPalindrome1(str));

        String strlps = "1234321";
        System.out.println(getPalindrome2(str, strlps));

    }

}
