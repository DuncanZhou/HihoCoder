package SmallestSubArray;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] A = new int[len];
        for(int i = 0; i < len; i++) A[i] = in.nextInt();
        Main solution = new Main();
        System.out.println(solution.helper(A));
    }
    private int helper(int[] A){
        int[] B = Arrays.copyOf(A,A.length);
        Arrays.sort(B);
        int start = 0, end = B.length - 1,index = 0;
        while(index <= end){
            if(A[index] != B[index]) break;
            index++;
        }
        start = index;
        if(start > end) return 0;
        index = end;
        while(index > start){
            if(A[index] != B[index]) break;
            index--;
        }
        end = index;
        return end - start + 1;
    }
}
