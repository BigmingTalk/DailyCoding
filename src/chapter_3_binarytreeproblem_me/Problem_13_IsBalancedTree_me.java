package chapter_3_binarytreeproblem_me;

import java.util.SimpleTimeZone;

/**
 * Created by bigming on 16/9/9.
 * 题目: 判断二叉树是否为平衡二叉树
 * 难度: *
 * 思路: 平衡二叉树为要么是一棵空树,要么任何一个节点的左右子树高度差的绝对值
 *      不超过1.给定一棵二叉树的头结点head,判断这棵二叉树是否为平衡二叉树.
 *      可以采用后续遍历的思路,在遍历的过程中搜集两个信息,即node的左子树
 *      是否为平衡二叉树以及最深到哪一层,以及node的右子树是否为平衡二叉树以及
 *      最深到哪一层.
 */
public class Problem_13_IsBalancedTree_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static boolean isBalance(Node head){
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(head, 1, res);
        return res[0];
    }

    public static int getHeight(Node head, int level, boolean[] res){
        if (head == null){
            return level;
        }
        int lH = getHeight(head.left, level + 1, res);
        if (!res[0]){
            return level;
        }
        int rH = getHeight(head.right, level + 1, res);
        if (!res[0]){
            return level;
        }
        if (Math.abs(lH - rH) > 1){
            res[0] = false;
        }
        return Math.max(lH, rH);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));

    }

}
