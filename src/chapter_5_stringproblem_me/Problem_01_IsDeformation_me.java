package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/9/28.
 * 题目: 判断两个字符串是否互为变形词,所谓变形词就是str1和str2中出现的字符
 *      种类一样且每种字符出现的次数也一样.
 * 难度: *
 * 思路: 可以用哈希表,因为是字符,所以直接用一个256大小的int数组来记录数量也可以.
 *
 */
public class Problem_01_IsDeformation_me {
    public static boolean isDeformation(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() != str2.length()){
            return false;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < chas1.length; i++){
            map[chas1[i]]++;
        }

        //长度相等,所以如果str1在哪一个字符多了,str1也一定会在哪一个字符少了
        for (int i = 0; i < chas2.length; i++){
            if (map[chas2[i]]-- == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String A = "abcabcabc";
        String B = "bcacbaacb";
        System.out.println(isDeformation(A, B));

    }
}
