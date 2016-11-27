package chapter_9_others_me;

/**
 * Created by bigming on 16/11/27.
 * 题目: 画匠问题, 给定一个整型数组arr, 数组中的每个数都为正数, 表示完成一幅画作
 *      需要的时间, 再给定一个整数num表示画匠的数量, 每个画匠只能画连在一起的画作.
 *      所有的画家并行工作, 请返回完成所有的画作需要的最少时间.
 * 难度: ***
 * 思路: 动态规划! 用dp[i][j]表示i个画家搞定arr[0..j]这些画所需的最少时间. 那么有
 *      dp[i][j] = min{ max{dp[i-1][k], sum[k+1..j] } (0<=k<j) }
 *
 */
public class Problem_33_ArtisProblem_me {

    // 空间优化, 时间复杂度为O(N^2 * num), N为画作数量, num为画家数量
    public static int solution1(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1) {
            throw new RuntimeException("input is error");
        }
        int[] sumArr = new int[arr.length];
        int[] map = new int[arr.length];
        sumArr[0] = arr[0];
        map[0] = arr[0];
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
            map[i] = sumArr[i];
        }
        for (int i = 1; i < num; i++) {
            for (int j = map.length - 1; j > i - 1; j--) {
                int min = Integer.MAX_VALUE;
                for (int k = i - 1; k < j; k++) {
                    int cur = Math.max(map[k], sumArr[j] - sumArr[k]);
                    min = Math.min(min, cur);
                }
                map[j] = min;
            }
        }
        return map[arr.length - 1];
    }

    // 利用四边形不等式对枚举过程进行优化, 将时间复杂度降至O(N^2).
    public static int solution2(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1) {
            throw new RuntimeException("input is error");
        }
        int[] sumArr = new int[arr.length];
        int[] map =  new int[arr.length];
        sumArr[0] = arr[0];
        map[0] = arr[0];
        for (int i = 1; i < sumArr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
            map[i] = sumArr[i];
        }
        int[] cands = new int[arr.length];
        for (int i = 1; i < num; i++) {
            for (int j = map.length - 1; j > i - 1; j--) {
                int minPtr = cands[j];
                int maxPtr = j == map.length - 1 ? j : cands[j + 1];
                int min = Integer.MAX_VALUE;
                for (int k = minPtr; k < maxPtr + 1; k++) {
                    int cur = Math.max(map[k], sumArr[j] - sumArr[k]);
                    if (cur <= min) {
                        min = cur;
                        cands[j] = k;
                    }
                }
                map[j] = min;
            }
        }
        return map[arr.length - 1];
    }

    // 换一个思路, 假如规定每个画匠画画的时间不能多于limit, 那么需要几个画匠才够呢?
    // 然后limit的范围从[0, arr所有值的累加和]不断二分.
    public static int getNeedNum(int[] arr, int lim) {
        int res = 1;
        int stepNum = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] > lim) {
                return Integer.MAX_VALUE;
            }
            stepNum += arr[i];
            if (stepNum > lim) {
                res++;
                stepNum = arr[i];
            }
        }
        return res;
    }

    public static int solution3(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 1) {
            throw new RuntimeException("input is error");
        }
        if (arr.length < num) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i != arr.length; i++){
                max = Math.max(max, arr[i]);
            }
            return max;
        } else {
            int minSum = 0;
            int maxSum = 0;
            for (int i = 0; i < arr.length; i++) {
                maxSum += arr[i];
            }
            while (minSum != maxSum - 1) {
                int mid = (minSum + maxSum) / 2;
                if (getNeedNum(arr, mid) > num) {
                    minSum = mid;
                } else {
                    maxSum = mid;
                }
            }
            return maxSum;
        }
    }

    public static int[] generateRandomArray(int length) {
        int[] result = new int[length];
        for (int i = 0; i != result.length; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
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
        int[] arr = generateRandomArray(300);
        int painterNum = 2;
        System.out.println(solution1(arr, painterNum));
        System.out.println(solution2(arr, painterNum));
        System.out.println(solution3(arr, painterNum));

        arr = generateRandomArray(5000000);
        painterNum = 20000;
        long start = System.currentTimeMillis();
        System.out.println(solution3(arr, painterNum));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");

    }


}
