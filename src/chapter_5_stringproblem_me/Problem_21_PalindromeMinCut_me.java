package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/10/6.
 * 题目: 回文最少分割数. 给定一个字符串str, 返回把str全部切成回文子串的最小分割数
 * 举例: str = "ABA", 不需要切割,str本身就是回文串,返回0
 *      str = "ACDCDCDAD", 最少需要切2次变成3个回文子串,所以返回2.
 * 难度: ***
 * 思路: 经典的动态规划问题.用动态规划数组dp, dp[i]表示子串str[i..len-1]至少需要切割
 *      几次.dp[0]就是最后的结果.且有dp[i]= Min{dp[j+1]+1, i<=j<len,且str[i..j]是
 *      一个回文串.} 判断str[i..j]是否为回文串,可以用二维数组boolean[][]p, 如果p[i][j]
 *      为true,说明str[i..j]是回文串.
 *      p[i][j]为true一定是下面三种情况:
 *      1. str[i..j]由1个字符组成
 *      2. str[i..j]由2个字符组成且2个字符相等
 *      3. str[i+1, j-1]是回文串,即p[i+1][j-1]为true,且str[i]= str[j].
 *      在计算dp数组的过程中,位置i是从右向左依次计算的,而对每一个i来说,又依次从i位置向右
 *      枚举所有的位置j(i<=j<len),以此来决策出dp[i]的值.所以对p[i][j]来说,p[i+1][j-1]
 *      值一定以及计算过.
 */
public class Problem_21_PalindromeMinCut_me {
    public static int minCut(String str){
        if (str == null || str.equals("")){
            return 0;
        }
        char[] chas = str.toCharArray();
        int len = chas.length;
        int[] dp = new int[len+1];
        dp[len] = -1;
        boolean[][] p = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--){
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < len; j++){
                if (chas[i] == chas[j] && (j - i < 2 || p[i+1][j-1])){
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j+1] + 1);
                }
            }
        }
        return dp[0];
    }

    // 测试用~
    public static String getRandomStringOnlyAToD(int len) {
        int range = 'D' - 'A' + 1;
        char[] charArr = new char[(int) (Math.random() * (len + 1))];
        for (int i = 0; i != charArr.length; i++) {
            charArr[i] = (char) ((int) (Math.random() * range) + 'A');
        }
        return String.valueOf(charArr);
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int testTimes = 5;
        String str = null;
        for (int i = 0; i != testTimes; i++) {
            str = getRandomStringOnlyAToD(maxLen);
            System.out.print("\"" + str + "\"" + " : ");
            System.out.println(minCut(str));
        }

    }

}
