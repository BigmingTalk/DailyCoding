package chapter_2_listproblem_me;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bigming on 16/8/27.
 *
 * 题目: 将搜索二叉树转换成双向链表
 * 难度: **
 * 思路: 第一种方法是将二叉树采用中序遍历的方式放到队列中,然后对队列进行操作即可,时间空间
 *      复杂度都是O(N)
 *      第二种方法是递归,递归的时候要将双向链表转换为一种特殊形式,即尾节点的right指正指向
 *      头节点,这样是为了方便查找到头尾指针.
 *
 */
public class Problem_15_BSTtoDoubleLinkedList_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static void inOrderToQueue(Node head, Queue<Node> queue){
        if (head == null){
            return;
        }
        inOrderToQueue(head.left, queue);
        queue.add(head);
        inOrderToQueue(head.right, queue);
    }

    public static Node convert1(Node head){
        Queue<Node> queue = new LinkedList<Node>();
        inOrderToQueue(head, queue);
        if (queue.isEmpty()){
            return head;
        }
        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()){
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    public static Node process(Node head){
        if (head == null){
            return null;
        }
        Node leftE = process(head.left); //左半部分的尾节点
        Node rightE = process(head.right); //右半部分的尾节点
        Node leftS = leftE != null ? leftE.right : null;
        Node rightS = rightE != null ? rightE.right : null;
        if (leftE != null && rightE != null){
            leftE.right = head;
            head.left =leftE;
            rightS.left = head;
            head.right = rightS;
            rightE.right = leftS;
            return rightE;
        }else if (leftE != null){
            leftE.right = head;
            head.left = leftE;
            head.right = leftS;
            return head;
        }else if (rightE != null){
            head.right = rightS;
            rightS.left = head;
            rightE.right = head;
            return rightE;
        }else {
            head.right = head;
            return head;
        }
    }

    public static Node convert2(Node head){
        if (head == null){
            return null;
        }
        Node last = process(head);
        head = last.right;
        last.right = null;
        return head;
    }

    public static void inOrderPrint(Node head) {
        if (head == null) {
            return;
        }
        inOrderPrint(head.left);
        System.out.print(head.value + " ");
        inOrderPrint(head.right);
    }

    public static void printBSTInOrder(Node head) {
        System.out.print("BST in-order: ");
        if (head != null) {
            inOrderPrint(head);
        }
        System.out.println();
    }

    public static void printDoubleLinkedList(Node head) {
        System.out.print("Double Linked List: ");
        Node end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert1(head);
        printDoubleLinkedList(head);

        head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);

        printBSTInOrder(head);
        head = convert2(head);
        printDoubleLinkedList(head);

    }
}
