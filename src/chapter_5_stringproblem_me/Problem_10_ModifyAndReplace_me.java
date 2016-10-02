package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/10/2.
 * 题目: 给定一个字符类型的数组chas[], chas右半区全是空字符,左半区不含有空字符,
 *      现在想将左半区中所有的空格字符替换成"%20",假设chas右半区足够大,可以满足
 *      替换所需要的空间,请完成替换函数.
 * 举例: 如果把chas的左半区看做字符串,为"a b  c",假设chas的右半区足够大.替换后,
 *      chas的左半区为"a%20b%20%20c".
 * 要求: 替换函数的时间复杂度为O(N),额外空间复杂度为O(1).
 * 补充题目: 给定一个字符类型的数组chas[], 其中只含有数字字符和"*"字符.现在想把
 *          所有的"*"字符挪到chas的左边,数字字符挪到chas的右边.请完成调整函数.
 * 举例: 如果把chas看做字符串,为"12**345".调整后chas为"**12345".
 * 要求: 1. 调整函数的时间复杂度为O(N),额外空间复杂度为O(1).
 *      2. 不得改变数字字符从左到右出现的顺序.
 * 难度: *
 *
 */
public class Problem_10_ModifyAndReplace_me {
    public static void replace(char[] chas){
        if (chas == null || chas.length == 0){
            return;
        }
        int num = 0;
        int len  =0;
        for (len = 0; len < chas.length && chas[len] != 0; len++){
            if (chas[len] == ' '){
                num++;
            }
        }
        int j = len + num * 2 - 1;
        for (int  i = len - 1; i > -1; i--){
            if (chas[i] != ' '){
                chas[j--] = chas[i];
            } else {
                chas[j--] = '0';
                chas[j--] = '2';
                chas[j--] = '%';
            }
        }
    }

    public static void modify(char[] chars){
        if (chars == null || chars.length == 0){
            return;
        }
        int j = chars.length - 1;
        for (int i = chars.length - 1; i > -1; i--){
            if (chars[i] != '*'){
                chars[j--] = chars[i];
            }
        }
        for (; j > -1;){
            chars[j--] = '*';
        }
    }

    public static void main(String[] args) {
        char[] chas1 = { 'a', ' ', 'b', ' ', ' ', 'c', 0, 0, 0, 0, 0, 0, 0, 0, };
        replace(chas1);
        System.out.println(String.valueOf(chas1));

        char[] chas2 = { '1', '2', '*', '*', '3', '4', '5' };
        modify(chas2);
        System.out.println(String.valueOf(chas2));

    }

}
