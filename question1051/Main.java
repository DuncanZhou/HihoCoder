package question1051;


import java.util.Scanner;

/**
 * Created by duncan on 17-11-16.
 */
public class Main {
    public int solve(int[] abs,int M,int N){
        int len = N;
        if(M >= len)
            return 100;
        //M张一定要连续填充
        int max = 0;
        for(int i = M + 1; i <= len; i++)
        {
            int temp = abs[i] - abs[i - M - 1] - 1;
            if(temp > max)
                max = temp;
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main solution = new Main();
        int examples = in.nextInt(),count = 0;
        while (count < examples) {
            int N = in.nextInt();
            int M = in.nextInt();
            int[] abs = new int[N + 2];
            abs[0] = 0;
            abs[N + 1] = 100;
            for (int i = 1; i <= N; i++)
                abs[i] = in.nextInt();
            System.out.println(solution.solve(abs, M, N));
            count++;
        }
    }
}
