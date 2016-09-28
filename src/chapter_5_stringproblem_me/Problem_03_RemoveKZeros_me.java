package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/9/28.
 * 题目: 去掉字符串中连续出现k个0的子串,如str="A0000B000",K=3,则返回
 *      "A0000B"
 * 难度: *
 *
 */
public class Problem_03_RemoveKZeros_me {
    public static String removeKZeros(String str, int k){
        if (str == null || k < 1){
            return str;
        }
        char[] chars = str.toCharArray();
        int count = 0, start = -1;
        for (int i = 0; i != chars.length; i++) {
            if (chars[i] == '0') {
                count++;
                start = start == -1 ? i : start;
            } else {
                if (count == k){
                    while (count-- !=0){
                        chars[start++] = '\0';
                    }
                }
                count = 0;
                start = -1;
            }
        }
        if (count == k){
            while (count-- != 0){
                chars[start++] = '\0';
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String test1 = "0A0B0C00D0";
        System.out.println(removeKZeros(test1, 1));

        String test2 = "00A00B0C00D0";
        System.out.println(removeKZeros(test2, 2));

        String test3 = "000A00B000C0D00";
        System.out.println(removeKZeros(test3, 3));

        String test4 = "0000A0000B00C000D0000";
        System.out.println(removeKZeros(test4, 4));

        String test5 = "00000000";
        System.out.println(removeKZeros(test5, 5));

    }
}
