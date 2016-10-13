package chapter_7_bitoperation_me;

/**
 * Created by bigming on 16/10/13.
 * 题目: 给定一个整型数组arr和一个大于1的整数k.已知arr中只有1个数出现了1次,
 *      其他的数都出现了k次,请返回只出现了1次的数.
 * 难度: **
 * 要求: 时间复杂度为O(N), 额外空间复杂度为O(1)
 * 思路: 将其转化为K进制数,如果k个相同的k进制数进行无进位相加,相加的结果一定
 *      是每一位上都是0的k进制数.
 */
public class Problem_06_KTimesOneTime_me {
    public static int onceNum(int[] arr, int k){
        int[] eO = new int[32];
        for (int i = 0; i != arr.length; i++){
            setExclusiveOr(eO, arr[i], k);
        }
        int res = getNumFromKSystem(eO, k);
        return res;
    }

    public static void setExclusiveOr(int[] eO, int value, int k){
        int[] curKSystem = getKSysNumFromNum(value, k);
        for (int i = 0; i != eO.length; i++){
            eO[i] = (eO[i] + curKSystem[i]) % k;
        }
    }

    public static int[] getKSysNumFromNum(int value, int k){
        int[] res = new int[32];
        int index = 0;
        while (value != 0){
            res[index++] = value % k;
            value = value / k;
        }
        return res;
    }

    public static int getNumFromKSystem(int[] eO, int k){
        int res = 0;
        for (int i = eO.length - 1; i != -1; i--){
            res = res * k + eO[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
        System.out.println(onceNum(test1, 3));

        int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
        System.out.println(onceNum(test2, 5));

    }
}
