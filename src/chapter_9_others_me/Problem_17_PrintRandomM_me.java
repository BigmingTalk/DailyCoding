package chapter_9_others_me;

/**
 * Created by bigming on 16/11/7.
 * 题目: 给定一个程度为N且没有重复元素的数组arr和一个整数M,实现函数
 *      等概率随机打印arr中的M个数
 * 要求: 1. 相同的数不要重复打印.
 *      2. 时间复杂度为O(M),额外空间复杂度为O(1)
 *      3. 可以改变arr数组.
 * 难度: *
 * 思路: 通过把已经打印的数放到末尾来实现. 很多有关等概率随机的面试题都是用
 *      这种和最后一个位置交换的解法.
 */
public class Problem_17_PrintRandomM_me {
    public static void printRandM(int[] arr, int m){
        if (arr == null || arr.length == 0 || m < 0){
            return;
        }
        m = Math.min(arr.length, m);
        int count = 0;
        int i = 0;
        while (count < m) {
            i = (int) (Math.random() * (arr.length - count));
            System.out.println(arr[i]);
            swap(arr, arr.length - count++ - 1, i);
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = { 6, 2, 3, 5, 1, 4, 7, 8, 10, 23};
        int m = 5;
        printRandM(arr, m);

    }
}
