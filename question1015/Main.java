package question1015;

import java.util.Scanner;

/**
 * Created by duncan on 17-7-27.
 */
public class Main {
    //求解Next数组
    public int[] getNext(String b)
    {
        int len=b.length();
        int j=0;
        int next[]=new int[len+1];//next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始
        next[0]=next[1]=0;
        for(int i=1;i<len;i++)//i表示字符串的下标，从0开始
        {
            //j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置
            while(j>0 && b.charAt(i)!=b.charAt(j))
                j=next[j];
            if(b.charAt(i)==b.charAt(j))
                j++;
            next[i+1]=j;
        }
        return next;
    }
    //字符串匹配算法,统计出现了多少次
    public int Search(String original,String find,int next[]){
        int j = 0;
        int count = 0;
        for (int i = 0; i < original.length(); i++) {
            while (j > 0 && original.charAt(i) != find.charAt(j))
                j = next[j];
            if (original.charAt(i) == find.charAt(j))
                j++;
            if (j == find.length()) {
//                System.out.println("find at position " + (i - j + 1));
//                System.out.println(original.subSequence(i - j + 1, i + 1));
                count++;
                j = next[j];
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for(int i = 0; i < num; i++){
            String find = in.next();
            String original = in.next();
            int[] Next = solution.getNext(find);
            System.out.println(solution.Search(original,find,Next));
        }
    }
}
