package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/10/3.
 * 题目: 括号字符串的有效性和最长有效长度
 * 举例: str="(()())",返回true, str="()(",返回false
 * 难度: *
 * 补充题目: 给定一个括号字符串str, 返回最长的有效括号子串.
 * 难度: **
 * 思路: 原问题的思路是遍历即可,如果发现)的数量多于(,直接返回false
 *      补充问题: 用动态规划的方法,dp[i]的值表示str[0..i]中必须以
 *      字符str[i]结尾的最长的有效括号子串长度.
 *
 */
public class Problem_14_ParenthesesProblem_me {
    public static boolean isValid(String str){
        if (str == null || str.equals("")){
            return false;
        }
        char[] chas = str.toCharArray();
        int status = 0;
        for (int i = 0; i < chas.length; i++){
            if (chas[i] != '(' && chas[i] != ')'){
                return false;
            }
            if (chas[i] == ')' && --status < 0){
                return false;
            }
            if (chas[i] == '(') {
                status++;
            }
        }

        return status == 0;
    }

    public static int maxLength(String str){
        if (str == null || str.equals("")){
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] dp = new int[str.length()];
        int pre = 0;
        int res =0;
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == ')') {
                pre = i - dp[i-1] - 1;
                if (pre >= 0 && chas[pre] == '('){
                    dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "((())())";
        System.out.println(isValid(str1));
        System.out.println(maxLength(str1));

        String str2 = "(())(()(()))";
        System.out.println(isValid(str2));
        System.out.println(maxLength(str2));

        String str3 = "()(()()(";
        System.out.println(isValid(str3));
        System.out.println(maxLength(str3));

    }
}
