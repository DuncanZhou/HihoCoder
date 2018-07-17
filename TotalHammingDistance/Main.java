package TotalHammingDistance;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in  = new Scanner(System.in);
        int N = in.nextInt();
        int[] nums = new int[N];
        for(int i = 0; i < N; i++)
            nums[i] = in.nextInt();
        System.out.println(solution.helper(nums));
    }
    private int helper(int[] nums){
        int bit = 1,res = 0,count = 0;
        //每一位去与运算
        while(count < 32){
            int one = 0,zero = 0;
            for(int x : nums){
                if((bit & x) == 0) zero++;
                else one++;
            }
            res += one * zero;
            bit <<= 1;
            count ++;
        }
        return res;
    }
}
