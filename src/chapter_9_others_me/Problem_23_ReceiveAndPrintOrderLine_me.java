package chapter_9_others_me;

import java.util.HashMap;

/**
 * Created by bigming on 16/11/19.
 * 题目: 一种接收消息并打印的结构设计
 *      已知一个消息流会不断地吐出整数1~N, 但不一定按照顺序吐出. 如果
 *      上次打印的数为i, 那么当i+1出现时, 请打印i+1及其之后接收过的并且
 *      连续的所有数,直到1~N全部接收并打印完, 请设计这种接收并打印的结构.
 * 难度: **
 * 思路: 这里主要就是处理连续空间并合并连续空间的问题.
 */
public class Problem_23_ReceiveAndPrintOrderLine_me {
    public static class Node {
        public int num;
        public Node next;

        public Node(int num) {
            this.num = num;
        }
    }

    public static class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int lastPrint;

        public MessageBox() {
            this.headMap = new HashMap<Integer, Node>();
            this.tailMap = new HashMap<Integer, Node>();
            this.lastPrint = 0;
        }

        public void receive(int num) {
            if (num < 1) {
                return;
            }
            Node cur = new Node(num);
            headMap.put(num, cur);
            tailMap.put(num, cur);
            if (tailMap.containsKey(num - 1)) {
                tailMap.get(num - 1).next = cur;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            if (headMap.containsKey(num + 1)) {
                cur.next = headMap.get(num + 1);
                headMap.remove(num + 1);
                tailMap.remove(num);
            }
            if (headMap.containsKey(lastPrint + 1)) {
                print();
            }
        }

        public void print() {
            Node node = headMap.get(++lastPrint);
            headMap.remove(lastPrint);
            while (node != null) {
                System.out.print(node.num + " ");
                node = node.next;
                lastPrint++;
            }
            tailMap.remove(--lastPrint);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();

        box.receive(2); // - 2
        box.receive(1); // 1 2 -> print, trigger is 1

        box.receive(4); // - 4
        box.receive(5); // - 4 5
        box.receive(7); // - 4 5 - 7
        box.receive(8); // - 4 5 - 7 8
        box.receive(6); // - 4 5 6 7 8
        box.receive(3); // 3 4 5 6 7 8 -> print, trigger is 3

        box.receive(9); // 9 -> print, trigger is 9

        box.receive(10); // 10 -> print, trigger is 10

        box.receive(12); // - 12
        box.receive(13); // - 12 13
        box.receive(11); // 11 12 13 -> print, trigger is 11

    }
}
