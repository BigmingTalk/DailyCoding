package chapter_7_bitoperation_me;

/**
 * Created by bigming on 16/10/13.
 * 题目: 在其他数都出现偶数次的数组中找到出现奇数次的数
 * 进阶: 有两个数出现了奇数次,其他数都出现了偶数次,打印这两个数
 * 要求: 时间复杂度为O(N), 额外空间复杂度为O(1).
 * 分析: 原题用亦或即可,进阶也用亦或,只不过是两次亦或,第一次亦或的结果
 *      为两个出现奇数次的数的亦或结果,肯定不为0,因为他们不相等,然后找
 *      到这个亦或结果的不为0的最低位,这两个数一定有一个数该位为0,另一
 *      个为1,所以只要下次将所有该位为1的亦或一下,就能找出其中一个出现
 *      奇数次的数,另一个也很容易得到.
 */
public class Problem_05_EvenTimesOddTimes_me {
    public static void printOddTimesNum1(int[] arr){
        int eO = 0;
        for (int cur : arr){
            eO ^= cur;
        }
        System.out.println(eO);
    }

    public static void printOddTimesNum2(int[] arr){
        int eO = 0, eOhasOne = 0;
        for (int cur : arr){
            eO ^= cur;
        }
        int rightOne = eO & (~eO + 1);
        for (int cur : arr){
            if ((cur & rightOne) != 0){
                eOhasOne ^= cur;
            }
        }
        System.out.println(eOhasOne + " " + (eO ^ eOhasOne));
    }

    public static void main(String[] args) {
        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum1(arr1);

        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum2(arr2);

    }
}
