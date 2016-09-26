package chapter_4_recursionanddp_me;

/**
 * Created by bigming on 16/9/26.
 * 题目: 给定数组arr, arr[i]=k代表可以从位置i向右跳1~k个距离.如果
 *      从位置0出发,返回最少跳几次能跳到arr最后的位置上.
 * 难度: *
 * 思路: 用jump表示目前跳了不少步,cur表示如果只能跳jump步,最远能够达到
 *      的位置,next代表如果再多跳一步,最远能够达到的位置.
 */
public class Problem_15_JumpGame_me {
    public static int jump(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int jump = 0;
        int cur =0;
        int next =0;
        for (int i =0; i < arr.length; i++){
            if (cur < i){
                jump++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return jump;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 3, 1, 1, 4 };
        System.out.println(jump(arr));

    }

}
