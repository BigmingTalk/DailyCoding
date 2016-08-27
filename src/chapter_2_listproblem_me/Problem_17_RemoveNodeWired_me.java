package chapter_2_listproblem_me;

/**
 * Created by bigming on 16/8/27.
 * 题目: 给定一个链表中的节点node,但不给定头节点,要求删除node
 * 难度: *
 * 思路: 例如链表为1-2-3-4-null,要求删除3节点,那么只需要把3节点的值改为4,
 *      然后删除4这个节点就可以.但是这种方法有问题,比如如果删除的是最后一个
 *      节点,在这里为4,则无法让3指向null,这里不能让4直接变为null,因为null
 *      是系统中一块特殊的区域.而且实际的应用中节点可能不止有值,还有其他一些
 *      信息.
 *
 */
public class Problem_17_RemoveNodeWired_me {
    public static class Node{
        public int value;
        public Node next;

        public Node(int value){
            this.value = value;
        }
    }

    public static void removeNodeWired(Node node){
        if (node == null){
            return;
        }
        Node next = node.next;
        if (next == null){
            throw new RuntimeException("can not remove last node.");
        }
        node.value = next.value;
        node.next = next.next;
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
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        Node node = head;
        printLinkedList(head);
        removeNodeWired(node);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        node = head.next;
        printLinkedList(head);
        removeNodeWired(node);
        printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		node = head.next.next;
		printLinkedList(head);
		removeNodeWired(node);
		printLinkedList(head);

    }


}
