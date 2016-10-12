package chapter_7_bitoperation_me;

/**
 * Created by bigming on 16/10/12.
 * 题目: 整数的二进制表达中有多少个1
 * 难度: **
 * 思路: 多种解法:
 *      1. 每次右移一位
 *      2. n &= (n - 1)
 *      3. n -= n & (~n + 1)
 *      4. 平行算法
 *      5. MIT hackmem等偏,怪,难的逆天算法
 */
public class Problem_04_NumberOf1_me {
    public static int count1(int n){
        int res = 0;
        while (n != 0){
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    public static int count2(int n){
        int res = 0;
        while (n != 0){
            n &= (n - 1);
            res++;
        }
        return res;
    }

    public static int count3(int n){
        int res = 0;
        while (n != 0){
            n -= n & (~n + 1);
            res++;
        }
        return res;
    }

    public static int count4(int n){
        n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }


    public static void printNumBit(int n) {
        for (int i = 31; i != -1; i--) {
            if ((n & (1 << i)) != 0) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int num = -1;
        System.out.println(count1(num));
        System.out.println(count2(num));
        System.out.println(count3(num));
        System.out.println(count4(num));
        /*printNumBit(0x55555555);
        printNumBit(0x33333333);
        printNumBit(0x0f0f0f0f);
        printNumBit(0x00ff00ff);
        printNumBit(0x0000ffff);*/

    }
}
