package chapter_2_listproblem_me;

/**
 * Created by bigming on 16/8/20.
 *
 * 题目: 反转部分单向链表
 * 难度: *
 * 思路: 找出from的前一个节点和to的后一个节点,反转from到to的节点,然后首尾接上,
 *      这里需要注意的是反转部分是否包含头节点,如果包含,则需要返回新的头节点.
 *
 */
public class Problem_05_ReversePartList_me {
    public static class Node{
        public int value;
        public Node next;
        public Node(int value ){
            this.value = value;
        }
    }

    public static Node reversePart(Node head, int from, int to) {
        int len = 0;
        Node node1= head;
        Node fPre = null;
        Node tPos = null;
        while (node1 != null){
            len ++;
            fPre = len == from-1? node1: fPre;
            tPos = len == to + 1? node1: tPos;
            node1 = node1.next;
        }

        if (from > to || from<1 || to>len){
            return head;
        }

        node1 = fPre == null ? head: fPre.next;
        Node node2 = node1.next;
        node1.next =tPos;
        Node next = null;
        while (node2 != tPos){
            next = node2.next;
            node2.next =node1;
            node1 = node2;
            node2 = next;
        }

        if (fPre != null){
            fPre.next = node1;
            return head;
        }
        return node1;

    }



    public static void printLinkedList(Node head) {
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        printLinkedList(head);
        head = reversePart(head, 1, 1);
        printLinkedList(head);

        head = new Node(1);
        printLinkedList(head);
        head = reversePart(head, 1, 1);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        head = reversePart(head, 1, 2);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        head = reversePart(head, 2, 3);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        head = reversePart(head, 1, 3);
        printLinkedList(head);

    }


}
