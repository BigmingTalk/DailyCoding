package chapter_1_stackandqueue_me;

import java.util.Stack;

/**
 * Created by bigming on 16/8/18.
 *
 * 题目: 一个整形矩阵map,其中的值只有0和1两种,求其中全是1的所有矩形区域中,
 *      最大的矩形区域为1的数量
 * 难度: ***
 * 思路: 按行切割, 用height[]数组来表示从该行往上连续多少个1,然后就转换为
 *      有了height数组后,求该height数组内的最大值,就像一个直方图,求最大的
 *      矩形面积.
 *
 */
public class Problem_09_MaximalRectangle_me {
    public static int maxRecFromBottom(int[] height){
        if (height==null||height.length==0){
            return 0;
        }
        int maxArea =0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i< height.length; i ++){
            while (!stack.isEmpty() && height[i]<= height[stack.peek()]){
                int j = stack.pop();
                int k = stack.isEmpty()? -1:stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(curArea, maxArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length -k -1) * height[j];
            maxArea = Math.max(curArea, maxArea);
        }

        return  maxArea;
    }

    public static int maxRecSize(int[][] map){
        if (map == null || map.length ==0 || map[0].length==0){
            return 0;
        }

        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i =0; i<map.length; i++){
            for (int j = 0; j< map[0].length; j++){
                height[j] = map[i][j]== 0 ? 0 : height[j] + 1;
            }
            maxArea =Math.max(maxRecFromBottom(height), maxArea);
        }
        return  maxArea;
    }

    public static void main(String[] args) {
        int[][] map = { { 1, 0, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 0 }, };
        System.out.println(maxRecSize(map));
    }

}
