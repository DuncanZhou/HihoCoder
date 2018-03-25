package offer52_01;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by duncan on 18-3-25.
 */
public class Main {
    //定义一个字母对应的hashmap,再定一个优先队列,定义优先队列中的比较方法
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String alphas = in.next();
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i < alphas.length(); i++)
            map.put(alphas.charAt(i),26-i);
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //比较o1,o2每位字母大小
                for(int i = 0; i < o1.length() && i < o2.length(); i++){
                    if(map.get(o1.charAt(i)) > map.get(o2.charAt(i))) return -1;
                    else if(map.get(o1.charAt(i)) < map.get(o2.charAt(i))) return 1;
                    else continue;
                }
                if(o1.length() == o2.length()) return 0;
                else if(o1.length() > o2.length()) return -1;
                else return 1;
            }
        });
        //输入所有的字符串
        for(int i = 0; i < n; i++)
            queue.add(in.next());
        //对queue遍历输出
        while(!queue.isEmpty())
            System.out.println(queue.poll());
    }
}
