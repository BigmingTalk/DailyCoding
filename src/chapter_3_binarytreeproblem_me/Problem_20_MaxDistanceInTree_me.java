package chapter_3_binarytreeproblem_me;

/**
 * Created by bigming on 16/9/14.
 * 题目: 找出二叉树节点间的最大距离问题
 * 难度: **
 * 思路: 一个以h为头的树上,最大距离可能来自以下三种情况
 *      1. h的左子树上的最大距离
 *      2. h的右子树上的最大距离
 *      3. h左子树上离h.left最远距离 + 1(h) + h的右子树上离h.right最远的距离
 */
public class Problem_20_MaxDistanceInTree_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static int maxDistance(Node head){
        int[] record = new int[1];
        return posOrder(head, record);
    }

    public static int posOrder(Node head, int[] record){
        if (head == null){
            record[0] = 0;
            return 0;
        }
        int lMax = posOrder(head.left, record);
        int maxFromLeft = record[0];
        int rMax = posOrder(head.right, record);
        int maxFromRight = record[0];
        int curNodeMax = maxFromLeft + maxFromRight + 1;
        record[0] = Math.max(maxFromLeft, maxFromRight) + 1;
        return Math.max(Math.max(lMax, rMax), curNodeMax);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
        System.out.println(maxDistance(head1));

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
        System.out.println(maxDistance(head2));

    }

}
