package chapter_1_stackandqueue_me;

import java.util.Stack;

/**
 * Created by bigming on 16/8/15.
 *
 * 题目:实现一个特殊的栈,在实现栈的基本功能之上,能够返回栈中最小元素的操作.
 * 要求: 1. pop, push, getMin操作的时间复杂度都是O(1).
 *      2. 设计的栈类型可以使用现成的栈结构.
 *难度: *
 *
 */

public class Problem_01_GetMinStack_me {
    /**
     * 第一种实现方式,主要思想是用一个stackMin的栈保存每一步的最小值,stackData与普通栈相同
     */
    public static class MyStack1{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1(){
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }


        /**
         * 注释掉的是我以前自己写的
         * @param value
         */
        public void push(int value){
            this.stackData.push(value);
            /*if (stackMin.isEmpty()){
                stackMin.push(value);
            }else if(stackMin.peek() > value){
                stackMin.push(value);
            }*/
            if (this.stackMin.isEmpty()){
                this.stackMin.push(value);
            }else if(value <= getMin()){
                this.stackMin.push(value);
            }
        }

        public int pop(){
            if (this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty");
            }
            int value = this.stackData.pop();
            /*if(value == stackMin.peek()){
                stackMin.pop();
            }*/
            if(value == this.getMin()){
                this.stackMin.pop();
            }
            return value;
        }

        public int getMin(){
            if (this.stackMin.isEmpty()){
                throw new RuntimeException("Your satck is empty");
            }
            return this.stackMin.peek();
        }

    }

    /**
     * 第二种方式的思想与第一种方式是一样的,只不过是在push和pop上稍有不同.
     */
    public static class MyStack2{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2(){
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int value){
            this.stackData.push(value);
            if (this.stackMin.isEmpty()){
                this.stackMin.push(value);
            }else if(value <= getMin()){
                this.stackMin.push(value);
            }else{
                int pNum = this.stackMin.peek();
                this.stackMin.push(pNum);
            }
        }

        public int pop(){
            if (this.stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty");
            }
            int value = this.stackData.pop();
            this.stackMin.pop();
            return value;
        }

        public int getMin(){
            if (this.stackMin.isEmpty()){
                throw new RuntimeException("Your satck is empty");
            }
            return this.stackMin.peek();
        }
    }


    public static void main(String[] args){
        MyStack1 stack1 = new MyStack1();
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(5);
        System.out.println(stack1.getMin());
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.pop();
        System.out.println(stack1.getMin());

        MyStack2 stack2 = new MyStack2();
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(5);
        System.out.println(stack2.getMin());
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.pop();
        System.out.println(stack2.getMin());
    }
}
