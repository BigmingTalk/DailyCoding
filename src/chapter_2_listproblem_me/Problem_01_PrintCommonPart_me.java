package chapter_2_listproblem_me;

/**
 * Created by bigming on 16/8/19.
 *
 * 题目: 给定两个有序链表的头指针head1和head2,打印两个链表的公共部分
 * 难度: *
 * 分析: 因为都是有序的,所以只需要比较大小然后做相应的移动即可
 */
public class Problem_01_PrintCommonPart_me {
    public static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public static  void printCommonPart(Node head1, Node head2){
        System.out.print("Commont Part: ");
        while (head1!=null && head2!= null){
            if (head1.value == head2.value){
                System.out.print(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }else if(head1.value < head2.value){
                head1 = head1.next;
            }else {
                head2 = head2.next;
            }
        }
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        printLinkedList(node1);
        printLinkedList(node2);
        printCommonPart(node1, node2);

    }

}
