package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/21.
 * 题目: 给定一个长度为N的整型数组arr, 其中有N个互不相等的自然数1~N, 请
 *      实现arr的排序,但是不要把下标0~N-1位置上的数通过直接赋值的粉丝替换
 *      为1~N.
 * 难度: *
 */
public class Problem_14_SortNaturalNumberArray_me {
    public static void sort1(int[] arr){
        int tmp = 0;
        int next = 0;
        for (int i = 0; i != arr.length; i++){
            tmp = arr[i];
            while (arr[i] != i + 1){
                next = arr[tmp - 1];
                arr[tmp - 1] = tmp;
                tmp = next;
            }
        }
    }

    public static void sort2(int[] arr){
        int tmp = 0;
        for (int i = 0; i != arr.length; i++){
            while (arr[i] != i + 1){
                tmp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = tmp;
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
        int[] arr = { 8, 2, 1, 6, 9, 3, 7, 5, 4 };
        sort1(arr);
        printArray(arr);
        arr = new int[] { 8, 2, 1, 6, 9, 3, 7, 5, 4 };
        sort2(arr);
        printArray(arr);

    }
}
