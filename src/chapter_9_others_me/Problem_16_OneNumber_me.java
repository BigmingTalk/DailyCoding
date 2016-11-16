package chapter_9_others_me;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by bigming on 16/11/16.
 * 题目: 给定一个整数n, 返回从1到n的数字中1出现的次数.
 * 例如: n = 5, 1~n为 1, 2, 3, 4, 5, 那么1出现了1次,所以返回1.
 *      n = 11, 1出现了4次,分别为1(1次), 10(1次), 11(2次).
 * 难度: ***
 * 思路: 解法一: 暴力循环,每个数都循环求解有多少个1,每次求解时间复杂度为O(LogN),
 *              所以总的时间复杂度为O(NLogN).
 *      解法二: 分解求解, 比如N = 24316时,先求解4317~24316有多少个1, 求解4317~24316
 *             有多少个1又分解为最高位有多少个1以及后面为1的个数. 最高位位1的个数要看
 *             最高位是否为1, 后面有多少位为1的个数=最高位的数字 * 除去最高位后剩下的
 *             位数 * 某一位固定为1的情况下,剩下的几位数都可以从0~9自由变化,得到的总数
 *             然后再递归求解1~4316上有多少个1.
 */
public class Problem_16_OneNumber_me {
    public static int solution1(int num) {
        if (num < 1){
            return 0;
        }
        int count = 0;
        for (int i = 0; i != num + 1; i++){
            count += get1Nums(i);
        }
        return count;
    }

    public static int get1Nums(int num) {
        int res = 0;
        while (num != 0){
            if (num % 10== 1){
                res++;
            }
            num /= 10;
        }
        return res;
    }

    public static int getLenOfNum(int num) {
        int len = 0;
        while (num != 0){
            len++;
            num /=  10;
        }
        return len;
    }

    public static int powerBaseOf10(int base) {
        return (int) Math.pow(10, base);
    }

    public static int solution2(int num) {
        if (num < 1){
            return 0;
        }
        int len = getLenOfNum(num);
        if (len == 1){
            return 1;
        }
        int tmp = powerBaseOf10(len - 1);
        int first = num / tmp;
        int firstOneNum = first == 1 ? num % tmp + 1 : tmp;
        int otherOneNum = first * (len - 1) * (tmp / 10);
        return firstOneNum + otherOneNum + solution2(num % tmp);
    }

    public static void main(String[] args) {
        int num = 5124221;
        long start1 = System.currentTimeMillis();
        System.out.println(solution1(num));
        long end1 = System.currentTimeMillis();
        System.out.println("cost time: " + (end1 - start1) + " ms");

        long start2 = System.currentTimeMillis();
        System.out.println(solution2(num));
        long end2 = System.currentTimeMillis();
        System.out.println("cost time: " + (end2 - start2) + " ms");

    }
}
