package chapter_1_stackandqueue_me;

import java.util.Stack;

/**
 * Created by bigming on 16/8/15.
 *
 * 题目: 一个栈依次压入1,2,3,4,5,那么从栈顶到栈底分别为5,4,3,2,1.将这个栈转置后,
 *      从栈顶到栈底为1,2,3,4,5,也就是1,2,3,4,5,也就是实现栈中元素的逆序,但是只能
 *      用递归函数来实现,不能用其他数据结构
 * 难度: **
 *
 */
public class Problem_03_ReverseStackUsingRecursive_me {

    /**
     * 首先用一个递归函数实现得到一个栈的栈底元素并删除
     * @param stack
     * @return
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else{
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    /**
     * 不断得到栈底元素然后重新push就可以得到倒序的栈
     * @param stack
     */
    public static void reverseStack(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        //int value = stack.pop();
        int value = getAndRemoveLastElement(stack);
        reverseStack(stack);
        stack.push(value);
    }

    public static void main(String[] args){
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        reverseStack(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
