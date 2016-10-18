package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/18.
 * 题目: 不重复打印排序数组中相加和为给定值k的所有不降序二元组和三元组
 *      例如,arr=[-8,-4,-3,0,1,2,4,5,8,9], k=10, 打印结果为:
 *      1, 9
 *      2, 8
 * 难度: **
 * 补充题目: 给定排序数组arr和整数k,不重复打印arr中所有相加和为k的不降序
 *          三元组.
 * 思路: 二元组采用两遍向中间逼近的方式即可
 *      三元组则在在二元组的基础上先固定一个值即可.
 * P.S: 原书中对三元组的不重复判定有一定问题,即固定第一个元素后,要保证left!=第一个元素
 *      这里给出的第二个数组再原书中就会输出2,2,7,我这里已经修改回来.
 *
 */
public class Problem_09_PrintUniquePairAndTriad_me {

    // 二元组
    public static void printUniquePair(int[] arr, int k){
        if (arr == null || arr.length < 2){
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            if (arr[left] + arr[right] < k){
                left++;
            } else if (arr[left] + arr[right] > k){
                right--;
            } else {
                if (left == 0 || arr[left - 1] != arr[left]){
                    System.out.println(arr[left] + "," + arr[right]);
                }
                left++;
                right--;
            }
        }
    }

    // 三元组
    public static void printUniqueTriad(int[] arr, int k){
        if (arr == null || arr.length < 3){
            return;
        }
        for (int i = 0; i < arr.length - 2; i++){
            if (i == 0 || arr[i] != arr[i - 1]){
                printRest(arr, i, i + 1, arr.length - 1, k - arr[i]);
            }
        }
    }

    public static void printRest(int[] arr, int f, int l, int r, int k){
        while (l < r){
            if (arr[l] + arr[r] < k){
                l++;
            } else if (arr[l] + arr[r] > k){
                r--;
            } else {
                // if(l == f + 1 || arr[l -1] != arr[l]) 原书中的判断,没有
                // 确保排除arr[f]与arr[l]相等的情况.
                if (arr[l] != arr[f] && arr[l - 1] != arr[l]){
                    System.out.println(arr[f] + "," + arr[l] + "," + arr[r]);
                }
                l++;
                r--;
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
        int sum = 10;
        int[] arr1 = { -8, -4, -3, 0, 1, 2, 4, 5, 8, 9 };
        printArray(arr1);
        System.out.println("====");
        printUniquePair(arr1, sum);
        System.out.println("====");
        printUniqueTriad(arr1, sum);

        int sum2 = 11;
        int[] arr2 = { -1, 1, 2, 2, 4, 7 };
        printArray(arr2);
        System.out.println("====");
        printUniquePair(arr2, sum2);
        System.out.println("====");
        printUniqueTriad(arr2, sum2);

    }
}
