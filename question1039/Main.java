package question1039;

import java.util.Scanner;

/**
 * Created by duncan on 17-5-25.
 */

////字符消除
//public class Main {
//    public String process(String string){
//        //先把相同的字符去除
//        StringBuilder tempstr = new StringBuilder(string);
//        int i = 0;
//        while (i < tempstr.length() - 1) {
//            if (tempstr.charAt(i) == tempstr.charAt(i + 1)) {
//                char tempchar = tempstr.charAt(i);
//                int j = i + 1;
//                while (j < tempstr.length() && tempstr.charAt(j) == tempchar) {
//                    j++;
//                }
//                //删除相同
//                tempstr.delete(i, j);
//                continue;
//            }
//            i++;
//        }
//        return tempstr.toString();
//    }
//    public int maxChars(String string,int current){
//        boolean flag = false;
//        //判断相邻的是否有相同的
//        for(int i = 0; i < string.length() - 1; i++){
//            if(string.charAt(i) == string.charAt(i + 1)) {
//                flag = true;
//                break;
//            }
//        }
//        if(flag == false)
//            return current;
//            //先去除相同的字符
//            //找连续相同的字符个数
//            String newstring = process(string);
//            //循环之后再把剩余的字符串判断
//            return maxChars(newstring,current + string.length() - newstring.length());
//    }
//    public static void main(String[] args){
//        Main solution = new Main();
//        Scanner in = new Scanner(System.in);
//        int caseN = in.nextInt();
//        int[] results = new int[caseN];
////        String string = "BAAABAABBCCB";
////        System.out.println(solution.process(string));
//        for(int i = 0; i < caseN ; i++)
//        {
//            String string = in.next();
//
//            //先预处理一遍
//            String sestring = solution.process(string);
//            int result = string.length() - sestring.length();
//            //如果为空则result +　1
//            //寻找字符串中最长对称段;没有则result +１
//            boolean flag = false;
//            StringBuilder insertstring = new StringBuilder(sestring);
//            int maxlen = 3,optimalpos;
//            int[] position = new int[sestring.length()];
//            int s = 0;
//            for(int k = 0; k < sestring.length() - 2; k++){
//                //先找到XOX这样的模式串
//                if(sestring.charAt(k) == sestring.charAt(k + 2)) {
//                    flag = true;
//                    position[s] = k + 1;
//                    s++;
//                }
//            }
//            if(flag == false) {
//                //为空
//                if (insertstring.length() == 0)
//                    results[i] = result + 1;
//                //加一个消除两个
//                else {
//                    int hasremove = solution.maxChars(insertstring.toString(),result);
//                    if(string.length() - hasremove == 0)
//                        results[i] = hasremove + 1;
//                    else
//                        results[i] = hasremove + 2;
//                }
//            }
//            else {
//                //寻找插入点最佳的位置
//                optimalpos = position[0];
//                for(int k = 0; k < position.length; k++){
//                    int templen = 3;
//                    int left = position[k] - 1;
//                    int right = position[k] + 1;
//                    while(left > 0 && right < sestring.length()){
//                        if(sestring.charAt(left) == sestring.charAt(right)) {
//                            templen += 2;
//                            left--;
//                            right++;
//                        }
//                        else
//                            break;
//                    }
//                    if(templen > maxlen){
//                        maxlen = templen;
//                        optimalpos = position[k];
//                    }
//                }
//                insertstring.insert(optimalpos,sestring.charAt(optimalpos));
//                results[i] = (solution.maxChars(insertstring.toString(), result));
//            }
//        }
//        for(int i = 0; i < caseN; i++)
//            System.out.println(results[i]);
//    }
//}

public class Main{
    int stringLength;
    int max;

    public Main(String s) {
        stringLength = s.length();
        max = 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 0; i < n; i++){
            String s = in.next();

            Main ce = new Main(s);
            ce.travel(s);

            System.out.println(ce.max);
        }
    }

    public void travel(String s){
        int length = s.length();
        StringBuffer sb = new StringBuffer(s);
        for(int i = 0; i <= length; i++){
            //暴力搜索
            sb.insert(i, 'A');
            f(sb.toString());
            sb.setCharAt(i, 'B');
            f(sb.toString());
            sb.setCharAt(i, 'C');
            f(sb.toString());
            sb.deleteCharAt(i);
        }
    }

    //遍历一次消除相同的字符串，所得的新串再次递归直到字符长度不再改变
    public void f(String s){
        int i = 0;
        int j = 1;
        int length = s.length();
        StringBuffer sb = new StringBuffer();
        while(j < length){
            if(s.charAt(i)!=s.charAt(j)){
                sb.append(s.charAt(i));
            } else {
                while(j < length && s.charAt(i)==s.charAt(j)){
                    j++;
                }
            }
            i = j;
            j++;
        }
        if(j == length){
            sb.append(s.charAt(i));
        }

        //递归出口,不再可以删除
        if(length == sb.toString().length()){
            int temp = stringLength - length + 1;
            if(temp > max){
                max = temp;
            }
            return;
        }
        f(sb.toString());
    }
}