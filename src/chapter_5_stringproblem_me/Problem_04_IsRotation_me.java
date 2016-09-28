package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/9/28.
 * 题目: 判断两个字符串是否互为旋转词. 如果一个字符串str, 把字符串str前面任意
 *      的部分挪到后面形成的字符串叫作str的旋转词. 比如str="12345", str的
 *      旋转词有"12345","23451","34512","45123","51234".
 * 要求: 时间复杂度为O(N),其中N为字符串a和b的长度
 * 难度: *
 * 思路: 将两个b首位相接为b2,看b2是否包含a
 *
 */
public class Problem_04_IsRotation_me {
    public static boolean isRotation(String a, String b){
        if (a == null || b == null || a.length() != b.length()){
            return false;
        }
        String b2 = b + b;
        return getIndexOf(b2, a) != -1; //KMP算法
    }

    // KMP算法
    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));
    }
}
