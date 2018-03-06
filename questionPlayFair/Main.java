package questionPlayFair;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by duncan on 18-3-6.
 */
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String input = in.next();
        Set<Character> has = new HashSet<Character>(){{add('J');}};
        //将单词中的J替换为I
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < input.length(); i++)
        {
            if(input.charAt(i) != 'J') str.append(input.charAt(i));
            else str.append('I');
        }
        String word = str.toString();
        int index = 0,add = 0;
        char[][] alphas = new char[5][5];
        //先按照单词填,单词填完填其他字母
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(index < word.length()){
                    //继续填单词中的
                    while(index < word.length() && has.contains(word.charAt(index)))
                        index++;
                    if(index < word.length())
                    {
                        has.add(word.charAt(index));
                        alphas[i][j] = word.charAt(index);
                    }else{
                        //填其他字母
                        while(has.contains((char)('A' + add)))
                            add++;
                        alphas[i][j] = (char)('A' + add);
                        has.add((char)('A' + add));
                    }
                }else{
                    //填其他字母
                    while(has.contains((char)('A' + add)))
                        add++;
                    alphas[i][j] = (char)('A' + add);
                    has.add((char)('A' + add));
                }
            }
        }
        //输出
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(alphas[i][j]);
            }
            System.out.println();
        }
    }
}
