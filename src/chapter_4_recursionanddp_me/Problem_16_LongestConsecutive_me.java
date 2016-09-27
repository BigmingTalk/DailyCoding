package chapter_4_recursionanddp_me;

import java.util.HashMap;

/**
 * Created by bigming on 16/9/27.
 * 题目: 数组中最长连续序列,如arr=[100,1,200,3,2,4],则最长连续序列为
 *      [1,2,3,4],返回4
 * 难度: **
 * 思路: 用哈希表map,key表示遍历过的某个数,value代表key这个数所在的最长
 *      连续序列的长度. 遍历arr,在每个连续序列中只有最小值和最大值在map中
 *      的记录有意义,中间数的记录不再更新.因为如果一个没出现的能够把某个连续
 *      区间扩大,或者把某两个连续区间连在一起,只需要有关这个连续区间最小值和
 *      最大值的记录.
 *
 */
public class Problem_16_LongestConsecutive_me {
    public static int longestConsecutive(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int max = 1;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i =0; i < arr.length; i++){
            if (!map.containsKey(arr[i])){
                map.put(arr[i], 1);
                if (map.containsKey(arr[i]-1)){
                    max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
                }
                if (map.containsKey(arr[i]+1)){
                    max = Math.max(max, merge(map, arr[i], arr[i] + 1));
                }
            }
        }
        return max;
    }

    public static int merge(HashMap<Integer, Integer> map, int less, int more){
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        map.put(left, len);
        map.put(right, len);
        return len;
    }

    public static void main(String[] args) {
        int[] arr = { 100, 4, 200, 1, 3, 2 };
        System.out.println(longestConsecutive(arr));
    }
}
