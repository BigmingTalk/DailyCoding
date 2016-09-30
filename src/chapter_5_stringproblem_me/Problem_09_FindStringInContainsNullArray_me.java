package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/9/30.
 * 题目: 在有序但含有空的数组中查找字符串. 字符串数组strs[]中有些位置为null,
 *      在不为null的位置上,其字符串是按照字典顺序由小到大依次出现的. 再给定一
 *      个字符串str, 请返回str在strs中出现的最左的位置.
 * 难度: *
 * 思路: 二分查找即可,注意null的情况
 *
 */
public class Problem_09_FindStringInContainsNullArray_me {
    public static int getIndex(String[] strs, String str){
        if (strs == null || strs.length == 0 || str == null){
            return  -1;
        }
        int res = -1;
        int left = 0;
        int right = strs.length - 1;
        int mid = 0;
        int i = 0;
        while (left <= right){
            mid = (left + right) / 2;
            if (strs[mid] != null && strs[mid].equals(str)){
                res = mid;
                right = mid - 1;
            } else if (strs[mid] != null){
                if (strs[mid].compareTo(str) < 0){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                i = mid;
                while (strs[i] == null && --i >= left)
                    ;
                // 找到头了都是null或者还是比str小
                if (i < left || strs[i].compareTo(str) < 0){
                    left = mid + 1;
                } else {
                    res = strs[i].equals(str) ? i : res;
                    right = i - 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = new String[] { null, "a", null, "a", null, "b", null,
                null, null, "b", null, "c", null, "c", null, null, "d", null,
                null, null, null, null, "d", null, "e", null, null, "e", null,
                null, null, "f", null, "f", null };
        String str1 = "a";
        System.out.println(getIndex(strs, str1));
        String str2 = "b";
        System.out.println(getIndex(strs, str2));
        String str3 = "c";
        System.out.println(getIndex(strs, str3));
        String str4 = "d";
        System.out.println(getIndex(strs, str4));
        String str5 = "e";
        System.out.println(getIndex(strs, str5));
        String str6 = "f";
        System.out.println(getIndex(strs, str6));

    }
}
