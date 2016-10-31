package chapter_9_others_me;

/**
 * Created by bigming on 16/10/31.
 * 题目: 给定一个等概率随机产生1~5的随机函数rand1To5, 除此之外,不能使用任何
 *      额外的随机机制,请用rand1To5实现等概率随机产生1~7的随机函数rand1To7.
 *      补充题目: 给定一个以p概率产生0, 以1-p概率产生1的随机函数rand01p,
 *      除此之外,不能使用任何额外的随机机制,请用rand01p实现等概率随机产生1~6
 *      的随机函数rand1To6.
 *      进阶题目: 给定一个等概率随机产生1~M的随机函数rand1ToM,除此之外,不能
 *      使用任何额外的随机机制.有两个输入参数,分别为m和n,请用rand1ToM(m)实现
 *      等概率随机产生1~n的随机函数rand1ToN.
 * 难度: 原问题: **
 *      补充问题: **
 *      进阶题目: ***
 * 思路: 插空和筛选的过程!!!
 *      只要给定某一个区间上的等概率随机函数,就可以实现任意区间上的随机函数!
 */
public class Problem_01_Rand5ToRand7_me {
    public static int rand1To5(){
        return (int)(Math.random() * 5) + 1;
    }

    public static int rand01p(){
        double p = 0.71;
        return Math.random() < p ? 0 : 1;
    }

    public static int rand1ToM(int m){
        return (int)(Math.random() * m) + 1;
    }

    public static int rand1To7(){
        int num =  0;
        do {
            num = (rand1To5() - 1) * 5 + rand1To5() - 1; // 插空的过程
        }while (num > 20); // 筛选的过程
        return num % 7 + 1;
    }

    public static int rand01(){
        int num = 0;
        do {
            num = rand01p();
        } while (num == rand01p());
        return num;
    }

    public static int rand0To3(){
        return rand01() * 2 + rand01();
    }

    public static int rand1To6(){
        int num = 0;
        do {
            num = rand0To3() * 4 + rand0To3();
        }while (num > 11);
        return num % 6 + 1;
    }

    public static int rand1ToN(int n, int m){
        int[] nMSys = getMSysNum(n -1, m);
        int[] randNum = getRanMSysNumLessN(nMSys, m);
        return getNumFromMSysNum(randNum, m) + 1;
    }

    // 把value转成m进制数
    public static int[] getMSysNum(int value, int m){
        int[] res = new int[32];
        int index = res.length - 1;
        while (value != 0){
            res[index--] = value % m;
            value = value / m;
        }
        return res;
    }

    // 等概率产生一个0~nMsys范围的数,只不过是用m进制表达的.
    public static int[] getRanMSysNumLessN(int[] nMSys, int m){
        int[] res = new int[nMSys.length];
        int start = 0;
        while (nMSys[start] == 0){
            start++;
        }
        int index = start;
        boolean lastEqual = true;
        while (index != nMSys.length){
            res[index] = rand1ToM(m) - 1;
            if (lastEqual){
                if (res[index] > nMSys[index]){
                    index = start;
                    lastEqual = true;
                    continue;
                } else {
                    lastEqual = res[index] == nMSys[index];
                }
            }
            index++;
        }
        return res;
    }

    // 把m进制数转成十进制数
    public static int getNumFromMSysNum(int[] mSysNum, int m){
        int res = 0;
        for (int i = 0; i != mSysNum.length; i++){
            res = res * m + mSysNum[i];
        }
        return res;
    }

    public static void printCountArray(int[] countArr) {
        for (int i = 0; i != countArr.length; i++) {
            System.out.println(i + " appears " + countArr[i] + " times");
        }
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int[] countArr1 = new int[8];
        for (int i = 0; i != testTimes; i++) {
            countArr1[rand1To7()]++;
        }
        printCountArray(countArr1);

        System.out.println("=====================");

        int[] countArr2 = new int[7];
        for (int i = 0; i != testTimes; i++) {
            countArr2[rand1To6()]++;
        }
        printCountArray(countArr2);

        System.out.println("=====================");

        int n = 17;
        int m = 3;
        int[] countArr3 = new int[n + 1];
        for (int i = 0; i != 2000000; i++) {
            countArr3[rand1ToN(n, m)]++;
        }
        printCountArray(countArr3);

        System.out.println("=====================");

    }
}
