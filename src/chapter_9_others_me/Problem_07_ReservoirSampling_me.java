package chapter_9_others_me;

/**
 * Created by bigming on 16/11/3.
 * 题目: 蓄水池算法
 *      有一个机器按自然数序列的方式吐出球(1号球, 2号球, 3号球,....), 你有一个
 *      袋子,袋子最多只能装下K个球, 并且除袋子以外,你没有更多的空间. 设计一种方案,
 *      使得当机器吐出第N号球的时候(N > K), 你袋子中的球数是K个,同时可以保证从
 *      1号球到N号球中的每一个,被选进袋子的概率都是K/N.举一个例子,有一个只能装
 *      10个球的袋子, 当吐出100个球时,袋子里有10个球,并且1~100号球每一个被选中
 *      的概率都是10/100, 继续吐球,到底1000个时, 袋子里有10个球,并且1~1000个球
 *      的每一个球被选中的概率都是10/1000.即吐球的同时,已经吐出的球被选中的概率也
 *      动态地变化.
 * 难度: **
 * 思路: 首先从题意分析,已经吐出的球被选中的概率也动态地变化,说明其哪怕之前被选入袋中,
 *      也是有一定概率被拿出来的,或者说没有永远一定在袋子里的球.具体是用蓄水池算法:
 *      1. 处理1~k号球时,直接放进袋子里
 *      2. 处理第i号球时(i > k), 以k/i的概率决定是否将第i号球放进袋子, 如果不决定
 *         将第i号球放进袋子,直接扔掉第i号球.如果决定将第i号球放进袋子,那么久从袋子
 *         里的k个球中随机扔掉一个,然后把第i号球放入袋子.
 *      3. 处理第i+1号球时重复步骤1或步骤2.
 */
public class Problem_07_ReservoirSampling_me {
    public static int rand(int max){
        return (int) (Math.random() * max) + 1;
    }

    public static int[] getKNumsRand(int k, int max){
        if (max < 1 || k < 1){
            return null;
        }
        int[] res = new int[Math.min(k, max)];
        for (int i = 0; i != res.length; i++){
            res[i] = i + 1;
        }
        for (int i = k + 1; i < max + 1; i++){
            if (rand(i) <= k){ // 被选中的概率是 k / i
                res[rand(k) - 1] = i; // i随机替换掉袋子中的一个
            }
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] res = getKNumsRand(10, 10000);
        printArray(res);
    }
}
