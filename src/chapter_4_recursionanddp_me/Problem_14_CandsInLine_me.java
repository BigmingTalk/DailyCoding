package chapter_4_recursionanddp_me;

/**
 * Created by bigming on 16/9/26.
 * 题目: 给定一个整型数组arr, 代表数值不同的纸牌排成一条龙.玩家A和玩家B
 *      依次拿走每张纸牌,规定玩家A先拿,玩家B后拿,但是每个玩家每次只能拿走
 *      最左或最右的纸牌,玩家A和玩家B都绝顶聪明.请返回最后获胜者的分数.
 * 难度: **
 * 思路: 先是暴力递归,f(i,j)表示如果arr[i..j]这个排列上的纸牌被绝顶聪明
 *      的人先拿,最终能获得什么分数.s(i,j)表示如果arr[i..j]这个排列上的
 *      纸牌如果被绝顶聪明的人后拿,最终能获得什么分数.
 */
public class Problem_14_CandsInLine_me {
    public static int win1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int i, int j){
        if (i == j){
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j -1));
    }

    public static int s(int[] arr, int i, int j){
        if (i == j){
            return 0;
        }
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }

    public static int win2(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j =0; j < arr.length; j++){
            f[j][j] = arr[j];
            for (int i =j-1; i >=0; i--){
                f[i][j] = Math.max(arr[i] + s[i+1][j], arr[j] + s[i][j-1]);
                s[i][j] = Math.min(f[i+1][j], f[i][j-1]);
            }
        }
        return Math.max(f[0][arr.length-1], s[0][arr.length-1]);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 9, 1 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));

    }

}
