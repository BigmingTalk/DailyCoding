package chapter_5_stringproblem_me;

/**
 * Created by bigming on 16/10/5.
 * 题目: 找到被指的新类型字符,新类型字符有以下三种:
 *      1. 小写字母, 如"e"
 *      2. 大写字符+小写字符, 如"Ab"
 *      3. 大写字母+大写字母, 如"BB"
 * 举例: str = "aaABCDEcBCg"
 *      1. k = 7时, 返回"Ec"
 *      2. k = 4时, 返回"CD"
 *      3. k = 10时, 返回"g"
 * 难度: *
 * 思路: 一种思路是str[0]开始,到k位置就能够知道是什么新类型字符了
 *      简单的方法是从k-1位置开始,向左看连续出现的大写字符的个数,然后由其实奇数
 *      还是偶数就能判断出具体的新类型字符了.这里之所以看大写字符连续出现的个数,
 *      是因为大写字母后面必须跟着一个字符,而小写字符是可以单独出现的.
 *
 */
public class Problem_19_FindNewTypeChar_me {
    public static String pointNewchar(String s, int k){
        if (s == null || s.equals("") || k < 0 || k >= s.length()){
            return "";
        }
        char[] chas = s.toCharArray();
        int uNum = 0;
        for (int i = k - 1; i >= 0; i--){
            if (!isUpper(chas[i])){
                break;
            }
            uNum++;
        }
        if ((uNum & 1) == 1){
            return s.substring(k - 1, k + 1);
        }
        if (isUpper(chas[k])){
            return s.substring(k, k + 2);
        }
        return String.valueOf(chas[k]);
    }

    public static boolean isUpper(char ch) {
        return !(ch < 'A' || ch > 'Z');
    }

    public static void main(String[] args) {
        String str = "aaABCDEcBCg";
        System.out.println(pointNewchar(str, 7));
        System.out.println(pointNewchar(str, 4));
        System.out.println(pointNewchar(str, 10));

    }
}
