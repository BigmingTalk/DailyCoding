package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/10/9.
 * <p/>
 * 题目: 字符串匹配问题.给定字符串str,其中绝对不会哟字符'.'和'*'.再给定字符串exp,
 * 其中可以含有'.'或'*','*'字符不能是exp的首字符,并且任意两个'*'不相邻.exp
 * 中的'.'代表任何一个字符.exp中的'*'的前一个字符可以有0个或者多个.请写一个函数,
 * 判断str是否能被exp匹配.
 * 难度: ***
 * 思路: 用递归的方式来解决.首先判断str和exp是否符合格式要求.然后递归函数process(s, e, si, ei)
 * 代表str[si..slen]与exp[ei..elen]是否匹配.
 * 最后将递归改写为非递归.
 * 递归改写为非递归的一般方法(自我总结):
 * 1. 找出递归函数的状态,即变化的量,将这些变量用状态表来表示,从而避免重复计算
 * 2. 找出变量(或者说是状态)之间的依赖关系
 * 3. 根据具体情况来对状态表中的一些初值进行赋值
 * 4. 根据依赖关系进行遍历计算
 */
public class Problem_22_RegularExpressionMatch_me {
    public static boolean isValid(char[] s, char[] e) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) ? process(s, e, 0, 0) : false;
    }

    public static boolean process(char[] s, char[] e, int si, int ei) {
        if (ei == e.length) {
            return si == s.length;
        }
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            return si != s.length && (e[ei] == s[si] || e[ei] == '.')
                    && process(s, e, si + 1, ei + 1);
        }
        while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
            if (process(s, e, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process(s, e, si, ei + 2);
    }

    public static boolean isMatchDP(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = str.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        boolean[][] dp = initDPMap(s, e);
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static boolean[][] initDPMap(char[] s, char[] e) {
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;

        // 最后一列的初始化
        for (int j = elen - 2; j > -1; j = j - 2) {
            if (e[j] != '*' && e[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }

        // 倒数第一列默认为false

        // 倒数第二列的其中一个值,只有该值可能为true
        if (slen > 0 && elen > 0) {
            if (e[elen - 1] == '.' || s[slen - 1] == e[elen - 1]) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp));
        System.out.println(isMatchDP(str, exp));
    }

}
