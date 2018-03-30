package Package01;

import java.util.Scanner;

/**
 * Created by duncan on 18-3-30.
 */
public class Main {
    //01背包问题
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //N个物品
        int N = in.nextInt();
        //M张兑换券
        int M = in.nextInt();
        int[] values = new int[N+1];
        int[] cost = new int[N+1];
        for(int i = 1; i <= N; i++){
            cost[i] = in.nextInt();
            values[i] = in.nextInt();
        }
        //N件物品,M张券
        int[][] dp = new int[N+1][M+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(j >= cost[i]) dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-cost[i]] + values[i]);
                else dp[i][j] = dp[i-1][j];
            }
        }
        System.out.println(dp[N][M]);
    }
}
