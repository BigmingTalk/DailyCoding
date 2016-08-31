package chapter_3_binarytreeproblem_me;

/**
 * Created by bigming on 16/8/31.
 * 题目: 如何较为直观地打印二叉树
 * 难度: **
 * 思路: 仅通过三种遍历的结果来构造二叉树的真实结构很难,
 *      而且如果有重复结构的话会有很多种可能性,最极端的
 *      例子就是所有节点的值都是一样的.在设计时,最重要的
 *      是优良性与复杂性的一个平衡.
 *
 */
public class Problem_03_PrintBinaryTree_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static void printTree(Node head){
        System.out.println("Binary Tree: ");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len){
        if (head == null){
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

    public static String getSpace(int num){
        String space = " ";
        StringBuffer buffer = new StringBuffer("");
        for (int i  = 0; i < num; i++){
            buffer.append(space);
        }
        return buffer.toString();
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);

    }

}
