package chapter_4_recursionanddp_me;

/**
 * Created by bigming on 16/9/25.
 * 题目: 给定一个字符串str, str全部由数字字符组成,如果str中某一个或某相邻两个字符
 *      组成的子串值在1~26之间,则这个子串可以转换成一个字母.规定"1"转换为"A","2"
 *      转换为"B",依次类推.写一个函数,求str有多少种不同的转换结果,并返回种数.
 * 难度: **
 * 思路: 首先暴力递归,再找出递归的依赖关系,将递归转换为迭代.定义递归函数p(i),p(i)的
 *      含义是str[0..i-1]已经转换完毕,而str[i..N-1]还没转换的情况下,最终合法的转换
 *      数有多少并返回.
 */
public class Problem_12_NumberToLetter_me {
    public static int num1(String str){
        if (str == null || str.equals("")){
            return  0;
        }
        char[] chs = str.toCharArray();
        return process(chs, 0);
    }

    public static int process(char[] chs, int i){
        if (i == chs.length){
            return 1;
        }
        if (chs[i] == '0'){
            return 0;
        }
        int res = process(chs, i + 1);
        if (i + 1 < chs.length && (chs[i] - '0') * 10 + chs[i+1] - '0' < 27){
            res += process(chs, i + 2);
        }
        return res;
    }

    public static int num2(String str){
        if (str == null || str.equals("")){
            return 0;
        }
        char[] chs = str.toCharArray();
        int cur = chs[chs.length - 1] == '0' ? 0 : 1;
        int next = 1;
        int tmp = 0;
        for (int i = chs.length - 2; i >= 0; i--){
            if (chs[i] == '0'){
                next = cur;
                cur =0;
            } else {
                tmp = cur;
                if ((chs[i] - '0') * 10 + chs[i +1] -'0' < 27){
                    cur += next;
                }
                next = tmp;
            }
        }
        return cur;
    }

    public static void main(String[] args) {
        String str = "781231783161018231";
        System.out.println(num1(str));
        System.out.println(num2(str));
    }
}
