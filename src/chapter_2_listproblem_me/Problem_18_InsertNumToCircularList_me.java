package chapter_2_listproblem_me;

/**
 * Created by bigming on 16/8/28.
 *
 * 题目: 向有序的环形链表中插入新节点.
 * 难度: *
 * 思路: 主要问题是如果插入的数值是最小的或者是最大的,需要在头和尾之间插入.
 *
 */
public class Problem_18_InsertNumToCircularList_me {
    public static class Node{
        public int value;
        public Node next;

        public Node(int value){
            this.value = value;
        }
    }

    public static Node insertNum(Node head, int num){
        Node node = new Node(num);
        if (head == null){
            node.next = node;
            return node;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != head){
            if (pre.value < num && cur.value >= num){
                break;
            }
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = node;
        node.next = cur;
        return num < head.value ? node : head;
    }


    public static void printCircularList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.value + " ");
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.value);
    }

    public static void main(String[] args) {
        Node head = null;
        head = insertNum(head, 2);
        printCircularList(head);
        head = insertNum(head, 1);
        printCircularList(head);
        head = insertNum(head, 4);
        printCircularList(head);
        head = insertNum(head, 3);
        printCircularList(head);
        head = insertNum(head, 5);
        printCircularList(head);
        head = insertNum(head, 0);
        printCircularList(head);

    }
}
