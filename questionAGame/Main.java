package questionAGame;

import java.util.Scanner;

/**
 * Created by duncan on 17-10-26.
 */
//动态规划
    //little hi always select the optimal strategy
    //对手也是最优方案
public class Main {
    public int MaximalScore(int[] array){
        int len = array.length - 1;
        int[] sum = new int[len+1];
        int[][] dp = new int[len+1][len+1];
        for(int i = 1; i <= len; i++){
            sum[i] = sum[i - 1] + array[i];
            dp[i-1][i] = Math.max(array[i],array[i-1]);
            dp[i][i] = array[i];
        }
        for(int i = len; i >= 1; i--){
            for(int j = i + 2; j <= len; j++){
                dp[i][j] = sum[j] - sum[i -1] + (dp[i + 1][j] > dp[i][j - 1] ? -dp[i][j - 1]: -dp[i + 1][j]);
            }
        }
        return dp[1][len];
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int[] array = new int[number + 1];
        for(int i = 1 ; i <= number; i++)
            array[i] = in.nextInt();
        System.out.println(solution.MaximalScore(array));
    }
}
