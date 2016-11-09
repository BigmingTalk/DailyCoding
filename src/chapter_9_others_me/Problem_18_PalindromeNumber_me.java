package chapter_9_others_me;

/**
 * Created by bigming on 16/11/9.
 * 题目: 定义回文数的概念如下:
 *      1. 如果一个非负数左右完全对应,则该数是回文数,例如: 121, 22等
 *      2. 如果一个负数的绝对值左右完全对应,也是回文数,例如: -121, -22等.
 * 给定一个32位整数num,判断num是否是回文数.
 * 难度: *
 */
public class Problem_18_PalindromeNumber_me {
    public static boolean isPalindrome(int n){
        if(n == Integer.MIN_VALUE){
            return false;
        }
        n = Math.abs(n);
        int help = 1;
        while (n / help >= 10){ // 防止help溢出
            help *= 10;
        }
        while (n != 0){
            if (n / help != n % 10){
                return false;
            }
            n = (n % help) / 10;
            help /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        int test = -10001;
        System.out.println(isPalindrome(test));

    }
}
