package chapter_8_arrayandmatrix_me;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by bigming on 16/10/18.
 * 题目: 最长的可整合子数组的长度.给定一个数组,返回其中最大可整合子数组的长度.
 * 难度: **
 * 思路: 可整合数组: 如果一个数组再排序之后,每相邻两个数差的绝对值都为1, 则
 *      该数组为可整合数组,如[5,2,3,1,4]排序后为[1,2,3,4,5],满足要求,所
 *      以为可整合数组.
 *      最粗暴的方式: 共N^2个子数组,都排序,判断,时间复杂度为O(N^3*LogN)
 *      简单方式: 也是N^2个子数组,但不排序,判断其是否为可整合数组只需要判断该
 *              数组中没有重复元素,且最大值-最小值+1=数组元素个数即可,时间
 *              复杂度为O(N^2).
 *
 */
public class Problem_08_LongestIntegratedLength_me {
    // 粗暴解法...
    public static int getLIL1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int len = 0;
        for (int i = 0; i < arr.length; i++){
            for (int j = i; j < arr.length; j++){
                if (isIntegrated(arr, i, j)){
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }

    // 判断子数组是否为可整合数组
    public static boolean isIntegrated(int[] arr, int left, int right){
        int[] newArr = Arrays.copyOfRange(arr, left, right + 1); // O(N)
        Arrays.sort(newArr); // O(N*LogN)
        for (int i = 1; i < newArr.length; i++){
            if (newArr[i - 1] != newArr[i] - 1){
                return false;
            }
        }
        return true;
    }

    // 快速方法
    public static int getLIL2(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int max = 0;
        int min = 0;
        int len = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++){
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++){
                if (set.contains(arr[j])){
                    break;
                }
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                set.add(arr[j]);
                if (max - min == j - i){
                    len = Math.max(len, j - i + 1);
                }
            }
            set.clear();
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 1, 7, 9, 8};
        System.out.println(getLIL1(arr));
        System.out.println(getLIL2(arr));

    }

}
