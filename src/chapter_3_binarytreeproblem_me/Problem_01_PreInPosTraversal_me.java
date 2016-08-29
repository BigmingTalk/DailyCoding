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

    public void preOrderRecur(Node head){
        if (head == null){
            return;
        }

        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public void inOrderRecur(Node head){
        if (head == null){
            return;
        }

        inOrderRecur(head.left);
        System.out.println(head.value + " ");
        inOrderRecur(head.right);
    }

    public void posOrderRecur(Node head){
        if (head ==  null){
            return;
        }

        inOrderRecur(head.left);
        inOrderRecur(head.right);
        System.out.println(head.value + " ");
    }

    public void preOrderUnRecur(Node head){
        System.out.println("pre-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            while (!stack.isEmpty()){
                Node node = stack.pop();
                System.out.println(node.value + " ");
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

    public void inOrderUnRecur(Node head){
        System.out.println("in-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null){
                if (head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }


    public void posOrderUnRecur1(Node head){
        System.out.println("pos-order: ");
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

    public void posOrderUnRecur2(Node head){
        System.out.println("pos-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            Node cur = null;
            while (!stack.isEmpty()){
                cur = stack.peek();
                if (cur.left != null && head != cur.left && head != cur.right){
                    stack.push(cur.left);
                }else if (cur.right != null && head != cur.right){
                    stack.push(cur.right);
                }else {
                    System.out.print(stack.pop().value + " ");
                    head = cur;
                }
            }
        }
        System.out.println();
    }





}
