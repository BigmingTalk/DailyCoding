package chapter_1_stackandqueue_me;

import java.util.LinkedList;

/**
 * Created by bigming on 16/8/18.
 *
 * 题目: 给定数组arr和整数num,共返回多少个子数组满足如下情况:
 *      1. max(arr[i..j]) -min(arr[i..j]) <=num
 *      2. max(arr[i..j])表示子数组arr[i..j]中的最大值,
 *         min(arr[i..j])表示子数组arr[i..j]中的最小值
 * 难度: ***
 * 思路: 普通法就是穷举,复杂度为O(N^3),不再说明
 *      另一种方法是与窗口最大值数组问题一样的,用qmax和qmin两个双端队列来维护窗口子数组的
 *      最大值和最小值,每次更新窗口的时间为O(1),所以总的时间复杂度为0(N)
 */
public class Problem_10_AllLessNumSubArray_me {
    public static int getNum(int[] array, int num){
        if (array == null || array.length==0){
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int i = 0;
        int j =0;
        int res =0;
        while(i< array.length){
            while (j < array.length){
                while (!qmin.isEmpty() && array[qmin.peekLast()] >= array[j]){
                    qmin.pollLast();
                }
                qmin.addLast(j);

                while (!qmax.isEmpty() && array[qmax.peekLast()] <= array[j]){
                    qmax.pollLast();
                }
                qmax.addLast(j);

                if (array[qmax.peekFirst()]-array[qmin.peekFirst()] >num){
                    break;
                }
                j++;
            }
            if (qmax.peekFirst()==i){
                qmax.pollFirst();
            }
            if (qmin.peekFirst()==i){
                qmin.pollFirst();
            }

            res += j-i;
            i++;
        }
        return res;
    }


    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(3);
        int num = 5;
        printArray(arr);
        System.out.println(getNum(arr, num));

    }
}
