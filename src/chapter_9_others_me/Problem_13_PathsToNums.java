package chapter_9_others_me;

/**
 * Created by bigming on 16/11/14.
 * 题目: 给定一个路径数组paths, 表示一张图. paths[i]=j代表城市i连向城市j,
 *      如果paths[i]=j, 则表示i城市是首都,一张图里只会有一个首都且图中除
 *      首都指向自己之外都不会有环. 例如paths=[9,1,4,9,0,4,8,9,0,1],
 *      其中1是首都,所以距离为0, 离首都距离为1的城市只有城市9,离首都距离
 *      为2的城市有0,3,7.
 *      于是统计数组nums=[1,1,3,2,3,0,0,0,0,0], nums[i]=j代表距离为i的
 *      城市有j座.要求实现一个void类型的函数,输入一个路径数组paths, 直接在
 *      原数组上调整,使之变为nums数组.即paths=[9,1,4,9,0,4,8,9,0,1]经过
 *      该函数处理后变成[1,1,3,2,3,0,0,0,0,0].
 * 要求: 如果paths数组长度为N, 要求时间复杂度为O(N).额外空间复杂度为O(1).
 * 难度: ***
 * 思路: 本题由于复杂度要求的设置,完全考查代码实现技巧.
 *      怎么在一个数组上不停地折腾且不出错是非常锻炼边界处理能力的!!!
 *      该解法分为两步:
 *      1. 将paths数组转换为距离数组,即paths=[9,1,4,9,0,4,8,9,0,1]转换为
 *         [-2,0,-4,-2,-3,-4,-4,-2,-3,-1].用负数是为了该步已及下一步的方
 *         便操作.
 *      2. 将距离数组转换为统计数组.
 *
 */
public class Problem_13_PathsToNums {
    public static void pathsToDistances(int[] paths){
        int cap = 0;
        for (int i = 0; i < paths.length; i++){
            if (paths[i] == i){
                cap = i;
            } else if (paths[i] > -1){
                int curI = paths[i];
                paths[i] = -1; // 设置-1的作用是用来做循环终止的条件标记
                int preI = i;
                while (paths[curI] != curI){ // 无论如何都会终止的情况
                    if (paths[curI] > -1){ // 提前终止的情况.
                        int nextI = paths[curI];
                        paths[curI] = preI;
                        preI = curI;
                        curI =  nextI;
                    } else {
                        break;
                    }
                }
                int value = paths[curI] == curI ? 0 : paths[curI];
                while (paths[preI] != -1){ // 之前设置-1用在这里
                    int lastPreI = paths[preI];
                    paths[preI] = --value;
                    curI = preI;
                    preI = lastPreI;
                }
                paths[preI] = --value;
            }
        }
        paths[cap] = 0;
    }

    public static void distanceToNums(int[] disArr){
        for (int i = 0; i < disArr.length; i++){
            int index = disArr[i];
            if (index < 0){
                disArr[i] = 0; // 设置为0表示不再表示距离,而是统计了.
                while (true){
                    index = -index;
                    if (disArr[index] > -1){
                        disArr[index]++;
                        break;
                    } else {
                        int nextIndex = disArr[index];
                        disArr[index] = 1;
                        index = nextIndex;
                    }
                }
            }
        }
        disArr[0] = 1;
    }

    public static void  pathsToNums(int[] paths){
        if (paths == null || paths.length == 0){
            return;
        }

        pathsToDistances(paths);
        distanceToNums(paths);
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] paths = { 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 };
        printArray(paths);
        pathsToNums(paths);
        printArray(paths);

    }
}
