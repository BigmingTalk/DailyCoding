package chapter_3_binarytreeproblem_me;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by bigming on 16/9/12.
 * 题目: 在二叉树中找到两个节点的最近公共祖先
 *      进阶: 如果查询非常频繁,想办法让当次查询的时间减少
 *      再进阶:给定二叉树的头结点,同时给定所有想要进行的查询,
 *            二叉树的节点数为N,查询条数的M,请在时间复杂度为
 *            O(M+N)内返回所有查询的结果.
 * 难度: *
 *      进阶: **
 *      再进阶: ***
 *
 * 思路: 原问题用递归即可,即返回null或两个节点o1或o2.
 *      进阶: 思路一:建立一张每个节点对应的父节点信息哈希表.
 *            思路二: 为每两个节点之间建立信息.对二叉树的每棵子树(共N棵)
 *                  都进行下列步骤,假设子树头结点为h,h所有的后代节点和h节点
 *                  的最近公共祖先都是h,记录下列,h左子树和h右子树的每个节点
 *                  的公共祖先都是h,记录下来.
 *      再进阶: 请看下一题Tarjan算法
 */
public class Problem_18_LowestCommonAncestor_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static Node lowestAncestor(Node head, Node o1, Node o2){
        if (head == null || head == o1 || head == o2){
            return head;
        }
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        if (left != null && right != null){
            return head;
        }
        return left != null ? left : right;
    }

    public static class Record1{
        private HashMap<Node, Node> map;

        public Record1(Node head){
            map = new HashMap<Node, Node>();
            if (head != null){
                map.put(head, null);
            }
            setMap(head);
        }

        private void setMap(Node head){
            if (head == null){
                return;
            }
            if (head.left != null){
                map.put(head.left, head);
            }
            if (head.right != null){
                map.put(head.right, head);
            }
            setMap(head.left);
            setMap(head.right);
        }

        public Node query(Node o1, Node o2){
            HashSet<Node> path = new HashSet<Node>();
            while (map.containsKey(o1)){
                path.add(o1);
                o1 = map.get(o1);
            }
            while (!path.contains(o2)){
                o2 = map.get(o2);
            }
            return o2;
        }
    }


    public static class Record2{
        private HashMap<Node, HashMap<Node, Node>> map;

        public Record2(Node head){
            map = new HashMap<Node, HashMap<Node, Node>>();
            initMap(head);
            setMap(head);
        }

        private void initMap(Node head){
            if (head == null){
                return;
            }
            map.put(head, new HashMap<Node, Node>());
            initMap(head.left);
            initMap(head.right);
        }

        private void setMap(Node head){
            if (head == null){
                return;
            }
            //h所有的后代节点和h节点的最近公共祖先都是h
            headRecord(head.left, head);
            headRecord(head.right, head);
            // h左子树每个节点和h右子树每个节点的最近公共祖先都是h
            subRecord(head);
            setMap(head.left);
            setMap(head.right);
        }

        private void headRecord(Node n, Node h){
            if (n == null){
                return;
            }
            map.get(n).put(h, h);
            headRecord(n.left, h);
            headRecord(n.right, h);
        }

        private void subRecord(Node head){
            if (head == null){
                return;
            }
            preLeft(head.left, head.right, head);
            subRecord(head.left);
            subRecord(head.right);
        }

        private void preLeft(Node l, Node r, Node h){
            if (l == null){
                return;
            }
            preRight(l, r, h);
            preLeft(l.left, r, h);
            preLeft(l.right, r, h);
        }

        private void preRight(Node l, Node r, Node h){
            if (r == null){
                return;
            }
            map.get(l).put(r, h);
            preRight(l, r.left, h);
            preRight(l, r.right, h);
        }

        public Node query(Node o1, Node o2){
            if (o1 == o2){
                return o1;
            }
            if (map.containsKey(o1)){
                return map.get(o1).get(o2);
            }
            if (map.containsKey(o2)){
                return map.get(o2).get(o1);
            }
            return null;
        }
    }


    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.right.left = new Node(8);
        printTree(head);
        System.out.println("===============");

        Node o1 = head.left.right;
        Node o2 = head.right.left;

        System.out.println("o1 : " + o1.value);
        System.out.println("o2 : " + o2.value);
        System.out.println("ancestor : " + lowestAncestor(head, o1, o2).value);
        System.out.println("===============");

        Record1 record1 = new Record1(head);
        Record2 record2 = new Record2(head);

        System.out.println("o1 : " + o1.value);
        System.out.println("o2 : " + o2.value);
        System.out.println("ancestor : " + record1.query(o1, o2).value);
        System.out.println("ancestor : " + record2.query(o1, o2).value);

        o1 = head.left.left;
        o2 = head.left;
        System.out.println("o1 : " + o1.value);
        System.out.println("o2 : " + o2.value);
        System.out.println("ancestor : " + record1.query(o1, o2).value);
        System.out.println("ancestor : " + record2.query(o1, o2).value);

        o1 = head.right.left;
        o2 = head.right.right.left;
        System.out.println("o1 : " + o1.value);
        System.out.println("o2 : " + o2.value);
        System.out.println("ancestor : " + record1.query(o1, o2).value);
        System.out.println("ancestor : " + record2.query(o1, o2).value);

    }

}
