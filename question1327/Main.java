package question1327;

import java.util.Scanner;

/**
 * Created by duncan on 18-1-30.
 */
public class Main {
    public boolean valid(int[] cnt,int x){
        for(int i = 0; i < 26; i++)
            if(cnt[i] > (x-1)/2+1)
                return false;
        return true;
    }
    public static void main(String[] args) {
        Main solution =  new Main();
        //当某个字符在字符串中出现了超过一半次数则便是失败的
        Scanner in = new Scanner(System.in);
        //贪心算法
        int[] cnt = new int[26];
        String str = in.next();
        for(int i = 0; i < str.length(); i++)
            cnt[str.charAt(i)-'a']++;
        if(!solution.valid(cnt,str.length())){
            System.out.println("INVALID");
            return;
        }
        int pre = -1,n = str.length();
        String res = "";
        while(res.length() < str.length())
        {
            for(int j = 0; j < 26; j++){
                if(cnt[j] != 0 && j != pre){
                    cnt[j]--;
                    if(solution.valid(cnt,n-1)){
                        res += (char)('a' + j);
                        pre = j;
                        n--;
                        break;
                    }
                    //不能则还原
                    else cnt[j]++;
                }
            }
        }
        System.out.println(res);
    }
}
