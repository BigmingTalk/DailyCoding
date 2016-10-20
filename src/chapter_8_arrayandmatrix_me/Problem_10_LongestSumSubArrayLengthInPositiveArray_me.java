package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/20.
 * 题目: 未排序正数数组中累加和为给定值的最长子数组长度
 *      例如: arr = [1,2,1,1,1], k=3,累加和为3的最长子数组为[1,1,1],
 *          所以结果返回3.
 * 难度: 3
 * 思路: 因为是正数数组,所以可以做到时间复杂度为O(N).用两个变量left和right表示
 *      子数组的位置即可.
 *
 */
public class Problem_10_LongestSumSubArrayLengthInPositiveArray_me {
    public static int getMaxLength(int[] arr, int k){
        if (arr == null || arr.length == 0 || k < 0){
            return 0;
        }
        int sum = arr[0];
        int left = 0;
        int right = 0;
        int len = 0;
        while (right < arr.length){
            if (sum == k){
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum > k){
                sum-= arr[left++];
            } else {
                right++;
                if (right == arr.length){
                    break;
                }
                sum+= arr[right];
            }
        }
        return len;
    }


    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
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
        int len = 20;
        int k = 22;
        int[] arr = generatePositiveArray(len);
        printArray(arr);
        System.out.println(getMaxLength(arr, k));

    }
}
