package chapter_8_arrayandmatrix_me;

/**
 * Created by bigming on 16/10/25.
 * 题目: 数组中子数组的最大累乘积. 给定一个double类型的数组arr, 其中的元素可正,可负,可0,
 *      返回子数组累乘的最大乘积,例如,arr=[-2.5, 4, 0, 3, 0.5, 8, -1], 子数组[3, 0.5, 8]
 *      累乘可以获得最大的乘积,所以返回12.
 * 难度: **
 * 思路: 与求子数组的最大累加不同的一点是,累乘可能是有负负得正的情况,所以不能只保存之前最大的那个
 *      数就行了. 解决方法非常巧妙,就是再保存之前最小的数,因为最小的数可能是负数,所以会有可能得到
 *      负负得正,转化为最大的数.
 */
public class Problem_19_SubArrayMaxProduct_me {
    public static double maxProduct(double[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        double max = arr[0];
        double min = arr[0];
        double res = arr[0];
        double maxEnd;
        double minEnd;
        for (int i = 1; i < arr.length; i++){
            maxEnd = max * arr[i];
            minEnd = min * arr[i];
            max = Math.max(Math.max(maxEnd, minEnd), arr[i]);
            min = Math.min(Math.min(maxEnd, minEnd), arr[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    public static void main(String[] args) {
        double[] arr = { -2.5, 4, 0, 3, 0.5, 8, -1 };
        System.out.println(maxProduct(arr));
    }

}
