package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/28.
 * 题目: 给定一个整型数组arr, 返回不包含本位置值的累乘数组
 * 例如, arr=[2,3,1,4],返回[12,8,24,6],即除自己外,其他位置上的累乘
 * 要求: 时间复杂度为O(N),额外空间复杂度为O(1).
 * 进阶题目: 对时间和空间复杂度的要求不变,而且不可以使用除法!
 * 难度: *
 * 思路: 原题是要总的累乘除以自己位置的数即可,需要注意0的数量
 *      进阶题目可以采用lr[]和rl[]分别表示从左到右的累乘和从右到左的
 *      累乘.但是为了使额外空间复杂度为O(1),则可以通过res数组复用的
 *      方式来实现
 */
public class Problem_22_MultiplyExceptOwn_me {
    public static int[] product1(int[] arr){
        if (arr == null || arr.length < 2){
            return null;
        }
        int count = 0;
        int all = 1;
        for (int i = 0; i != arr.length; i++){
            if (arr[i] == 0){
                count++;
            } else {
                all *= arr[i];
            }
        }
        int[] res = new int[arr.length];
        if (count == 0){
            for (int i = 0; i != arr.length; i++){
                res[i] = all / arr[i];
            }
        }
        if (count == 1){
            for (int i = 0; i != arr.length; i++){
                if (arr[i] == 0){
                    res[i] = all;
                }
            }
        }
        return res;
    }

    public static int[] product2(int[] arr){
        if (arr == null || arr.length < 2){
            return null;
        }
        int[] res = new int[arr.length];
        res[0] = arr[0];
        for (int i = 1; i < arr.length; i++){
            res[i] = res[i - 1] * arr[i];
        }
        int tmp = 1;
        for (int i = arr.length - 1; i > 0; i--){
            res[i] = res[i - 1] * tmp;
            tmp *= arr[i];
        }
        res[0] = tmp;
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4 };
        int[] res1 = product1(arr);
        printArray(res1);
        int[] res2 = product2(arr);
        printArray(res2);

    }
}
