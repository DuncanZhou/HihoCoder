package questionPalindarome;

import java.util.Scanner;

/**
 * Created by duncan on 17-8-6.
 */

//题目要求,最少多少次将一个字符串改为回文字符串,允许改动:插入字符,删除字符,以及修改字符
    //动态规划求解
public class Main {
    public int Search(String string){
        int[][] dp = new int[100][100];
        int len = string.length();
        for(int i = 0; i < len; i++)
            dp[i][i] = 0;
        for(int step = 1; step < len; step++) {
            for (int index = 0; index + step < len; index++) {
                if (string.charAt(index) == string.charAt(index + step))
                    dp[index][index + step] = dp[index + 1][index + step - 1];
                else
                    dp[index][index + step] = Math.min(Math.min(dp[index + 1][index + step],dp[index][index + step - 1]),dp[index + 1][index + step - 1])+1;
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        String string = in.next();
        System.out.println(solution.Search(string));
    }
}
