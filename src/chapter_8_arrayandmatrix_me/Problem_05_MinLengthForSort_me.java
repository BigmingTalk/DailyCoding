package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/16.
 * 题目: 需要排序的最短子数组长度
 * 难度: *
 * 例子: arr = [1, 5, 3, 4, 2, 6, 7]返回4, 因为只有[5, 3, 4, 2]需要排序
 */
public class Problem_05_MinLengthForSort_me {
    public static int getMinLength(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i != -1; i--){
            if (arr[i] > min){
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1){
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i!= arr.length; i++){
            if (arr[i] < max){
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
        System.out.println(getMinLength(arr));
    }

}
