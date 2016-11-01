package chapter_9_others_me;

/**
 * Created by bigming on 16/11/1.
 * 题目: 一行代码求两个数的最大公约数
 * 难度: **
 * 思路: 辗转相除法
 *
 */
public class Problem_02_GCD_me {
    public static int gcd(int m, int n){
        return n == 0 ? m : gcd(n, m % n);
    }

    public static void main(String[] args) {
        System.out.println(gcd(18, 27));
    }
}
