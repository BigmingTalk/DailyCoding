package chapter_9_others_me;

/**
 * Created by bigming on 16/11/4.
 * 题目: 最大的leftMax和rightMax之差的绝对值
 *      给定一个长度为N(N > 1)的整型数组arr, 可以划分成左右两个部分, 左部分
 *      为arr[0..K],右部分为arr[K+1..N-1],K可以取值的范围是[0,N-2].求这么
 *      多划分方案中,左部分中的最大值减去右部分最大值的绝对值中,最大是多少?
 * 难度: ***
 * 思路: 解法一: 对每个位置都进行一次划分,求每种划分之差,去最大值.时间复杂度为
 *             O(N^2),额外空间复杂度O(1).
 *      解法二: 用leftArr和rightArr分别记录, leftArr[i]代表arr[0..i]中的
 *             最大值, rightArr[i]代表arr[i..N-1]中的最大值.再求差的最大值.
 *             时间复杂度为O(N),额外空间复杂度为O(N).
 *      解法三: 找到整个数组的最大值,该最大值要么在左半部分,有么在右半部分, 然后
 *             如果在左半部分,则应该让右半部分的最大值最小,那右半部分就直接应该
 *             是arr[N-1].同理如果最大值在右半部分,则左半部分应该取arr[0],所以
 *             两种情况比较一下即可.
 */
public class Problem_09_MaxABSBetweenLeftAndRight_me {
    public static int maxABS1(int[] arr){
        int res = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i != arr.length - 1; i++){
            maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j != i + 1; j++){
                maxLeft = Math.max(arr[j], maxLeft);
            }
            maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j != arr.length; j++){
                maxRight = Math.max(arr[j], maxRight);
            }
            res = Math.max(Math.abs(maxLeft - maxRight), res);
        }
        return res;
    }

    public static int maxABS2(int[] arr){
        int[] lArr = new int[arr.length];
        int[] rArr = new int[arr.length];
        lArr[0] = arr[0];
        rArr[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length; i++){
            lArr[i] = Math.max(lArr[i - 1], arr[i]);
        }
        for (int i = arr.length - 2; i > -1; i--){
            rArr[i] = Math.max(rArr[i + 1], arr[i]);
        }
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++){
            max = Math.max(max, Math.abs(lArr[i] - rArr[i+1]));
        }
        return max;
    }

    public static int maxABS3(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++){
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(maxABS1(arr));
        System.out.println(maxABS2(arr));
        System.out.println(maxABS3(arr));
    }
}
