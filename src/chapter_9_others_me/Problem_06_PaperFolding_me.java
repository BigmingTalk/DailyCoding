package chapter_9_others_me;

/**
 * Created by bigming on 16/11/3.
 * 题目: 折纸问题
 *      把一段纸条竖着放在桌子上,然后从纸条的下边向上对折一次,压出折痕后
 *      展开,此时折痕是凹下去的,即折痕突出的方向指向纸条的背面. 如果从纸条
 *      的下边向上方连续对折2次,压出折痕后展开,此时有三条折痕,从上到下依次
 *      是下折痕,下折痕和上折横.给定一个输入参数N,代表纸条都从下边向上方连续
 *      对折N次,请从上到下打印所有折痕的方向.
 * 例如: N = 1时, 打印:
 *      down
 *      N = 2时, 打印:
 *      down
 *      down
 *      up
 * 难度: **
 * 思路: 把前几次的规律找一下就能够发现,这是一个头结点为down的满二叉树的遍历过程,
 *      遍历用先右,再中,后左的中序遍历即可.
 */
public class Problem_06_PaperFolding_me {
    public static void printAllFolds(int N){
        printProcess(1, N, true);
    }

    public static void  printProcess(int i, int N, boolean down){
        if (i > N){
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "down " : "up ");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);
    }
}
