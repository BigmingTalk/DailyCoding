package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/10/6.
 * 题目: 给定字符串str1和str2, 求str1的子串中包含str2所有字符的最小子串长度.
 * 举例: str1 = "abcde", str2 = "ac", 因为"abc"包含str2中的所有字符,且是
 *      满足这一条件的str1的所有子串中长度最短的,所以返回3.
 *      str1 = "12345", str2 = "344".最小包含子串不存在,返回0.
 * 难度: ***
 * 思路: 用left和right来表示str1中框住的子串,先是right开始移动,满足后然后是
 *      left开始移动.思路非常好!!!
 *
 */
public class Problem_20_MinWindowLength_me {
    public static int minLength(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() < str2.length()){
            return 0;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i != chas2.length; i++){
            map[chas2[i]]++;
        }
        int left = 0;
        int right = 0;
        int mingLen = Integer.MAX_VALUE;
        int match = chas2.length;
        while (right != chas1.length){
            map[chas1[right]]--;
            if (map[chas1[right]] >= 0){
                match--;
            }
            if (match == 0){
                while (map[chas1[left]] < 0){
                    map[chas1[left++]]++;
                }
                mingLen = Math.min(mingLen, right - left + 1);
                // 当前以right结尾的是最短了,所以left向左让其不满足,看以其他结尾的.
                match++;
                map[chas1[left++]]++;
            }
            right++;
        }
        return mingLen == Integer.MAX_VALUE ? 0 : mingLen;
    }


    public static void main(String[] args) {
        String str1 = "adabbca";
        String str2 = "acb";
        System.out.println(minLength(str1, str2));

    }
}
