package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/9/30.
 * 题目: 判断字符数组中是否所有字符都只出现过一次,分别实现如下两种要求
 * 要求: 1. 时间复杂度为O(N)
 *      2. 保证额外空间复杂度为O(1)的前提下,时间复杂度尽量低
 * 思路: 1. 用map记录即可
 *      2. 分析: 排序后比较即可,转换为排序算法的要求
 *          * 复杂度为O(N)的排序算法,如通排序,基数排序,计数排序等,它们
 *          都不可能,因为其都不是基于比较的排序算法,不可能额外空间复杂度O(1)
 *          * 复杂度为O(NlogN)的排序算法,如归并排序,快速排序,希尔排序和桶排序,
 *          归并排序需要辅助数组,从小组合并到大组,排除,尽管归并排序可以使用手摇
 *          算法将额外空间复杂度降至O(1),但这样最差的时间复杂度会上升到O(N^2).
 *          快速排序也被排序,不管是递归实现还是非递归实现,快速排序额外空间复杂度
 *          最低位O(logN),希尔排序是啊金复杂度并不固定,其取决于步长的选择,选择
 *          不当时间复杂度为O(N^2),堆排序可以做到额外空间复杂度为O(1),时间复杂
 *          度还在O(NlogN),只要是非递归实现即可,因为堆排序递归实现时额外空间复
 *          杂度为O(logN).
 *
 */
public class Problem_08_IsAllUnique_me {
    public static boolean isUnique1(char[] chas){
        if (chas == null){
            return true;
        }
        boolean[] map = new boolean[256];
        for (int i = 0; i < chas.length; i++){
            if (map[chas[i]]){
                return false;
            }
            map[chas[i]] = true;
        }
        return true;
    }

    public static boolean isUnique2(char[] chas){
        if (chas == null){
            return true;
        }
        heapSort(chas);

        /*System.out.println("heap sort result:");
        for (int i = 0; i < chas.length; i++){
            System.out.print(chas[i] + " ");
        }
        System.out.println();*/

        for (int i = 1; i < chas.length; i++){
            if (chas[i] == chas[i-1]){
                return false;
            }
        }
        return true;
    }

    public static void heapSort(char[] chas){
        for (int i = 0; i < chas.length; i++){
            heapInsert(chas, i);
        }
        for (int i = chas.length - 1; i > 0; i--){
            swap(chas, 0, i);
            heapify(chas, 0, i);
        }
    }

    public static void heapInsert(char[] chas, int i){
        int parent = 0;
        while (i != 0){
            parent = (i - 1) / 2;
            if (chas[parent] < chas[i]){
                swap(chas, parent, i);
                i = parent;
            } else {
                break;
            }
        }
    }

    public static void heapify(char[] chas, int i, int size){
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        while (left < size){
            if (chas[left] > chas[i]){
                largest = left;
            }
            if (right < size && chas[right] > chas[largest]){
                largest = right;
            }
            if (largest != i){
                swap(chas, largest, i);
            } else {
                break;
            }
            i = largest;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }

    public static void swap(char[] chas, int index1, int index2){
        char tmp = chas[index1];
        chas[index1] = chas[index2];
        chas[index2] = tmp;
    }

    public static void main(String[] args) {
        char[] chas = { '1', '2', '3', 'a', 'b', 'c', 'd', '4', '5', '6' };
        System.out.println(isUnique1(chas));
        System.out.println(isUnique2(chas));

    }

}
