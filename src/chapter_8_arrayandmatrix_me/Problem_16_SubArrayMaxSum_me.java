package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/24.
 * 题目: 子数组的最大累加和
 * 难度: *
 * 思路: 用cur记录每一步的累加和,若cur<0,说明累加到当前数出现了
 *      小于0的结果,那么累加的这一部分一定不会作为产生最大累加和
 *      的子数组的左边部分.
 */
public class Problem_16_SubArrayMaxSum_me {
    public static int maxSum(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++){
            cur += arr[i];
            max = Math.max(cur, max);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = { -2, -3, -5, 40, -10, -10, 100, 1 };
        System.out.println(maxSum(arr1));

        int[] arr2 = { -2, -3, -5, 0, 1, 2, -1 };
        System.out.println(maxSum(arr2));

        int[] arr3 = { -2, -3, -5, -1 };
        System.out.println(maxSum(arr3));

    }
}
