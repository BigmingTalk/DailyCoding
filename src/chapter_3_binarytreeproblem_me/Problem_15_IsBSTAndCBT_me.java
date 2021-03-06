package chapter_3_binarytreeproblem_me;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bigming on 16/9/9.
 * 题目: 判断一棵二叉树是否为搜索二叉树和完全二叉树
 * 难度: *
 * 思路: 判断二叉树可以用中序遍历,看遍历的时候节点值是否递增即可.这里用的是
 *      Morris遍历
 *      判断是否为完全二叉树,可以按层遍历,如果当前节点有右孩子没有左孩子,
 *      返回false,如果当前节点不是左右孩子全有,那之后节点必须都为叶节点,
 *      否则返回false,如果遍历过程纵横不返回false,直接返回true.
 *
 */
public class Problem_15_IsBSTAndCBT_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static boolean isBST(Node head){
        if (head == null){
            return true;
        }
        boolean res = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null){
            cur2 = cur1.left;
            if (cur2 != null){
                while (cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if (cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value > cur1.value){
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }

    public static boolean isCBT(Node head){
        if (head == null){
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((l==null && r!= null) || (leaf && (l!=null || r!=null))){
                return false;
            }
            if (l != null){
                queue.offer(l);
            }
            if (r != null){
                queue.offer(r);
            } else {
                leaf = true;
            }
        }
        return true;
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
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));

    }
}
