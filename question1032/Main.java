package question1032;

import java.util.Scanner;

/**
 * Created by duncan on 17-9-27.
 */
//计算最长回文字符串
    //Manancher算法
public class Main {
    public int Manancher(String string){
        int[] len = new int[string.length()];
        int mx = 0, po = 0, result = 0;
        len[0] = 1;
        for(int i = 1; i < string.length(); i++){
            if(i < mx)
                len[i] = Math.min(mx - i,len[2 * po - i]);
            else
                len[i] = 1;
            while(i + len[i] < string.length() && string.charAt(i - len[i]) == string.charAt(i + len[i]))
                len[i] ++;
            if(len[i] + i > mx){
                //update po and mx
                mx = len[i] + i;
                po = i;
            }
            result = Math.max(len[i],result);
        }
//        for(int i = 0; i < len.length; i++)
//            System.out.print(len[i] + " ");
//        System.out.println();
        return result - 1;
    }
    private String Prepare(String string){
        //在字符串开头添加'@',在字符串结尾添加'$',其他中间填充'#'
        StringBuilder newstring = new StringBuilder();
        newstring.append('@');
        newstring.append('#');
        for(int i = 0; i < string.length(); i++) {
            newstring.append(string.charAt(i));
            newstring.append('#');
        }
        newstring.append('$');
//        System.out.println(newstring.toString());
        return newstring.toString();
    }

    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 0; i < n; i++) {
            String str = in.next();
            System.out.println(solution.Manancher(solution.Prepare(str)));
        }
//        System.out.println(solution.Manancher(solution.Prepare("aaaba")));
    }
}
