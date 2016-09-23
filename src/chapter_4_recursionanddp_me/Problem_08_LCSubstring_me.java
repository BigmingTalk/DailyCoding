package chapter_4_recursionanddp_me;

/**
 * Created by bigming on 16/9/23.
 * 题目: 求两个字符串str1和str2的最长公共子串
 * 难度: ***
 * 要求: 额外空间复杂度为O(1).
 * 思路: 额外空间复杂度为O(M*N)容易实现,dp[i][j]代表以str1[i]和str2[j]结尾的
 *      的情况下公共子串最长能有多长.之所以能使其额外空间复杂度为O(1),是因为其迭代
 *      只要依据其左上角的值.
 *
 */
public class Problem_08_LCSubstring_me {
    public static int[][] getdp(char[] str1, char[] str2){
        int[][] dp = new int[str1.length][str2.length];
        for (int i =0; i < str1.length; i++){
            if (str1[i] == str2[0]){
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < str2.length; j++){
            if (str1[0] == str2[j]){
                dp[0][j] = 1;
            }
        }
        for (int i =1; i < str1.length; i++){
            for (int j =1; j< str2.length; j++){
                if (str1[i] == str2[j]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
            }
        }
        return dp;
    }

    public static String lcst1(String str1, String str2){
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "";
        }
        char[] chrs1 = str1.toCharArray();
        char[] chrs2 = str2.toCharArray();
        int[][] dp = getdp(chrs1, chrs2);
        int end = 0;
        int max = 0;
        for (int i =0; i < chrs1.length; i++){
            for (int j =0; j < chrs2.length; j++){
                if (dp[i][j] > max){
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end-max +1, end+1);
    }

    public static String lcst2(String str1, String str2){
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")){
            return "";
        }
        char[] chrs1 = str1.toCharArray();
        char[] chrs2 = str2.toCharArray();

        int row = 0; //斜线开始位置的行
        int col = chrs2.length -1; //斜线位置开始的列
        int max = 0; // 记录最大长度
        int end =0; // 最大长度更新时, 记录子串的结尾位置
        while (row < chrs1.length){
            int i = row;
            int j = col;
            int len =0;
            // 从(i, j)开始向右下方遍历
            while (i< chrs1.length && j < chrs2.length){
                if (chrs1[i] != chrs2[j]){
                    len=0;
                } else {
                    len ++;
                }
                // 记录最大值,以及结束字符的位置
                if (len > max){
                    end =i;
                    max = len;
                }
                i++;
                j++;
            }
            if (col > 0){ //斜线开始位置的列先向左移动
                col--;
            } else{ // 列移动到最左之后,行向下移动
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }


    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
        System.out.println(lcst1(str1, str2));
        System.out.println(lcst2(str1, str2));

    }

}
