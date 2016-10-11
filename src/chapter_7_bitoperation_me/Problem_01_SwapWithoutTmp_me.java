package chapter_7_bitoperation_me;

/**
 * Created by bigming on 16/10/11.
 * 题目: 用位运算交换两个数的值
 * 难度: *
 * 思路: 用异或运算即可.在java中因为两个变量的地址不同,不会有问题.
 *      但是在C++中,如果交换函数传入的两个变量的地址相同,就会出错.
 */
public class Problem_01_SwapWithoutTmp_me {

    public static void main(String[] args) {
        int a = 189;
        int b = 123;
        System.out.println(a);
        System.out.println(b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }

}
