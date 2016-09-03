package chapter_8_arrayandmatrix_me;

import java.util.HashMap;

/**
 * Created by bigming on 16/9/3.
 * 题目: 未排序数组中累加和为k给定值的最长子数组系列问题,其中元素
 *      可正可负.
 *      变形: 所有子数组中正数与负数个数相等的最长子数组长度
 *           0,1数组中0和1个数相等的最长子数组长度
 * 难度: **
 * 思路: 对于原题,可以运用累加和的思想,并且用hashmap记录累加和
 *      第一次出现的位置,如map(sum, j)代表出现累加和为sum的
 *      第一次位置为j.所以当遍历到当前位置i时,假设到当前i位置的
 *      累加和为sum,查找hashmap中是否存在sum-k的key,如果存在,其
 *      对应的索引为置为j,说明i-j之间的元素和为sum-(sum-k) = k,
 *      有因为map中是记录第一次出现sum的位置,所以i-j是到位置i为止
 *      累加和为k的最长子数组.
 *      改编题: 正数负数相等只需要把正数变为1,负数变为-1,0不变,求
 *             累加和为0的最长子数组.
 *             0,1数组只需要把0变为-1,1不变,累加和为0的最长子数组
 *
 */
public class Problem_11_LongestSumSubArrayLength_me {
    public static int maxLength(int[] arr, int k){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++){
            sum  = sum + arr[i];
            if (map.containsKey(sum - k)){
                if (i - map.get(sum-k) > len){
                    len = i - map.get(sum - k);
                }
            }
            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }
        return len;
    }

    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = generateArray(20);
        printArray(arr);
        System.out.println(maxLength(arr, 10));

    }

}
