package chapter_3_binarytreeproblem_me;

/**
 * Created by bigming on 16/9/11.
 * 题目: 在二叉树中找到一个节点的后继节点, 在这里节点有parent节点,
 *      后继节点指的是中序遍历的序列中node的下一个节点
 * 难度: **
 * 思路: 如果node节点有右子树,则右子树最左边的就是
 *      如果node没有右子树,要先看node是不是node父节点的左孩子,如果是,
 *      那么node父节点就是node的后继节点,如果是右孩子,就向上寻找node
 *      的后继几点,假设向上移动的节点为s,s的父节点为p,如果发现s是p的左孩子,
 *      那么节点p就是node节点的后继节点,否则一直向上移动直到为空,则node根本
 *      不存在后继节点.
 *
 */
public class Problem_17_DescendantNode_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value){
            this.value = value;
        }
    }

    public static Node getNextNode(Node node){
        if (node == null){
            return node;
        }
        if (node.right != null){
            return getLeftMost(node.right);
        } else {
            Node parent = node.parent;
            while (parent != null && parent.left != node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node){
        if (node == null){
            return node;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;
    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getNextNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getNextNode(test));
    }

}
