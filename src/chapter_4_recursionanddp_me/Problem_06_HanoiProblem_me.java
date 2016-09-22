package chapter_4_recursionanddp_me;

/**
 * Created by bigming on 16/9/22.
 * 题目: 打印汉诺塔最优移动轨迹.
 * 进阶: 给定数组arr, 其中只含有1,2,3,代表圆盘的状态,1代表左,2代表中,3代表右,
 *      如果arr代表的状态时最优移动轨迹中出现的状态,则返回是第几个状态,否则,返回-1
 * 难度: ***
 * 思路: 进阶问题转化为递归问题,再将递归转为非递归,递归思路清晰.对圆盘1~i来说,考虑
 *      最底下的圆盘i, 如果圆盘i在from上,需要继续考察圆盘1~i,其目标是从from到mid,
 *      如果圆盘i在to上,说明走完了2^(i-1)步,圆盘1~i-1的目标是从mid到to,如果圆盘i
 *      在mid上,直接返回-1,因为这不会出现在最优轨迹上.
 *
 */
public class Problem_06_HanoiProblem_me {
    public static void hanoi(int n){
        if (n > 0){
            func(n, "left", "mid", "right");
        }
    }

    public static void func(int n, String from, String mid, String to){
        if (n == 1){
            System.out.println("move from " + from + " to " + to);
        } else {
            func(n - 1, from, to, mid);
            func(1, from, mid, to);
            func(n - 1, mid, from, to);
        }
    }

    // 递归形式
    public static int step1(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        return process(arr, arr.length - 1, 1, 2, 3);
    }

    public static int process(int[] arr, int i, int from, int mid, int to){
        if (i == -1){
            return 0;
        }
        if (arr[i] != from && arr[i] != to){
            return -1;
        }
        if (arr[i] == from){
            return process(arr, i - 1, from, to, mid);
        } else {
            int rest = process(arr, i - 1, mid, from, to);
            if (rest == -1){
                return -1;
            }
            return (1 << i) + rest;
        }
    }


    // 改为非递归形式, 做到额外空间复杂度为O(1)
    public static int step2(int[] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length -1;
        int res =0;
        int tmp =0;
        while (i >= 0){
            if (arr[i] != from && arr[i] != to){
                return -1;
            }
            if (arr[i] == to){
                res += 1 << i;
                tmp = from;
                from = mid;
            } else {
                tmp = to;
                to = mid;
            }
            mid = tmp;
            i--;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 4;
        hanoi(n);

        int[] arr = { 3, 3, 2, 1 };
        System.out.println(step1(arr));
        System.out.println(step2(arr));

    }
}
