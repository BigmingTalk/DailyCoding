package chapter_1_stackandqueue_me;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by bigming on 16/8/17.
 * 题目: 给定一个没有重复元素的数组,构建一个二叉树,且该数的任何一个子树
 *      上值最大的节点都是树的头
 * 难度: ***
 * 要点: 以下列原则来建立这颗树:
 *      1. 每一个数的父节点是它在左边第一个比它大的数和它右边第一个比它大的数中,较小的那个
 *      2. 如果一个数左边没有比它大的数,右边也没有,那么它就是整个数组的最大值,也就是head节点
 * 证明思路:
 *      1. 证明是一棵树而不是森林,因为没有重复值,所以会有一个最大的数作为头节点
 *      2. 证明是二叉树,而不是多叉树,可以用反证法,列出来一个具体例子
 *
 */
public class Problem_08_MaxTree_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static Node getMaxTree(int[] array){
        Node newArr[] = new Node[array.length];
        for (int i = 0; i< array.length; i ++){
            newArr[i] = new Node(array[i]);
        }

        Stack<Node> stack = new Stack<Node>();
        HashMap<Node, Node> lBigmap = new HashMap<Node, Node>();
        HashMap<Node, Node> rBigmap = new HashMap<Node, Node>();
        for (int i = 0; i< newArr.length; i++){
            Node curNode = newArr[i];
            //当不满足栈中元素从底到顶是从大到小时,则要对现有栈中的元素做pop操作,直到满足为止
            while((!stack.isEmpty()) && (stack.peek().value<curNode.value)){
                popStackSetMap(stack, lBigmap);
            }
            stack.push(curNode);
        }
        //最后剩下的栈中的元素做一次整体的pop操作直到满足
        while (!stack.isEmpty()){
            popStackSetMap(stack,lBigmap);
        }

        for (int i = newArr.length-1; i!=-1; i--){
            Node curNode = newArr[i];
            while((!stack.isEmpty()) && (stack.peek().value<curNode.value)){
                popStackSetMap(stack, rBigmap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()){
            popStackSetMap(stack, rBigmap);
        }

        Node head = null;
        for (int i =0; i<newArr.length; i++){
            Node curNode = newArr[i];
            Node left = lBigmap.get(curNode);
            Node right = rBigmap.get(curNode);
            if (left==null && right == null){
                head = curNode;
            }else if (left==null){
                if (right.left==null){
                    right.left = curNode;
                }else{
                    right.right = curNode;
                }
            }else if (right==null){
                if (left.left==null){
                    left.left = curNode;
                }else {
                    left.right = curNode;
                }
            }else{
                Node parent = left.value<right.value ? left : right;
                if (parent.left == null){
                    parent.left = curNode;
                }else{
                    parent.right = curNode;
                }
            }
        }

        return  head;
    }

    public static void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map){
        Node popNode = stack.pop();
        if (!stack.isEmpty()){
            map.put(popNode, stack.peek());
        }else{
            map.put(popNode, null);
        }
    }


    public static void printPreOrder(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        printPreOrder(head.left);
        printPreOrder(head.right);
    }

    public static void printInOrder(Node head) {
        if (head == null) {
            return;
        }
        printPreOrder(head.left);
        System.out.print(head.value + " ");
        printPreOrder(head.right);
    }

    public static void main(String[] args) {
        int[] uniqueArr = { 3, 4, 5, 1, 2 };
        Node head = getMaxTree(uniqueArr);
        printPreOrder(head);
        System.out.println();
        printInOrder(head);

    }
}
