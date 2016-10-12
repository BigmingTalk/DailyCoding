package chapter_7_bitoperation_me;

/**
 * Created by bigming on 16/10/12.
 * 题目: 只用位运算不用算术运算实现整数的加减乘除运算
 * 要求: 如果给定的aheb执行加减乘除的某些结果本来就会导致数据的溢出,
 *      那么你实现的函数不必对那些结果负责
 * 难度: **
 * 思路: 加法: 考虑进位和不考虑进位两种情况分开考虑
 *      减法: a-b则是a+(-b),算出-b的值再加法即可
 *      乘法: a*b = a*2^0*b0 + a*2^1*b1 + ...
 *      除法: 分a与b的正负考虑,又考虑到最小负数和最大正数的绝对值不相等,
 *           所以需要考虑一些特殊情况
 *
 */
public class Problem_03_AddMinusMultiDivideByBit_me {
    public static int add(int a, int b){
        int sum = a;
        while (b != 0){
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    // 相反数
    public static int negNum(int n){
        return add(~n, 1);
    }

    public static int minus(int a, int b){
        return add(a, negNum(b));
    }

    public static int multi(int a, int b){
        int res = 0;
        while (b != 0){
            if ((b & 1) != 0){
                res = add(res, a);
            }
            a <<= 1;
            b >>= 1;
        }
        return res;
    }

    public static boolean isNeg(int n){
        return n < 0;
    }

    // 除负数最小值及正数最小值的特殊情况
    public static int div(int a, int b){
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 31; i > -1; i = minus(i, 1)){
            if ((x >> i) >= y){
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a, int b){
        if (b == 0){
            throw new RuntimeException("divisor is 0");
        }
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
            return 1;
        } else if (b == Integer.MIN_VALUE){
            return 0;
        } else if (a == Integer.MIN_VALUE){
            int res = div(add(a, 1), b);
            return add(res, div(minus(a, multi(res, b)), b));
        } else {
            return div(a, b);
        }
    }

    public static void main(String[] args) {
        int a = (int) (Math.random() * 100000) - 50000;
        int b = (int) (Math.random() * 100000) - 50000;
        System.out.println("a = " + a + ", b = " + b);
        System.out.println(add(a, b));
        System.out.println(a + b);
        System.out.println("=========");
        System.out.println(minus(a, b));
        System.out.println(a - b);
        System.out.println("=========");
        System.out.println(multi(a, b));
        System.out.println(a * b);
        System.out.println("=========");
        System.out.println(divide(a, b));
        System.out.println(a / b);
        System.out.println("=========");

        a = Integer.MIN_VALUE;
        b = 32;
        System.out.println(divide(a, b));
        System.out.println(a / b);

    }

}
