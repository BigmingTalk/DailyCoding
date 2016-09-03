package chapter_8_arrayandmatrix_me;

import java.util.HashMap;

/**
 * Created by bigming on 16/9/3.
 */
public class Problem_11_LongestSumSubArrayLength_me {
    public static int maxLength(int[] arr, int k){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++){
            sum  = sum + arr[i];
            if (map.containsKey(sum - k)){
                if (i - map.get(sum-k) > len){
                    len = i - map.get(sum - k);
                }
            }
            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }
        return len;
    }

    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = generateArray(20);
        printArray(arr);
        System.out.println(maxLength(arr, 10));

    }

}
