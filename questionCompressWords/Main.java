package questionCompressWords;

import java.util.Scanner;

/**
 * Created by duncan on 17-7-26.
 */

//该题目是一道典型的动态规划题目
    //动态规划方程为dp[i][j] = min{j-i+1,dp[i][k] + 2 + (j-i)/(k-i)的位数,dp[i][k]+dp[k+1][j]}
public class Main {
    //计算位数
    public int CalcDigit(int num){
        String str = Integer.toString(num);
        return str.length();
    }
    //计算重复字符串
    public int CalcRepeat(String str,int period,int start,int end){
        String newString = str.substring(start,end);
        //period为重复的周期
        for(int i = 0; i < newString.length(); i++)
            if(newString.charAt(i) != newString.charAt(i % period))
                return -1;
        return newString.length() / period;
    }
    //计算最短长度表示
    public int CalcMinLength(String str){
        //初始化矩阵
        int[][] dp = new int[str.length()][str.length()];
        for(int i = 0 ; i < str.length(); i++){
            for(int j = i; j < str.length();j++){
                dp[i][j] = j - i + 1;
            }
        }
        //开始求解
        //步长为stride,从1开始
        for(int stride = 1;stride < str.length(); stride++){
            for(int i = 0; i + stride < str.length(); i++){
                for(int k = 0; k < stride; k++){
                    dp[i][i+stride] = Math.min(dp[i][i+stride],dp[i][i + k]+dp[i+k+1][i+stride]);
                    //判断是否有重复的字符串
                    int cnt = CalcRepeat(str,k + 1,i,i+stride+1);
                    if(cnt > 0){
                        dp[i][i + stride] = Math.min(dp[i][i+stride],dp[i][i + k] + 2 + CalcDigit(cnt));
                    }
                }
            }
        }
        return dp[0][str.length()-1];
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        for(int i = 0; i < count; i++){
            String str = in.next();
            System.out.println(solution.CalcMinLength(str));
        }
    }
}
