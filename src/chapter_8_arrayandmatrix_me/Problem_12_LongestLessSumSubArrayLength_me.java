package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/20.
 * 题目: 未排序数组中累加和小于或等于给定值的最长子数组长度
 * 难度: ***
 * 思路: 依次求以数组的每个位置结尾的,累加和小于或等于k的最长子数组长度,其中
 *      最长的那个子数组的长度就是结果.
 *      用sumArr表示arr每个位置从左到右的累加和,用helpArr表示sumArr的左侧
 *      最大值数组.因为我们只关心大于或等于某一个值的累加和最早出现的位置.
 *      比如: 比如0到30的累加和为100,求以30结尾的,累加和小于或等于10的最长子
 *      数组长度,再假设从位置0到位置10的时候,累加和第一次大于或等于90,那么可以
 *      知道以位置30结尾的相加和小于或等于10的最大子数组就是arr[11..30].
 *
 */
public class Problem_12_LongestLessSumSubArrayLength_me {
    public static int maxLength(int[] arr, int k){
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++){
            sum += arr[i];
            h[i+1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++){
            sum += arr[i];
            pre = getLessIndex(h, sum-k);
            len = pre == -1 ?  0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num){
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high){
            mid = (low + high) / 2;
            if (arr[mid] >= num){
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
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
        int[] arr = { 3, -2, -4, 0, 6 };
        int k = -2;
        System.out.println(maxLength(arr, k));

    }
}
