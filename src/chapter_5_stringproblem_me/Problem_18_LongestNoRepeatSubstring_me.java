package chapter_5_stringproblem_me;

import chapter_1_stackandqueue.Problem_04_DogCatQueue;

/**
 * Created by bigming on 16/10/5.
 * 题目: 给定一个字符串str, 返回str的最长无重复字符子串的长度
 * 举例: str = "aabcb", 最长无重复字符子串为"abc", 返回3
 * 要求: 如果str的长度为N,要求时间复杂度为O(N)
 * 难度: **
 * 思路: 用哈希表map, key表示某个字符, value为该字符最近一次出现的位置.
 *      整型变量pre表示如果当前遍历到字符str[i], pre表示在必须以str[i-1]
 *      字符结尾的情况下,最长无重复字符子串开始位置的前一个位置,初始时,pre=-1
 *      整型变量len表示最长的子串长度.
 *      变量过程中,假设a为最近一次出现str[i]的位置,主要的逻辑判断就是a与pre的
 *      大小关系,也就是前一个字符的最近重复字符与当前字符的最近重复字符谁更近一些,
 *      并根据其改变pre.
 * 难度: **
 */
public class Problem_18_LongestNoRepeatSubstring_me {
    public static int maxUnique(String str){
        if (str == null || str.equals("")){
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++){
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0; i < chas.length; i++){
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chas[i]] = i;
        }
        return len;
    }

    // for test
    public static String getRandomString(int len) {
        char[] str = new char[len];
        int base = 'a';
        int range = 'z' - 'a' + 1;
        for (int i = 0; i != len; i++) {
            str[i] = (char) ((int) (Math.random() * range) + base);
        }
        return String.valueOf(str);
    }

    // 打印出最长的str, 只需要增加一个end变量来记录结尾的字符即可
    public static String maxUniqueString(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = -1;
        int pre = -1;
        int cur = 0;
        int end = -1;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            if (cur > len) {
                len = cur;
                end = i;
            }
            map[chas[i]] = i;
        }
        return str.substring(end - len + 1, end + 1);
    }

    public static void main(String[] args) {
        String str = getRandomString(20);
        System.out.println(str);
        System.out.println(maxUnique(str));
        System.out.println(maxUniqueString(str));
    }
}
