package chapter_5_stringproblem_me;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by bigming on 16/10/5.
 * 题目: 给定一个字符串类型的数组strs, 请找到一种拼接顺序,使得将所有的字符串拼接
 *      起来组成的大写字符串是所有可能性中字典顺序最小的,并返回这个大写字符串.
 * 举例: strs = ["b", "ba"], 可以拼接为"bba",也可以是"bab",后者字典顺序更小,
 *      所以返回"bab"
 * 难度: ***
 * 思路: 这里不能简单地按字典排序来排序后拼接,因为举例中的例子即为反例. 所以比较两个
 *      字符串A和B的顺序是看A.B和B.A的大小关系,A.B代表字符串A和B连接起来.
 *      证明: 一方面是传递性的证明,另一方面是证明互换两个字符串后字典序会变大.
 *
 */
public class Problem_17_LowestLexicography_me {
    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String a, String b){
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString(String[] strs){
        if (strs == null || strs.length == 0){
            return "";
        }
        // 根据新的比较费时排序
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++){
            res += strs[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));

    }
}
