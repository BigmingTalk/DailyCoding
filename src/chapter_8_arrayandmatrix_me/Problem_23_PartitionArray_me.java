package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/28.
 * 题目: 给定一个有序数组arr,调整arr使得这个数组的左半部分没有
 *      重复元素且升序,而不用保证右部分是否有序.
 * 补充题目: 给定一个数组arr,只有0,1,2三个值,请实现arr排序.
 * 要求: 时间复杂度O(N),额外空间复杂度为O(1).
 * 难度: *
 * 思路: 原题用u和i分别表示已换区域的边界和遍历到的位置即可
 *      补充题目: 用left, index和right表示各个边界位置即可.
 *              0~left是左区,left+1~index是中区,right到N-1
 *              是右区.
 */
public class Problem_23_PartitionArray_me {

    public static void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void leftUnique(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int u = 0;
        int i = 1;
        while (i != arr.length){
            if (arr[i++] != arr[u]){
                swap(arr, ++u, i - 1);
            }
        }
    }

    public static void sort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int left = -1;
        int index = 0;
        int right = arr.length;
        while (index < right){
            if (arr[index] == 0){
                swap(arr, ++left, index++);
            } else if (arr[index] == 2){
                swap(arr, index, --right);
            } else {
                index++;
            }
        }
    }


    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7, 7, 8, 8, 8, 9 };
        printArray(arr1);
        leftUnique(arr1);
        printArray(arr1);

        System.out.println();

        int[] arr2 = { 2, 1, 2, 0, 1, 1, 2, 2, 0 };
        printArray(arr2);
        sort(arr2);
        printArray(arr2);

    }
}
