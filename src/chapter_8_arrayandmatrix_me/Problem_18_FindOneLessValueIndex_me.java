package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/25.
 * 题目: 在数组中找到一个局部最小的位置. 给定无序数组arr, 已知arr中
 *      任意两个相邻的数都不相等,写一个函数,只需返回arr中任意一个局部
 *      最小出现的位置即可.
 * 难度: **
 * 思路: 用二分查找的方式来查询
 */
public class Problem_18_FindOneLessValueIndex_me {
    public static int getLessIndex(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]){
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]){
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid;
        while (left < right){
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]){
                right =  mid - 1;
            } else if (arr[mid] > arr[mid + 1]){
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }


    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 6, 5, 3, 4, 6, 7, 8 };
        printArray(arr);
        int index = getLessIndex(arr);
        System.out.println("index: " + index + ", value: " + arr[index]);

    }

}
