package chapter_3_binarytreeproblem_me;

import java.util.Stack;

/**
 * Created by bigming on 16/8/29.
 */
public class Problem_01_PreInPosTraversal_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static void preOrderRecur(Node head){
        if (head == null){
            return;
        }

        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head){
        if (head == null){
            return;
        }

        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    public static void posOrderRecur(Node head){
        if (head ==  null){
            return;
        }

        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    public static void preOrderUnRecur(Node head){
        System.out.print("pre-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            while (!stack.isEmpty()){
                Node node = stack.pop();
                System.out.print(node.value + " ");
                if (node.right != null){
                    stack.push(node.right);
                }
                if (node.left != null){
                    stack.push(node.left);
                }
            }
        }
        System.out.println();
    }

    public static void inOrderUnRecur(Node head){
        System.out.print("in-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null){
                if (head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }


    public static void posOrderUnRecur1(Node head){
        System.out.print("pos-order: ");
        if (head != null){
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()){
                head = s1.pop();
                s2.push(head);
                if (head.left != null){
                    s1.push(head.left);
                }
                if (head.right != null){
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()){
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    public static void posOrderUnRecur2(Node cur){
        System.out.print("pos-order: ");
        if (cur != null){
            Stack<Node> stack = new Stack<Node>();
            stack.push(cur);
            Node head = null;
            while (!stack.isEmpty()){
                head = stack.peek();
                if (head.left != null && cur != head.left && cur != head.right){
                    stack.push(head.left);
                }else if (head.right != null && cur != head.right){
                    stack.push(head.right);
                }else {
                    System.out.print(stack.pop().value + " ");
                    cur = head;
                }
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur1(head);
        posOrderUnRecur2(head);

    }



}
