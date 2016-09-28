package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/9/28.
 * 题目: 给定一个字符串str,求其中全部数字串所代表的数字之和
 * 要求: 忽略小数点字符,如"A1.3"包括两个数字1和3,负负得正如"A-1BC--12"
 *      代表的是数字-1和12.
 * 难度: *
 * 思路: 用res表示目前累加和,num表示当前收集到的数字,布尔型变量posi表示正负
 *
 */
public class Problem_02_AllNumbersSum_me {
    public static int numSum(String str){
        if (str == null){
            return 0;
        }
        char[] charArr = str.toCharArray();
        int res =0;
        int num =0;
        boolean posi = true;
        int cur =0;
        for (int i = 0; i < charArr.length; i++){
            cur = charArr[i] - '0';
            if (cur < 0 || cur > 9){
                res += num;
                num = 0;
                if (charArr[i] == '-'){
                    // i - 1来保证第一个字符为-时的情况
                    if (i - 1 > -1 && charArr[i-1] == '-'){
                        posi = !posi;
                    } else {
                        posi = false;
                    }
                } else {
                    posi = true;
                }
            } else {
                num += num * 10 + (posi ? cur : -cur);
            }
        }
        // 因为是以非数字结尾做加法的,所以如果最后的几个字符是数字,
        // 则要把结果加上.
        res += num;
        return res;
    }

    public static void main(String[] args) {
        String test = "1K-100ABC500D-T--100F200G!!100H---300";
        System.out.println(numSum(test));

    }
}
