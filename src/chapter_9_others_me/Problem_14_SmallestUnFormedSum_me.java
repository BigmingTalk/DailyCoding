package chapter_9_others_me;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by bigming on 16/11/15.
 * 题目: 给定一个正数数组arr,其中所有的值都为正整数,以下是最小不可组成和的概念:
 *      1. 把arr每个子集内的所有元素加起来会出现很多值,其中最小的记为min,最大
 *         的记为max.
 *      2. 在区间[min, max]上,如果有数不可以被arr中某一个子集相加得到,那么其
 *         中最小的那个数是arr的最小不可组成和.
 *      3. 在区间[min, max]上,如果所有的数都可以被arr的某一个子集相加得到,那
 *         么max+1是arr的最小不可组成和.
 *      请写函数返回正数数组arr的最小不可组成和.
 * 进阶题目: 如果已知整数数组中肯定有1这个数,是否能更快地得到最小不可组成和.
 * 难度: **
 * 思路: 解法一: 暴力搜索,时间复杂度为O(2^N), 额外空间复杂度为O(N),因为递归N层
 *      解法二: 动态规划,主要是用了如果a[0..i]这个范围上的数组成的所有子集可以
 *             累加出k,那么arr[0..i+1]这个范围上的数组成的所有子集则必然可以
 *             累加出k + arr[i+1].
 *      进阶题目: 可以先排序,用range表示当计算到arr[i]时,[1,range]内的所有正
 *               数都可以被arr[0..i-1]的某一个子集加出来.所以如果arr[i]>range+1,
 *               则说明往后都不能出现range+1,返回range+1.
 */
public class Problem_14_SmallestUnFormedSum_me {
    public static int unformedSum1(int[] arr){
        if (arr == null || arr.length == 0){
            return 1;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        process(arr, 0, 0, set); // 搜集所有的子集的和
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++){
            min = Math.min(min, arr[i]);
        }
        for (int i = min + 1; i != Integer.MIN_VALUE; i++){
            if (!set.contains(i)){
                return i;
            }
        }
        return 0;
    }

    public static void process(int[] arr, int i, int sum, HashSet<Integer> set){
        if (i == arr.length){
            set.add(sum);
            return;
        }
        process(arr, i + 1, sum, set); // 不加上arr[i]的情况
        process(arr, i + 1, sum + arr[i], set); // 加上arr[i]的情况
    }

    public static int unformedSum2(int[] arr){
        if (arr == null || arr.length == 0){
            return 1;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++){
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        boolean[] dp =  new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < arr.length; i++){
            for (int j =  sum; j >= arr[i]; j--){
                // 下面这里不是 dp[j] = dp[j - arr[i]] ? true : false;
                // 因为可能之前设置为true了,而这里保持true就好,不应该将其
                // 设置为false.
                dp[j] = dp[j - arr[i]] ? true : dp[j];
            }
        }
        for (int i =  min; i != dp.length; i++){
            if (!dp[i]) {
                return i;
            }
        }
        return sum + 1;
    }

    public static int unformedSum3(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        Arrays.sort(arr);
        int range = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > range + 1){
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }


    public static int[] generateArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) + 1;
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
        int len = 27;
        int max = 30;
        int[] arr = generateArray(len, max);
        printArray(arr);
        long start = System.currentTimeMillis();
        System.out.println(unformedSum1(arr));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        start = System.currentTimeMillis();
        System.out.println(unformedSum2(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        start = System.currentTimeMillis();
        System.out.println(unformedSum3(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        System.out.println("set arr[0] to 1");
        arr[0] = 1;
        start = System.currentTimeMillis();
        System.out.println(unformedSum3(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");

    }
}
