package chapter_3_binarytreeproblem_me;

import java.util.HashMap;

/**
 * Created by bigming on 16/9/14.
 */
public class Problem_22_PreAndInArrayToPosArray_me {
    public static int[] getPosArray(int[] pre, int[] in){
        if (pre == null || in == null){
            return null;
        }
        int len = pre.length;
        int[] pos = new int[len];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++){
            map.put(in[i], i);
        }
        setPos(pre, 0, len -1, in, 0, len -1, pos, len -1, map);
        return pos;
    }

    // 从左往右一次填好后序数组s
    // si为后序数组s该填的位置
    // 返回值为s该填的下一个位置
    public static int setPos(int[] p, int pi, int pj, int[] n, int ni,
                             int nj, int[] s, int si, HashMap<Integer, Integer> map){
        if (pi > pj){
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj -nj + i + 1, pj, n, i+1, nj, s, si, map);
        return setPos(p, pi +1, pi + i -ni, n, ni, i -1, s, si, map);
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
        int[] in = { 4, 2, 5, 1, 6, 3, 7 };
        int[] pos = getPosArray(pre, in);
        printArray(pos);

    }
}
