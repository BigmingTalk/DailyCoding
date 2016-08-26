package chapter_1_stackandqueue_me;

import java.util.Stack;

/**
 * Created by bigming on 16/8/15.
 *
 * 题目:编写一个类,用两个栈实现队列,支持队列的基本操作(add, poll, peek)
 * 解答:栈是先进后出,队列是先进先出,两个栈刚好实现队列
 * 要点: 一个栈作为压入栈,一个栈作为弹出栈, 如果stackPush要往stackPop压入数据,
 *      则必须一次性全部压入,且只有在stackPop为空时才能压入
 *难度: **
 *
 */
public class Problem_02_TwoStacksImplementQueue_me {
    public static class TwoStackQueue{
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStackQueue(){
            this.stackPush = new Stack<Integer>();
            this.stackPop = new Stack<Integer>();
        }


        public void add(int value){
            stackPush.push(value);
        }

        /**
         * 只有当stackPop为空时才能将stackPush中的元素
         * 放入stackPop,且必须一次性全部放入
         * @return
         */
        public int poll(){
            if(stackPush.isEmpty() && stackPop.isEmpty()){
                throw new RuntimeException("Your queue is empty!");
            }

            if (!stackPop.isEmpty()){
                return stackPop.pop();
            }

            while(!stackPush.isEmpty()){
                stackPop.push(stackPush.pop());
            }

            return stackPop.pop();
        }

        public int peek(){
            if(stackPush.isEmpty() && stackPop.isEmpty()){
                throw new RuntimeException("Your queue is empty!");
            }

            if (!stackPop.isEmpty()){
                return stackPop.peek();
            }

            while(!stackPush.isEmpty()){
                stackPop.push(stackPush.pop());
            }

            return stackPop.peek();
        }

    }

    public static void main(String[] args){
        TwoStackQueue test = new TwoStackQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.poll());
    }

}
