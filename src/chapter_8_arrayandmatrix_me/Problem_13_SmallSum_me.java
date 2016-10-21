package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/21.
 * 题目: 计算数组的小和.如数组s = [1,3,5,2,4,6],则分别计算数组每个位置
 *      左侧比它小的数的和,再累加起来,这里的小和为(0) + (1) + (1 + 3) +
 *      (1) + (1 + 3 + 2) + (1 + 3 + 5 + 2 + 4) = 27
 * 难度: ***
 * 思路: 第一种就是O(N^2)的时间复杂度,直接把每个位置的小和求出来累加
 *      第二种是时间复杂度为O(NLogN),空间复杂度为O(N).用类似归并排序的
 *      方法,在归并的过程中计算小和.
 */
public class Problem_13_SmallSum_me {
    public static int getSmallSum(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        return func(arr, 0, arr.length - 1);
    }

    public static int func(int[] s, int l, int r){
        if (l == r){
            return 0;
        }
        int mid = (l + r) / 2;
        return func(s, l, mid) + func(s, mid + 1, r) + merge(s, l, mid, r);
    }

    public static int merge(int[] s, int left, int mid, int right){
        int[] h = new int[right - left + 1];
        int hi = 0;
        int i = left;
        int j = mid + 1;
        int smallSum = 0;
        while (i <= mid && j <= right){
            if (s[i] <= s[j]){
                smallSum += s[i] * (right - j + 1);
                h[hi++] = s[i++];
            } else {
                h[hi++] = s[j++];
            }
        }
        for (; (j < right + 1) || (i < mid + 1); j++, i++){
            h[hi++] = i > mid ? s[j] : s[i];
        }
        for (int k = 0; k != h.length; k++){
            s[left++] = h[k];
        }
        return smallSum;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 2, 4, 6 };
        System.out.println(getSmallSum(arr));

    }

}
