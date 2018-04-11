package hiho197;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by duncan on 18-4-11.
 */
//建立前缀树
class TreeNode{
    char val;
    TreeNode[] children = new TreeNode[26];
    TreeNode(char a) {
        val = a;
    }
    //标记是否为单词尾
    boolean word;
    TreeNode(){}
}
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main solution = new Main();
        int n = in.nextInt();
        String[] words = new String[n];
        TreeNode root = new TreeNode();
        //边读变插入到树中
        for(int i = 0; i < n; i++) {
            words[i] = in.next();
            solution.Insert(root,words[i]);
        }
        //查找,时间复杂度为O(nlogn)
        HashSet<String> has = new HashSet<>();
        int count = 0;
        for(String str:words){
            if(has.contains(str)) continue;
            String temp = new StringBuilder(str).reverse().toString();
            if(solution.Search(root,temp)){
                count++;
                has.add(str);
                has.add(temp);
            }
        }
        System.out.println(count);
    }
    private void Insert(TreeNode root,String word){
        TreeNode p = root;
        for(char c:word.toCharArray()){
            if(p.children[c-'a'] == null) p.children[c-'a'] = new TreeNode(c);
            p = p.children[c-'a'];
        }
        p.word = true;
    }
    private boolean Search(TreeNode root,String word){
        //查找是否有这个单词
        TreeNode p = root;
        for(char c:word.toCharArray()){
            if(p.children[c-'a'] != null) p = p.children[c-'a'];
            else return false;
        }
        if(p.word) return true;
        else return false;
    }
}
