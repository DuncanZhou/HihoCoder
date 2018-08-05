package SortingPhotoFiles;

import java.util.Arrays;
import java.util.Scanner;


class node implements Comparable<node>{
    public String string;
    public int val;
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
//        Pattern p = Pattern.compile("(a-zA-Z)(0-9)");
        for(int i = 0; i < n; i++) {
            String temp = in.next();
//            Matcher m = p.matcher(temp);
//            String[] substrings = temp.split("(a-zA-Z)(0-9)");
            String t_s = temp.replaceAll("[^(a-zA-Z)]","");
            int t_v = Integer.valueOf(temp.replaceAll("[^(0-9)]",""));
            node t_node = new node(t_s,t_v);
            nodes[i] = t_node;
        }
        Arrays.sort(nodes);
        for(int i = 0; i < nodes.length; i++)
            System.out.println(nodes[i].string+String.valueOf(nodes[i].val));
    }
}
