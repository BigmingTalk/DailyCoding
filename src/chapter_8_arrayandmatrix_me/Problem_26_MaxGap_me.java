package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/30.
 * 题目: 数组排序之后相邻数的最大差值
 * 难度: **
 * 要求: 如果arr的长度为N,要求时间复杂度为O(N)
 * 思路: 利用桶排序的思想做到时间复杂度为O(N)
 */
public class Problem_26_MaxGap_me {
    public static int maxGap(int[] nums){
        if (nums == null || nums.length < 2){
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max){
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++){
            bid = bucket(nums[i], len, min, max); // 得到桶号
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = 0;
        int i = 0;
        while (i <= len){
            if (hasNum[i++]){
                lastMax = maxs[i - 1]; // 第一个不为空的桶
                break;
            }
        }
        for (; i <= len; i++){
            if (hasNum[i]){
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    // 用long类型防止溢出
    public static int bucket(long num, long len, long min, long max){
        return (int)((num - min) * len / (max - min));
    }

    public static void main(String[] args) {
        int[] arr = { 11, 10, 9, 3, 1, 12 };
        System.out.println(maxGap(arr));

    }
}
