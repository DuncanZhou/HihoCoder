package FillBlanks;

import java.util.Scanner;

/**
 * Created by duncan on 18-4-18.
 */
public class Main {
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        String str = in.next();
        String[] expression = str.split("=");
        long target = Long.valueOf(expression[1]);
        String[] add = expression[0].split("\\+");
        int[] number = new int[add.length];
        int i = 0;
        for(String a:add)
            number[i++] = a.length();
        System.out.println(solution.helper(number,target,0));
    }
    //超时
    private int helper(int[] number,long target,int index){
        int res = 0;
        if(index == number.length)
            return target == 0 ? 1 : 0;
        //得到当前位数
        String s = "1",e = "9";
        for(int i = 0; i < number[index] - 1 ; i++) {
            s += "0";
            e += "9";
        }
        long start = Long.valueOf(s);
        if(start > target) return 0;
        long end = Long.valueOf(e);
        for(long i = start; i <= end && i <= target; i++){
            res += helper(number,target-i,index+1);
        }
        return res % 1000000007;
    }
}
