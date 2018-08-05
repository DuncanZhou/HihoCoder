package SortingPhotoFiles;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class node implements Comparable<node>{
    public String string;
    public int val;
    //构造函数
    public node(String s,int i){
        string = s;
        val = i;
    }
    @Override
    public int compareTo(node b){
        if(this.string.equals(b.string)) return this.val - b.val;
        else return this.string.compareTo(b.string);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        node[] nodes = new node[n];
        //正则匹配表达式
        Pattern p = Pattern.compile("([a-zA-Z]+)([0-9]+)");
        for(int i = 0; i < n; i++) {
            String temp = in.next();
            //匹配
            Matcher m = p.matcher(temp);
            //find()要加
           if(m.find()) {
               node t_node = new node(m.group(1), Integer.valueOf(m.group(2)));
               nodes[i] = t_node;
           }
        }
        Arrays.sort(nodes);
        for(int i = 0; i < nodes.length; i++)
            System.out.println(nodes[i].string+String.valueOf(nodes[i].val));
    }
}
