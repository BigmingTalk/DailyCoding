package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/9/29.
 * 题目: 替换字符串中连续出现的指定字符串. 给定三个字符串str, from和to,
 *      把str中所有from的子串全部替换成to字符串,对连续出现from的部分要求
 *      只替换成一个to字符串,返回最终的结果字符串.
 * 举例: str = "123abcabc",from="abc",to="X",返回"123X"
 * 难度: *
 * 思路: 只需要把符合的位置字符编码设为0(即空字符)
 *
 */
public class Problem_06_ReplaceString_me {
    public static String replace(String str, String from, String to){
        if (str == null || from == null || str.equals("") || from.equals("")){
            return str;
        }
        char[] chas = str.toCharArray();
        char[] chaf = from.toCharArray();
        int match = 0;
        for (int i = 0; i < chas.length; i++){
            if (chas[i] == chaf[match++]){
                if (match == chaf.length){
                    clear(chas, i, chaf.length);
                    match = 0;
                }
            } else {
                match = 0;
            }
        }
        String res = "";
        String cur = "";
        for (int i = 0; i < chas.length; i++){
            if (chas[i] != 0){
                cur = cur + String.valueOf(chas[i]);
            }
            if (chas[i] == 0 && (i == 0 || chas[i - 1] != 0)){
                res = res + cur + to;
                cur ="";
            }
        }
        if (!cur.equals("")){
            res = res + cur;
        }
        return res;
    }

    public static void clear(char[] chas, int end, int len){
        while (len-- != 0){
            chas[end--] = 0;
        }
    }


    public static void main(String[] args) {
        String str = "abc1abcabc1234abcabcabc5678";
        String from = "abc";
        String to = "XXXXX";
        System.out.println(replace(str, from, to));

        str = "abc";
        from = "123";
        to = "X";
        System.out.println(replace(str, from, to));
    }

}
