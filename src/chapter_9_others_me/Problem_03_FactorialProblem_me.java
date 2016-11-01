package chapter_9_others_me;

/**
 * Created by bigming on 16/11/1.
 * 题目: 有关阶乘的两个问题
 *      给定一个非负整数,返回N!结果的末尾为0的数量.
 *      例如: 3!=6, 结果的末尾没有0, 则返回0. 5!=120, 结果的末尾有1个0,
 *      返回1.
 * 进阶题目: 给定一个非负整数N, 如果用二进制数表达N!的结果,返回最低位的1在哪个
 *      位置上,认为最右的位置为位置0.
 *      例如: 1!= 1, 最低位的1在0位置上. 2! = 2, 最低位的1在1位置上.
 * 难度: 原问题: **
 *      进阶问题: ***
 * 思路: 原问题的本质上一共有多少个5因子,因为2因子一定比5因子多.
 *      进阶问题的本质是一共有多少个2因此.因为每一个2因子都会在最低位多一位0.
 */
public class Problem_03_FactorialProblem_me {

    // 时间复杂度为O(NLogN)
    public static int zeroNum1(int num){
        if (num < 0){
            return 0;
        }
        int res = 0;
        int cur = 0;
        for (int i = 5; i < num + 1; i = i + 5){
            cur = i;
            while (cur % 5 == 0){
                res++;
                cur /= 5;
            }
        }
        return res;
    }

    // 时间复杂度为O(LogN), 简单理解就是有1个5的有多少,有2个5的有多少, 3个5的有多少...
    public static int zeroNum2(int num){
        if (num < 0){
            return 0;
        }
        int res = 0;
        while (num != 0){
            res += num / 5;
            num /= 5;
        }
        return res;
    }

    // 本质上就是有多少个2因子
    public static int rightOne1(int num){
        if (num < 1){
            return -1;
        }
        int res = 0;
        while (num != 0){
            num >>>= 1;
            res += num;
        }
        return res;
    }

    // 如果把N!的结果中因子2的总个数即为Z,把N的二进制数表达式中1的个数即为m,
    // 还存在如下一个关系 Z = N - m
    public static int rightOne2(int num){
        if (num < 1){
            return -1;
        }
        int ones = 0;
        int tmp = num;
        while (tmp != 0){
            ones += (tmp & 1) != 0 ? 1 : 0;
            tmp >>>= 1;
        }
        return num - ones;
    }

    public static void main(String[] args) {
        int num = 1000000000;

        System.out.println(zeroNum2(num));
        System.out.println(zeroNum1(num));

        System.out.println(rightOne2(num));
        System.out.println(rightOne1(num));

    }


}
