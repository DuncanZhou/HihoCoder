package offer53_02;

import java.util.Scanner;

/**
 * Created by duncan on 18-4-1.
 */
public class Main {
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int examples = in.nextInt();
        for(int i = 0; i < examples; i++){
            long k = in.nextLong();
            System.out.println(solution.helper(100,k));
        }
    }
    private char helper(int N,long k){
        //求第N代,第k个字符
        if(k == 1) return 'h';
        else if(k == 2) return 'i';
        else{
            long old = k;
            if(k % 2 == 0) k = k / 2;
            else k = k / 2 + 1;
            if(helper(N-1,k) == 'h'){
                if( old % 2 == 0)
                    return 'i';
                else return 'h';
            }
            else if(helper(N-1,k) == 'o'){
                if( old % 2 == 0)
                    return 'h';
                else return 'o';
            }
            else{
                if( old % 2 == 0)
                    return 'o';
                else return 'i';
            }
        }
    }
}
