package question01string;

import java.util.Scanner;

/**
 * Created by duncan on 17-8-23.
 */
//计算01串变为有序01串最少的改动次数
public class Main {
    public int Calculate(String string){
        int[] prefiex = new int[string.length()];
        int[] suffix = new int[string.length()];
        for(int i = 0; i < string.length(); i++){
            //对每个元素计算它之前1的个数和后面0的个数
            int count = 0;
            int j = 0;
            while(j < i) {
                if (string.charAt(j) == '1')
                    count++;
                j++;
            }
            prefiex[i] = count;
            count = 0;
            j = i + 1;
            while(j < string.length()) {
                if (string.charAt(j) == '0')
                    count++;
                j++;
            }
            suffix[i] = count;
        }
        //统计最小值
        int min = string.length();
        for(int i = 0; i < string.length(); i++){
            if(prefiex[i] + suffix[i] < min)
                min = prefiex[i] + suffix[i];
        }
        return min;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        for(int i = 0; i < number; i++) {
            String string = in.next();
            System.out.println(solution.Calculate(string));
        }
    }
}
