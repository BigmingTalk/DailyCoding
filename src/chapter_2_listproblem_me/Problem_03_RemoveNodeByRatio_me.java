package chapter_2_listproblem_me;

import java.util.Map;

/**
 * Created by bigming on 16/8/19.
 * 题目: 删除中间节点以及第a/b处的节点
 * 难度: *
 * 分析: 主要是一些细节地方处理要注意,删除中间节点的时候分别
 *      用一个pre节点和cur节点,cur节点每次跳2步,pre跳一步,
 *      则当cur节点到尾部时pre刚好在中间(中间前一个?主要是
 *      思想是这样的).
 */
public class Problem_03_RemoveNodeByRatio_me {
    public static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public static Node removeMidNode(Node head){
        if (head==null || head.next ==null){
            return head;
        }

        if (head.next.next == null){
            return head.next;
        }

        Node pre = head;
        Node cur = head.next.next;
        while (cur.next!=null && cur.next.next !=null){
            pre = pre.next;
            cur = cur.next.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    public static Node removeByRatio(Node head, int a, int b){
        if (a<1 || a>b){
            return head;
        }
        int n = 0;
        Node cur = head;
        while (cur!=null){
            n++;
            cur =cur.next;
        }
        n = (int) Math.ceil(((double)(a*n))/(double)b);
        if (n ==1){
            head = head.next;
        }
        if (n>1){
            cur = head;
            while (--n!=1){
                cur =cur.next;
            }
            cur.next = cur.next.next;
        }
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
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        printLinkedList(head);
        head = removeMidNode(head);
        printLinkedList(head);
        head = removeByRatio(head, 2, 5);
        printLinkedList(head);
        head = removeByRatio(head, 1, 3);
        printLinkedList(head);

    }

}
