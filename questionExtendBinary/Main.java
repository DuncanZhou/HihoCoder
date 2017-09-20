package questionExtendBinary;

import java.util.Scanner;

/**
 * Created by duncan on 17-9-20.
 */

//扩展的二进制,动态规划解题
    //d[N,len] = d[N-1 / 2, len -1] + d[N, len -1] + d[N- 2, len - 1](if i mod 2 != 0, d[i,j]=0)
public class Main {
    //exceed time
    //the max length of N
//    public int MaxLen(int N){
//        int i = 1;
//        while(Math.pow(2,i) < N)
//            i ++;
//        return i + 1;
//    }
//    public static void main(String[] args) {
//        Main solution = new Main();
//        Scanner in = new Scanner(System.in);
//        int N = in.nextInt();
//        int length = solution.MaxLen(N);
//        if(N == 1 || N ==0) {
//            System.out.println(1);
//            return;
//        }
//        //initialize the array
//        int[][] d = new int[N + 2][length + 1];
//        //finish the array(row start from 0,col start from 1)
//        d[1][1] = d[2][1] = 1;
//        for(int col = 1; col <= length; col++) {
//            d[0][col] = 1;
//            d[1][col] = 1;
//        }
//        for(int row = 2; row <= N; row++) {
//            for (int col = 2; col <= length; col++) {
//                if (row % 2 != 0)
//                    d[row][col] = d[(row - 1) / 2][col - 1];
//                else
//                    d[row][col] = d[row / 2][col - 1] + d[(row - 2) / 2][col - 1];
//            }
//        }
//        System.out.println(d[N][length]);
//    }

    //best <code>
    public int cnt(int n){
        if(n ==0 || n == 1)
            return 1;
        if(n % 2 != 0) return cnt((n -1) /2);
        else return cnt(n / 2) + cnt(n/2 - 1);
    }

    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        System.out.println(solution.cnt(N));
    }
}
