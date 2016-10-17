package chapter_8_arrayandmatrix_me;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by bigming on 16/10/17.
 * 题目: 在数组中找到出现次数大于N/K的数. 给定一个整型数组arr,打印其中出现次数大于一半
 *      的数,如果没有这样的数,打印提示信息
 * 进阶: 给定一个整型数据arr,再给定一个整数K,打印所有出现次数大于N/K的数,如果没有这样的,
 *      数,打印提示信息.
 * 要求: 原问题时间复杂度为O(N),额外空间复杂度为O(1).进阶问题要求时间复杂度为O(N*K),额
 *      外空间复杂度为O(K).
 *
 */
public class Problem_06_FindKMajority_me {
    public static void printHalfMajor(int[] arr){
        int cand = 0;
        int times = 0;
        for (int i = 0; i != arr.length; i++){
            if (times == 0){
                cand = arr[i];
                times = 1;
            } else if (arr[i] == cand){
                times++;
            } else {
                times--;
            }
        }
        times = 0;
        for (int i = 0; i != arr.length; i++){
            if (arr[i] == cand){
                times++;
            }
        }
        if (times > arr.length / 2){
            System.out.println(cand);
        } else {
            System.out.print("no such number");
        }
    }


    public static void printKMajor(int[] arr, int K){
        if (K < 2){
            System.out.println("the value of K is invalid");
            return;
        }
        HashMap<Integer, Integer> cands = new HashMap<Integer, Integer>();
        for (int i = 0; i != arr.length; i++){
            if (cands.containsKey(arr[i])){
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else {
                if (cands.size() == K - 1){
                    allCandsMinusOne(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }
        HashMap<Integer, Integer> reals  = getReals(arr, cands);
        boolean hasPrint = false;
        for (Map.Entry<Integer, Integer> set : cands.entrySet()){
            Integer key = set.getKey();
            if (reals.get(key) > arr.length / K){
                hasPrint = true;
                System.out.print(key + " ");
            }
        }
        System.out.println(hasPrint ? "" : "No such number!");
    }

    public static void allCandsMinusOne(HashMap<Integer, Integer> map){
        List<Integer> removeList = new LinkedList<Integer>();
        for (Map.Entry<Integer, Integer> set : map.entrySet()){
            Integer key = set.getKey();
            Integer value = set.getValue();
            if (value == 1){
                removeList.add(key);
            }
            map.put(key, value - 1);
        }

        for (Integer removeKey : removeList){
            map.remove(removeKey);
        }
    }

    public static HashMap<Integer, Integer> getReals(int[] arr,
                                                     HashMap<Integer, Integer> cands){
        HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
        for (int  i = 0; i != arr.length; i++){
            int curNum = arr[i];
            if (cands.containsKey(curNum)){
                if (reals.containsKey(curNum)){
                    reals.put(curNum, reals.get(curNum) + 1);
                } else {
                    reals.put(curNum, 1);
                }
            }
        }
        return reals;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
        printHalfMajor(arr);
        int K = 4;
        printKMajor(arr, K);
    }

}
