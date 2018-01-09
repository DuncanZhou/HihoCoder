package questionDiscount;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by duncan on 18-1-9.
 */
public class Main {
    public int solve(int[] price,int discount){
        int sum = 0;
        int len = price.length;
        for(int i = 0; i < len; i++) {
            sum += price[i];
            if(price[i] == discount)
                return price[i];
        }
        if(sum < discount)
            return -1;
        Arrays.sort(price);
        int[][] dp = new int[len][len];
        int res = sum;
        //动态规划求解,找到区间和与discount最接近的
        //初始化dp
        for(int i = 0; i < len; i++) {
            dp[i][i] = price[i];
            if(dp[i][i] - discount >= 0 && dp[i][i] < res)
                res = dp[i][i];
        }
        for(int i = 0; i < len; i++){
            for(int step = 1; i + step < len; step++)
            {
                int j = i + step;
                dp[i][j] = dp[i][j-1] + price[j];
                if(dp[i][j] - discount >= 0 && dp[i][j] < res){
                    res = dp[i][j];
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        Main method = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int discount = in.nextInt();
        int[] price = new int[N];
        for(int i = 0; i < N; i++)
            price[i] = in.nextInt();
        System.out.println(method.solve(price,discount));
    }
}
