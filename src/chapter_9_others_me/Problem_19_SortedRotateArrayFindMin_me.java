package chapter_9_others_me;

/**
 * Created by bigming on 16/11/17.
 * 题目: 在有序旋转数组中找到最小值
 *      有序数组arr可能经过一次旋转处理,也可能没有,且arr可能存在重复的数.
 *      例如, 有序数组[1,2,3,4,5,6,7],可以旋转成[3,4,5,6,7,1,2]等.给
 *      定一个可能旋转过的有序数组arr,返回arr种的最小值.
 * 难度: **
 * 思路: 关键这里是存在重复的数,如何处理重复的数是关键.
 *
 */
public class Problem_19_SortedRotateArrayFindMin_me {
    public static int getMin(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low < high) {
            if (low == high - 1){
                break;
            }
            if (arr[low] < arr[high]) {
                return arr[low];
            }
            mid = (low + high) / 2;
            if (arr[low] > arr[mid]) {
                high = mid;
                continue;
            }
            if (arr[mid] > arr[high]) {
                low = mid;
                continue;
            }
            while (low < mid) {
                if (arr[low] == arr[mid]) {
                    low++;
                } else if (arr[low] < arr[mid]) {
                    return arr[low];
                } else {
                    high = mid;
                    break;
                }
            }
        }
        return Math.min(arr[low], arr[high]);
    }

    public static void main(String[] args) {
        int[] test = { 4, 5, 5, 5, 5, 5, 5, 1, 2, 3 };
        System.out.println(getMin(test));

    }
}
