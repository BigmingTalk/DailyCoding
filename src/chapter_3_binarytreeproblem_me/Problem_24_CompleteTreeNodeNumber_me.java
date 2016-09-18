package chapter_3_binarytreeproblem_me;

/**
 * Created by bigming on 16/9/18.
 * 题目: 给定一棵完全二叉树的头结点head, 返回这棵树的节点个数
 *      要求复杂度为O(h^2),h为树的高度
 * 难度: **
 * 思路: 先一直到左节点算出树的高度,再根据右节点的最左是不是到
 *      了树的高度判断是左子树是满的还是右子树是满的,然后递归即可
 *
 */
public class Problem_24_CompleteTreeNodeNumber_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static int numNode(Node head){
        if (head == null){
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    public static int bs(Node node, int l, int h){
        if (l == h){
            return 1;
        }
        if (mostLeftLevel(node.right, l + 1) == h){
            return (1 << (h - l)) + bs(node.right, l + 1, h);
        } else {
            return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
        }
    }

    public static int mostLeftLevel(Node node, int level){
        while (node != null){
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(numNode(head));

    }
}
