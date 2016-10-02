package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/10/2.
 * 题目: 给定一个字符类型的数组chas,请在单词减做逆序调整,只要做到单词顺序
 *      逆序即可,对空格的位置没有特别要求.
 * 举例: 如果把chas看作字符串为"dog loves pig",调整为"pig loves dog"
 *      如果把chas看作字符串为"I'm a student",调整为"student. a I'm"
 * 补充题目: 给定一个字符类型的数组chas和一个整数size,请把大小为size的左半区
 *          整体移动到右半区,右半区整体移动到左半区.
 * 举例: 如果把chas看作字符串为"ABCDE",size=3,调整成"DEABC"
 * 要求: 如果chas长度为N,两道题都要求时间复杂度为O(N),额外空间复杂度为0(1).
 * 难度: *
 *
 */
public class Problem_11_RotateString_me {
    public static void rotateWord(char[] chas){
        if (chas == null || chas.length == 0){
            return;
        }
        reverse(chas, 0, chas.length - 1);
        int l = -1;
        int r = -1;
        for (int i = 0; i < chas.length; i++){
            if (chas[i] != ' '){
                l = i == 0 || chas[i-1] == ' ' ? i : l;
                r = i == chas.length - 1 || chas[i + 1] == ' ' ? i : r;
            }
            if (l != -1 && r != -1){
                reverse(chas, l, r);
                l = -1;
                r = -1;
            }
        }
    }

    public static void reverse(char[] chas, int start, int end){
        char tmp = 0;
        while (start < end){
            tmp = chas[start];
            chas[start] = chas[end];
            chas[end] = tmp;
            start++;
            end--;
        }
    }

    public static void rotate1(char[] chas, int size){
        if (chas == null || size <= 0 || size >= chas.length){
            return;
        }
        reverse(chas, 0, size - 1);
        reverse(chas, size, chas.length - 1);
        reverse(chas, 0, chas.length - 1);
    }

    public static void rotate2(char[] chas, int size){
        if (chas == null || size <= 0 || size >=chas.length){
            return;
        }
        int start = 0;
        int end = chas.length - 1;
        int lpart = size;
        int rpart = chas.length - size;
        int s = Math.min(lpart, rpart);
        int d = lpart - rpart;
        while (true){
            exchange(chas, start, end, s);
            if (d == 0){
                break;
            } else if (d > 0){
                start += s;
                lpart = d;
            } else {
                end -= s;
                rpart = -d;
            }
            s = Math.min(lpart, rpart);
            d = lpart - rpart;
        }
    }

    public static void exchange(char[] chars, int start, int end, int size){
        int i = end - size + 1;
        char tmp = 0;
        while (size-- != 0){
            tmp = chars[start];
            chars[start] = chars[i];
            chars[i] = tmp;
            start++;
            i++;
        }
    }

    public static void main(String[] args) {
        char[] chas1 = { 'd', 'o', 'g', ' ', 'l', 'o', 'v', 'e', 's', ' ', 'p',
                'i', 'g' };
        System.out.println(String.valueOf(chas1));
        rotateWord(chas1);
        System.out.println(String.valueOf(chas1));

        char[] chas2 = { '1', '2', '3', '4', '5', 'A', 'B', 'C' };
        System.out.println(String.valueOf(chas2));
        rotate1(chas2, 5);
        System.out.println(String.valueOf(chas2));
        rotate2(chas2, 3);
        System.out.println(String.valueOf(chas2));

    }

}
