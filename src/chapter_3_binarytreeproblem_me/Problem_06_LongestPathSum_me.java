package chapter_3_binarytreeproblem_me;

import java.util.HashMap;

/**
 * Created by bigming on 16/9/2.
 * 题目: 在二叉树中找到累加和为指定值的最长路径长度
 * 难度: **
 * 思路: 与第8章第11题中秋未排序数组中累加和为规定值的最长子数组长度
 *      想法一致,在这里只不过是hashmap中记录的是具体的层数.并且在如果
 *      遍历完一个节点时如果hashmap中的值是该节点添加的,需要删除,因为
 *      接下来的遍历与该节点无关,即不经过该节点.
 *
 */
public class Problem_06_LongestPathSum_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static int getMaxLength(Node head, int sum){
        HashMap<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        sumMap.put(0, 0);
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }

    public static int preOrder(Node head, int sum, int preSum, int level,
                               int maxLen, HashMap<Integer, Integer> sumMap){
        if (head == null){
            return maxLen;
        }
        int curSum = preSum + head.value;
        if (!sumMap.containsKey(curSum)){
            sumMap.put(curSum, level);
        }
        if (sumMap.containsKey(curSum - sum)){
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }
        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);

        // 如果节点值是在该节点添加的,则删除,因为未来不会结果该节点.
        if (level == sumMap.get(curSum)){
            sumMap.remove(curSum);
        }
        return maxLen;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(-3);
        head.left = new Node(3);
        head.right = new Node(-9);
        head.left.left = new Node(1);
        head.left.right = new Node(0);
        head.left.right.left = new Node(1);
        head.left.right.right = new Node(6);
        head.right.left = new Node(2);
        head.right.right = new Node(1);
        printTree(head);
        System.out.println(getMaxLength(head, 6));
        System.out.println(getMaxLength(head, -9));

    }

}
