package chapter_5_stringproblem_me;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by bigming on 16/10/4.
 * 题目: 给定一个字符串str, str表示一个公式, 公式里可能有整数, 加减乘除符号
 *      和左右括号,返回公式的计算结果.
 * 举例: str = "48*((70-65)-43)+8*1",返回-1816
 *      str = "3+1*4",返回7
 *      str = "3+(1*4)",返回7
 * 说明: 1. 可以认定给定的字符串一定是正确的公式,即不需要对str做公式有效性检查
 *      2. 如果是负数,需要用括号括起来,如4*(-3)但是负数作为公公式的开头或括号
 *         部分的开头,则可以没有括号,比如"-3*4"和(-3*4)
 *      3. 不用考虑计算过程中会发生的溢出行为.
 *
 */
public class Problem_15_ExpressionCompute_me {
    public static int getValue(String str){
        return value(str.toCharArray(), 0)[0];
    }

    // 递归计算过程, 数组第一个值是公司子串的值,第二个是继续计算的位置.
    // 每当遇到"("时递归.
    public static int[] value(char[] chars, int i){
        Deque<String> deq = new LinkedList<String>();
        int pre = 0;
        int[] bra = null;
        while (i < chars.length && chars[i] != ')'){
            if (chars[i] >= '0' && chars[i] <= '9'){
                pre = pre * 10 + chars[i++] - '0';
            } else if (chars[i] != '('){ // 一定是加减乘除运算符号
                addNum(deq, pre);
                deq.addLast(String.valueOf(chars[i++]));
                pre = 0;
            } else { // 一定是'(',开始递归计算
                bra = value(chars, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(deq, pre);
        return new int[] {getNum(deq), i};
    }

    // 乘除法就计算, 加减法滞后, 最后就是一个只有加减法的算术式给getNum方法计算
    public static void addNum(Deque<String> deq, int num){
        if (!deq.isEmpty()){
            int cur = 0;
            String top = deq.pollLast();
            if (top.equals("+") || top.equals("-")){
                deq.addLast(top);
            } else {
                cur = Integer.valueOf(deq.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        deq.addLast(String.valueOf(num));
    }

    // 计算只有加减法的算术式.
    public static int getNum(Deque<String> deq){
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0 ;
        while (!deq.isEmpty()){
            cur = deq.pollFirst();
            if (cur.equals("+")){
                add = true;
            } else if (cur.equals("-")){
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));

    }

}
