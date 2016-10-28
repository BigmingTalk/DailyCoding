package chapter_8_arrayandmatrix_me;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bigming on 16/10/28.
 * 题目: 用一个整型数组matrix表示一个网络,1代表有路,0代表无路,每一个位置
 *      只要不越界,都有上下左右4个方向,求从最左上角到最右下角的最短通路值
 * 难度: **
 * 思路: 宽度优先遍历
 */
public class Problem_24_MinPathValue_me {
    public static int minPathValue(int[][] m){
        if (m == null || m.length == 0 || m[0].length == 0 ||
                m[0][0] != 1 || m[m.length - 1][m[0].length - 1] != 1){
            return 0;
        }
        int res = 0;
        int[][] map = new int[m.length][m[0].length];
        map[0][0] = 1;
        Queue<Integer> rQ = new LinkedList<Integer>();
        Queue<Integer> cQ = new LinkedList<Integer>();
        rQ.add(0);
        cQ.add(0);
        int r = 0;
        int c = 0;
        while (!rQ.isEmpty()){
            r = rQ.poll();
            c = cQ.poll();
            if (r == m.length - 1 && c == m[0].length - 1){
                return map[r][c];
            }
            walkTo(map[r][c], r - 1, c, m, map, rQ, cQ);
            walkTo(map[r][c], r + 1, c, m, map, rQ, cQ);
            walkTo(map[r][c], r, c + 1, m, map, rQ, cQ);
            walkTo(map[r][c], r, c - 1, m, map, rQ, cQ);
        }
        return res;
    }

    public static void walkTo(int pre, int toR, int toC, int[][] m,
                              int[][] map, Queue<Integer> rQ,
                              Queue<Integer> cQ){
        if (toR < 0 || toR == m.length || toC < 0 || toC == m[0].length
                || m[toR][toC] != 1 || map[toR][toC] != 0){
            return;
        }
        map[toR][toC] = pre + 1;
        rQ.add(toR);
        cQ.add(toC);
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 0, 1, 1, 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
                { 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1 } };
        System.out.println(minPathValue(matrix));

    }
}
