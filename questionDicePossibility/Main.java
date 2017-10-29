package questionDicePossibility;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by duncan on 17-10-29.
 */

//d[i][j]表示扔i个骰子点数和为j的概率。d[i][j]+=d[i-1][j-k]*(1/6) (1<=k<=6)
public class Main {
    //抛置N次色子,之和为M的概率
    public double Possibility(int N,int M){
        double[][] dp = new double[N + 1][M + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= N; i++){
            for(int k = 1; k <= 6; k++){
                for(int j = M; j >= k; j--)
                    dp[i][j] += dp[i - 1][j - k] / 6.0;
            }
        }
        return dp[N][M] * 100;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        double possibility = solution.Possibility(N ,M);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(possibility));
    }
}
