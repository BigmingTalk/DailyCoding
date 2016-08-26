package chapter_2_listproblem_me;

import java.util.HashSet;

/**
 * Created by bigming on 16/8/26.
 *
 * 题目: 删除链表中相同的元素,只保留一个,如原来链表是1-1-2-3-4-2-3-4-4-4-2-1-1-null,
 *      则最后应该是1-2-3-4-null
 * 思路: 第一种是用HashSet,很容易理解,第二种是用类似选择排序的思想,从第一个元素开始,删除与它
 *      的值相同的节点,不断遍历到null,则最后都保留了只出现第一次出现的节点.
 * 难度: *
 *
 */
public class Problem_13_RemoveRepetition_me {
    public static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public static void removeRep1(Node head){
        if (head == null){
            return;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        Node pre = head;
        Node cur = head.next;
        set.add(head.value);
        while (cur != null){
            if (set.contains(cur.value)){
                pre.next = cur.next;
            }else {
                set.add(cur.value);
                pre = cur;
            }
            cur = cur.next;
        }
    }

    public static void removeRep2(Node head){
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null){
            pre = cur;
            next = cur.next;
            while (next != null){
                if (cur.value == next.value){
                    pre.next = next.next;
                }else{
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }
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
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next = new Node(1);
        head.next.next.next.next.next.next.next.next = new Node(1);
        printLinkedList(head);
        removeRep1(head);
        printLinkedList(head);

        head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(3);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next = new Node(1);
        head.next.next.next.next.next.next.next.next = new Node(1);
        printLinkedList(head);
        removeRep2(head);
        printLinkedList(head);

    }
}
