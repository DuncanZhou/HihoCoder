package question1014;

import java.util.Scanner;

/**
 * Created by duncan on 17-5-8.
 */

class TrieTreeNode{
    /***
     * 创建TrieTree结点类
     */
    //是否是单词末尾
    boolean end;
    //结点值
    char val;
    //通过该点次数
    int num = 0;
    //子节点,最多26个字母
    TrieTreeNode[] childs = new TrieTreeNode[26];
}
public class Main {
    //插入树操作
    public TrieTreeNode Insert(String word,TrieTreeNode root){
        TrieTreeNode temp = root;
        if(root == null) {
            temp = root = new TrieTreeNode();
            for (int i = 0; i < word.length(); i++) {
                TrieTreeNode newNode = new TrieTreeNode();
                newNode.val = word.charAt(i);
                newNode.num += 1;
                int index = newNode.val - 'a';
                temp.childs[index] = newNode;
                temp = newNode;
            }
        }else{
            for (int i = 0; i < word.length(); i++) {
                char val = word.charAt(i);
                int index = val - 'a';
                //如果不存在该点,则添加,否则继续向下遍历
                if(temp.childs[index] == null){
                    TrieTreeNode newNode = new TrieTreeNode();
                    newNode.num += 1;
                    newNode.val = val;
                    temp.childs[index] = newNode;
                    temp = newNode;
                }else {
                    //计数器也要加
                    temp.childs[index].num += 1;
                    temp = temp.childs[index];
                }
            }
        }
        //单词结尾加个标记
        temp.end = true;
        return root;
    }
    //构建TrieTree树
    public TrieTreeNode ConstructTrieTree(String[] word,TrieTreeNode root){
        for(int i = 0; i < word.length; i++)
            root = Insert(word[i],root);
        return root;
    }
    //寻找单词前缀个数
    public int SearchPrefix(TrieTreeNode root,String prefix){
        int result = 0;
        TrieTreeNode searchnode = root;
        boolean flag = true;
        for(int i = 0; i < prefix.length(); i++){
            char al = prefix.charAt(i);
            int index = al - 'a';
            searchnode = searchnode.childs[index];
            if(searchnode == null || searchnode.val != al) {
                flag = false;
                break;
            }
        }
        if(flag == true)
            result = searchnode.num;
        return result;
    }
    public static void main(String[] args) {
        Main Solution = new Main();
        Scanner in = new Scanner(System.in);
        //DicN和PrefixM分别是字典单词个数和要查找的单词个数
        int DicN,PrefixM;
        DicN = in.nextInt();
        TrieTreeNode root = null;
        //results存储前缀单词数的结果
        int [] results = new int[DicN];
        String [] words = new String[DicN];
        for(int i = 0; i < DicN; i++)
            //输入词典单词    1.next()输入是遇到空格或换行符结束;2.nextLine()输入中可以包含空格
            words[i] = in.next();

        //构造TrieTree
        root = Solution.ConstructTrieTree(words,root);
        PrefixM = in.nextInt();
        for(int i = 0; i < PrefixM; i++) {
            String word = in.next();
            results[i] = Solution.SearchPrefix(root, word);
        }
        for(int i = 0 ; i < results.length ; i++)
            System.out.println(results[i]);
    }
}
