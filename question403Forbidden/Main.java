package question403Forbidden;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by duncan on 17-5-9.
 */
class TrieTreeNode{
    //该节点的内容0或1
    char binary;
    //是否是叶子节点
    boolean isleaf = false;
    //判断是否是终结节点
    int flag = 0;
    //存储规则的序号
    int order = 0;
    //如果是叶子结点是否是允许的
    boolean isAllow;
    //孩子节点
    TrieTreeNode[] childs = new TrieTreeNode[2];
}
public class Main {
    //先转成8位的二进制
    public String addressTo8bit(String address){
        Stack<Character> stack = new Stack<>();
        //转换成整型
        int numberaddr = Integer.parseInt(address);
        while(numberaddr / 2 >= 1){
            stack.push((char)(numberaddr % 2 + '0'));
            numberaddr =  numberaddr / 2;
        }
        stack.push((char)(numberaddr + '0'));
        while(stack.size() < 8){
            stack.push('0');
        }
        StringBuilder newaddr = new StringBuilder();
        while(!stack.isEmpty()){
            newaddr.append(stack.pop());
        }
        return newaddr.toString();
    }
    //转换成32位的地址
    public String ipTO32bit(String ip){
        /**
         * 将字符串ip地址转换成32位的二进制字符串
         */
        //先分成四部分,需要转译字符
        String[] address = ip.split("\\.");
        String newaddr = "";
        for(int i = 0; i < address.length; i++)
            newaddr += addressTo8bit(address[i]);
        return newaddr;
    }

    //插入到前缀树中
    public TrieTreeNode Insert(TrieTreeNode root,String address,boolean isAllow,int num,int order){
        if(num == 0) {
            root.flag = 1;
            root.isAllow = isAllow;
            return root;
        }
        String numberaddr = address;
            TrieTreeNode tempNode = root;
            //搜索并插入
            for(int i = 0; i < numberaddr.length(); i++){
                char al = numberaddr.charAt(i);
                //如果该节点存在则继续向下搜索
                if(tempNode.childs[al - '0'] != null && tempNode.childs[al - '0'].binary == al){
                    tempNode = tempNode.childs[al - '0'];

                }else{
                    TrieTreeNode newNode = new TrieTreeNode();
                    newNode.binary = al;
                    tempNode.childs[al - '0'] = newNode;
                    tempNode = newNode;
                }
                if(i + 1 == num) {
                    tempNode.isAllow = isAllow;
                    tempNode.flag = 1;
                    tempNode.order = order;
                }
            }
        return root;
    }
    public TrieTreeNode ConstructTrieTree(String[] addr,boolean[] permission,int[] num){
        TrieTreeNode root = new TrieTreeNode();
        for(int i = 0; i < addr.length; i++){
            root = Insert(root,addr[i],permission[i],num[i],i);
        }
        return root;
    }
    public boolean Search(TrieTreeNode root,String address){
        boolean result = true;
        int flag = 0;
        int order = Integer.MAX_VALUE;
        TrieTreeNode tempNode = root;
        for(int i = 0; i < address.length(); i++){
            char val = address.charAt(i);
            int index = val - '0';
            if(tempNode.childs[index] != null) {
                tempNode = tempNode.childs[index];
                if(tempNode.flag == 1) {
                    if(tempNode.order < order) {
                        result = tempNode.isAllow;
                        order = tempNode.order;
                        flag = 1;
                    }
                }
            }
            else
            {
                if(tempNode.flag == 1 && tempNode.order < order) {
                    result = tempNode.isAllow;
                    flag = 1;
                }
                break;
            }
        }
        //说明没找到,看看是否是拒绝了所有
        if(flag == 0)
        {
            if(root.flag == 1 && root.isAllow == false)
                result = false;
        }
        return result;
    }
    public static void main(String[] args) {
        Main solution = new Main();


        Scanner in = new Scanner(System.in);
        int configN = in.nextInt();
        int testN = in.nextInt();
        String[] configaddr = new String[configN];
        boolean[] permission = new boolean[configN];
        int[] num = new int[configN];
        in.nextLine();
        String[] testaddr = new String[testN];
        for(int i = 0 ; i  < configN; i++) {
            String config = in.nextLine();
            String[] pre = config.split("\\ ");
            if(pre[0].equals("allow"))
                permission[i] = true;
            else
                permission[i] = false;
            String[] preaddr = pre[1].split("/");
            configaddr[i] = solution.ipTO32bit(preaddr[0]);
            if(preaddr.length != 1)
                num[i] = Integer.parseInt(preaddr[1]);
            else
                num[i] = 32;
        }
        String[] results = new String[testN];
        TrieTreeNode root = solution.ConstructTrieTree(configaddr,permission,num);
        for(int i = 0 ; i < testN; i++) {
            testaddr[i] = solution.ipTO32bit(in.next());
            if(solution.Search(root,testaddr[i]) == true)
                results[i] = "YES";
            else
                results[i] = "NO";
        }
        for(int i = 0; i < testN; i++)
            System.out.println(results[i]);
    }
}
