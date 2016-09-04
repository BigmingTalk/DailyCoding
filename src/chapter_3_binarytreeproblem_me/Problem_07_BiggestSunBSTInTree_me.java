package chapter_3_binarytreeproblem_me;

/**
 * Created by bigming on 16/9/4.
 * 题目: 找到二叉树中复合搜索二叉树条件的最大拓扑结构
 * 思路: 二叉搜索树：对于树中的每个节点X，它的左子树中所有项的值都小于X，右子树所有值都大于X。
 *      所以,用后序遍历的思想,判断是否满足条件即可.
 * 难度: **
 */
public class Problem_07_BiggestSunBSTInTree_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public Node biggestSubBST(Node head){
        int[] record = new int[3];
        return posOrder(head, record);
    }

    public Node posOrder(Node head, int[] record){
        if (head == null){
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }

        int value = head.value;
        Node left = head.left;
        Node right = head.right;
        Node lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax =record[2];
        Node rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];

        record[1] = Math.min(lMin, value);
        record[2] = Math.max(rMax, value);
        if (left == lBST && right == rBST && lMax < value && value < rMin){
            record[0] = lSize + rSize + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
    }

}
