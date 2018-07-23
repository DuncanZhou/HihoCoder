package TargetSum;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main solution = new Main();
        int N = in.nextInt();
        int[] A = new int[N];
        int target = in.nextInt();
        for(int i = 0; i < N; i++)
            A[i] = in.nextInt();
        System.out.println(solution.helper(A,target,0,0));
    }
    private int helper(int[] A,int target,int index,int cur){
        if(index == A.length){
            if(cur == target)
                return 1;
            return 0;
        }
        return helper(A,target,index+1,cur+A[index]) + helper(A,target,index+1,cur-A[index]);
    }
}
