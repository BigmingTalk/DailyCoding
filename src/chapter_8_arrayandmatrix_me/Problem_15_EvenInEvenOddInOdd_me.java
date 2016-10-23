package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/23.
 * 题目: 给定一个长度不小于2的数组arr, 实现一个函数调整arr,要么
 *      让所有的偶数下标都是偶数,要么让所有的奇数下标都是奇数.
 * 难度: *
 * 思路: 一直都是和最后位置的数交换,如果最后位置的数是偶数,就向偶数
 *      下标发送,如果最后位置是奇数,就向奇数下标发送.直到偶数下标或
 *      者奇数下标都已经无法再向右移动,说明调整结束.
 */
public class Problem_15_EvenInEvenOddInOdd_me {
    public static void modify(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int even = 0;
        int odd = 1;
        int end = arr.length - 1;
        while (even <= end && odd <= end){
            if ((arr[end] & 1) == 0){
                swap(arr, end, even);
                even += 2;
            } else {
                swap(arr, end, odd);
                odd += 2;
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 1, 8, 3, 2, 4, 6 };
        printArray(arr);
        modify(arr);
        printArray(arr);

    }
}
