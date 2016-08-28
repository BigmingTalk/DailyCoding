package chapter_2_listproblem_me;

/**
 * Created by bigming on 16/8/28.
 *
 * 题目: 合并两个有序的单链表
 * 难度: *
 * 思路: 比较大小,用一个pre指针指向较小的那一个,然后让链表2的节点不断插入到链表1中.
 *
 */
public class Problem_19_MergeTwoLinkedLists_me {
    public static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public static Node merge(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }

        Node head = head1.value < head2.value ? head1 : head2;
        Node pre = null;
        Node cur1 = head1.value < head2.value ? head1 : head2;
        Node cur2 = head1.value < head2.value ? head2 : head1;
        Node next = null;
        while (cur1 != null && cur2 != null){
            if (cur1.value < cur2.value){
                pre = cur1;
                cur1 = cur1.next;
            }else{
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }

        pre.next = cur1 != null ? cur1 : cur2;
        return head;
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

        Node head1 = null;
        Node head2 = null;
        Node head = merge(head1, head2);
        printLinkedList(head);

        head1 = new Node(1);
        head2 = null;
        head = merge(head1, head2);
        printLinkedList(head);

        head1 = null;
        head2 = new Node(1);
        head = merge(head1, head2);
        printLinkedList(head);

        head1 = new Node(1);
        head2 = new Node(2);
        head = merge(head1, head2);
        printLinkedList(head);

        head1 = new Node(2);
        head2 = new Node(1);
        head = merge(head1, head2);
        printLinkedList(head);

        head1 = new Node(1);
        head1.next = new Node(4);
        head2 = new Node(2);
        head2.next = new Node(3);
        head2.next.next = new Node(5);
        head = merge(head1, head2);
        printLinkedList(head);

        head1 = new Node(1);
        head1.next = new Node(3);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(7);
        head1.next.next.next.next = new Node(9);
        head2 = new Node(0);
        head2.next = new Node(6);
        head2.next.next = new Node(6);
        head2.next.next.next = new Node(7);
        head = merge(head1, head2);
        printLinkedList(head);

    }
}
