package chapter_1_stackandqueue_me;

import java.util.LinkedList;

/**
 * Created by bigming on 16/8/17.
 *
 * 题目: 输入: 整型数组arr,窗口大小为w
 *      输出: 一个长度为n-w+1的数组res,res[i]表示每一种窗口状态下的最大值
 * 难度: **
 * 思路: 用一个双端队列qmax来存放数组arr的下标.主要就是当放入新的数a大于前面的数时,前面的数就没有
 *      用了,因为现在最大的是新放进来的a,如果小于前面的数,则要放进去,因为可能到时候前面的数都失效
 *      了,但是a还没失效,所以a还需要放进来.另外一个是队列头结点的弹出,因为窗口已经划过去了,所以可
 *      能已经失效了,需要弹出去.
 *
 */
public class Problem_07_SlidingWindowMaxArray_me {
    public static int[] getMaxWindow(int[] arr, int w){
        if(arr ==null || w<1 || arr.length<w){
            return null;
        }

        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length-w+1];

        int index =0;

        for(int i =0; i<arr.length; i++){

            //如果前面的结点都小于现在的结点,则要将前面的结点弹出,知道大于现在的结点为止
            //否则,直接加入到队列中,因为这是最新的,可能到时候前面都因为窗口过期了,它还没
            //有过期.
            while(!qmax.isEmpty() && arr[qmax.peek()]<arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);

            //如果队列头的结点已经失效了的话,则把头节点弹出
            //if (qmax.peekFirst() <i-w+1) 这个条件也是可以的
            if (qmax.peekFirst() == i-w){
                qmax.pollFirst();
            }

            //当移动了w-1时,已经到了第一个窗口的结果,按照我们的规则就是最头上的那个结点
            if(i>=w-1){
                res[index++] = arr[qmax.peekFirst()];
            }
        }

        return res;
    }


    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
        int w = 3;
        printArray(getMaxWindow(arr, w));

    }

}
