package chapter_1_stackandqueue_me;

import java.util.Stack;

/**
 * Created by bigming on 16/8/16.
 *
 * 题目: 一个栈中元素的类型为整型,将该栈从顶到底按从大到小的顺序排序,只许申请一个栈.
 *      除此之外,可以申请新的变量,但不能申请额外的数据结构,如何完成排序?
 * 难道: *
 *
 * 思路: 在stack上进行pop操作,弹出的元素记为cur,
 *      如果cur小于或等于help的栈顶元素,则cur直接压入help,
 *      否则,如果cur大于help的栈顶元素,则将help的元素逐个压入
 *          stack,直到help为空或cur小于等于help的栈顶元素
 *
 */
public class Problem_05_StackSortStack_me {
    public static void sortStackByStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<Integer>();
        while(!stack.isEmpty()){
            int cur = stack.pop();
            while(!help.isEmpty() && help.peek()<cur){
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(3);
        stack.push(1);
        stack.push(6);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        sortStackByStack(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
