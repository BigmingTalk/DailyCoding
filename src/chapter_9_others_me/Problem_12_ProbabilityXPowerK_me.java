package chapter_9_others_me;

/**
 * Created by bigming on 16/11/6.
 * 题目: 调整[0,x)区间上的数出现的概率
 * 函数Math.random()等概率随机返回一个在[0,1)范围上的数,在[0,x)
 * 区间上的数出现的概率为x(0<x<=1).给定一个大于0的整数k,并且可以使用
 * Math.random()函数,实现一个函数依然返回[0,1)范围上的数,但是在[0,x)
 * 区间上的数出现的概率为x^k(0<x<=1).
 * 难度: *
 * 思路: 实现区间[0,x)上的数返回的概率是x^2, 只需要调用2次Math.random(),
 * 返回最大的那个数即可,因为这样的算法要返回[0,x)的话,必须两次都小于x,
 * 所以概率就是x^2. 所以x^k同理
 */
public class Problem_12_ProbabilityXPowerK_me {
    public static double randXPowerK(int k) {
        if (k < 1) {
            return 0;
        }
        double res = -1;
        for (int i = 0; i != k; i++) {
            res = Math.max(res, Math.random());
        }
        return res;
    }

    public static void main(String[] args) {
        double range = 0.5;
        int times = 10000000;
        int count = 0;
        for (int i = 0; i != times; i++) {
            if (randXPowerK(3) < range) {
                count++;
            }
        }
        double p = (double) count / (double) times;
        System.out.println("range [0," + range + "), probability: " + p);
    }
}
