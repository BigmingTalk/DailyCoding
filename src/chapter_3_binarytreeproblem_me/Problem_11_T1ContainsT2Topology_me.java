package chapter_3_binarytreeproblem_me;

/**
 * Created by bigming on 16/9/6.
 *
 * 题目: 判断t1树是否包含t2树全部的拓扑结构
 * 难度: *
 * 思路: 检查每一个t1节点为头结点的子树中是否包含t2的结构即可
 *
 */
public class Problem_11_T1ContainsT2Topology_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static boolean contains(Node t1, Node t2){
        return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
    }

    public static boolean check(Node h, Node t2){
        if (t2 == null){
            return true;
        }
        if (h == null || h.value != t2.value){
            return false;
        }
        return check(h.left, t2.left) && check(h.right, t2.right);
    }


    public static void main(String[] args) {
        Node t1 = new Node(1);
        t1.left = new Node(2);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(5);
        t1.right.left = new Node(6);
        t1.right.right = new Node(7);
        t1.left.left.left = new Node(8);
        t1.left.left.right = new Node(9);
        t1.left.right.left = new Node(10);

        Node t2 = new Node(2);
        t2.left = new Node(4);
        t2.left.left = new Node(8);
        t2.right = new Node(5);

        System.out.println(contains(t1, t2));

    }
}
