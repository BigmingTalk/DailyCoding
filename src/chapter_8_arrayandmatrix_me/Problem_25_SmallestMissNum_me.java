package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/30.
 * 题目: 数组中未出现的最小正整数
 * 难度: **
 * 思路: 如果arr长度为N,最优解可以做到时间复杂度为O(N),额外空间复杂度为O(1)
 *      用变量l表示遍历到目前为止,数组arr已经包含的正整数范围是[1,l],变量r
 *      表示遍历到目前为止,在后续出现最优状况的情况下,arr可能包含的正整数范围
 *      是[1,r].
 */
public class Problem_25_SmallestMissNum_me {

    public static void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static int missNum(int[] arr){
        int l = 0;
        int r = arr.length;
        while (l < r){
            if (arr[l] == l + 1){
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[l] == arr[arr[l] - 1]){
                arr[l] = arr[--r];
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    public static void main(String[] args) {
        int[] arr = { -1, 0, 2, 1, 3, 5 };
        System.out.println(missNum(arr));

    }
}
