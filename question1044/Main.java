package question1044;

import java.util.Scanner;

/**
 * Created by duncan on 17-11-16.
 */


//令values(i,S)表示前i个位置，(i-m+1)到i这些位置是否被打扫如S集合中所表示的那样时，可以打扫的最多垃圾数。
//        则 i位置被选中
//        values(i,S)=max(values(i-1,S - {i-m} + {i-m被选}),values(i-1,S - {i-m} + {i-m未选})) + array[i]
//        i位置未被选中
//        values(i,S)=max(values(i-1,S - {i-m} + {i-m被选}),values(i-1,S - {i-m} + {i-m未选}))
public class Main {
    //统计i表示为二进制时中1的个数
    public int getCount(int i) {
        int count = 0;
        while (i!=0) {
            if ((i&1) == 1)
                count++;
            i>>=1;
        }
        return count;
    }

    public int recover(int[] array,int m,int n,int q){
        int maxValue = 0;
        //一共有1<<m种可能
        int[][] values = new int[n+1][1<<m];
        for (int i=1; i<=n; i++) {
            //对于每个位置都遍历判断
            for (int j = 0; j < (1<<Math.min(i,m)); j++) {
                //超出q
                if (getCount(j) > q)
                    continue;
                //i位置未被选中
                //选择第i-m个元素
                values[i][j] = Math.max(values[i-1][j>>1], values[i-1][(j>>1) + (1<<m-1)]);
                //i位置被选中
                if ((j&1) == 1)
                    //index i is chosed
                    values[i][j] += array[i];
            }
        }
        for (int i=0; i < (1<<Math.min(m,n)); i++) {
            maxValue = Math.max(maxValue,values[n][i]);
        }
        return maxValue;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int Q = in.nextInt();
        int[] weights = new int[N+1];
        for(int i = 1; i <= N; i++)
            weights[i] = in.nextInt();
        System.out.println(solution.recover(weights,M,N,Q));
    }
}
