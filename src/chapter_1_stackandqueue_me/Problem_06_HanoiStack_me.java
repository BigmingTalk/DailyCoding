package chapter_1_stackandqueue_me;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Stack;

/**
 * Created by bigming on 16/8/16.
 *
 * 题目: 汉诺塔更改一个游戏规则,不允许从最左侧的塔直接移动到最右侧,也不能从最右侧直接移动到
 *      最左侧,而是必须经过中间,求当塔有N层的时候,打印最优移动过程和最优移动总步数.
 *
 * 要求: 用递归和非递归的方法分别实现
 * 难度: ***
 *
 * 要点: 递归的就分为最后一个的情况和其他的情况
 *      非递归的可归结为两个原则,一个是不违反小压大的原则,另一个是不违反相邻不可逆的原则,即
 *      连续两个操作不是逆关系,如移动1从中到左,又从左到中. 根据这两个原则,可以得到每一步都
 *      是唯一的,然后只要去判断哪一步就可以了.
 *
 */
public class Problem_06_HanoiStack_me {
    public static int hanoiProblem1(int num, String left, String mid, String right){
        if (num < 1){
            return 0;
        }

        return process(num, left, mid, right, left, right);
    }

    public static int process(int num, String left, String mid, String right,
                       String from, String to){
        if (num ==1){
            if (from.equals(mid)|| to.equals(mid)){
                System.out.println("Move 1 from " + from + " o " + to);
                return 1;
            }else{
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }

        if (from.equals(mid)|| to.equals(mid)){
            String another = (from.equals(left)||to.equals(left))? right:left;
            int part1 = process(num-1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " form " +from + " to " +to);
            int part3 = process(num-1, left, mid, right, another, to);
            return part1+part2+part3;
        }else{
            int part1 = process(num-1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num +" form " + from + " to " +mid);
            int part3 = process(num-1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num +" form " + mid + " to " +to);
            int part5 = process(num-1, left, mid, right, from, to);
            return  part1 +part2 +part3+part4+part5;
        }
    }

    public enum Action{
        No, LToM, MToL, RToM, MToR
    }

    public static int hanoiProblem2(int num, String left, String mid, String right){
        Stack<Integer> lS = new Stack<Integer>();
        Stack<Integer> mS = new Stack<Integer>();
        Stack<Integer> rS = new Stack<Integer>();
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        for (int i = num; i>0; i--){
            lS.push(i);
        }
        Action[] record = {Action.No};
        int step = 0;
        while(rS.size()!= num+1){
            step+= fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left,mid);
            step+= fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid,left);
            step+= fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step+= fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);

        }
        return step;
    }

    public static int fStackTotStack(Action[] record, Action preNoAct,
                                     Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack,
                                     String from, String to){
        if(record[0]!=preNoAct && fStack.peek()< tStack.peek()){
            tStack.push(fStack.pop());
            System.out.println("Move "+ tStack.peek() +" from " +from + " to "+ to);
            record[0]= nowAct;
            return 1;
        }
        return 0;
    }


    public static void main(String[] args){
        int step  = hanoiProblem1(3, "left", "mid", "right");
        System.out.println("Step:" + step);

        int step2 = hanoiProblem2(3, "left", "mid", "right");
        System.out.println("Step:" + step2);
    }
}
