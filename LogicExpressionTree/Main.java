package LogicExpressionTree;


import java.util.Scanner;

class Node{
    int id;
    String val;
    Node(int a,String b){
        id = a;
        val = b;
    }
    Node left;
    Node right;
}
public class Main {
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Node root = new Node(1,"");
        for(int i = 0; i < N; i++){
            int parent = in.nextInt();
            String val = in.next();
            if(parent == 0) root.val = val;
            else solution.Insert(root,new Node(i+1,val),parent);
        }
        int[][] dp = new int[N+1][2];
        solution.dfs(root,dp,N+1);
        if(dp[1][0] == 0) System.out.println(dp[1][1] == N+1 ? -1 : dp[1][1]);
        else System.out.println(dp[1][0] == N+1 ? -1 : dp[1][0]);
    }
    //将t插入以root为根的树种
    private void Insert(Node root,Node t,int parent){
        if(root == null) return;
        Node p = root;
        if(p.id == parent){
            if(p.left == null) p.left = t;
            else p.right = t;
            return;
        }
        Insert(root.left,t,parent);
        Insert(root.right,t,parent);
    }
    //计算某个子树的值
//    private boolean Calc(Node root){
//        if(root.val.equals("TRUE")) return true;
//        if(root.val.equals("FALSE")) return false;
//        if(root.val.equals("OR")) return Calc(root.left) || Calc(root.right);
//        else return Calc(root.left) && Calc(root.right);
//    }

    private void dfs(Node root,int[][] dp,int inf){
        if(root.val.equals("TRUE")){
            dp[root.id][0] = inf;
            return;
        }
        if(root.val.equals("FALSE")){
            dp[root.id][1] = inf;
            return;
        }
        if(root.val.equals("TRUE") || root.val.equals("FALSE")) return;
        dfs(root.left,dp,inf);
        dfs(root.right,dp,inf);
        if(root.val.equals("AND"))
        {
            dp[root.id][0] = Math.min(dp[root.left.id][0],dp[root.right.id][0]);
            dp[root.id][0] = Math.min(dp[root.left.id][0]+dp[root.right.id][0] + 1,dp[root.id][0]);
            dp[root.id][1] = dp[root.left.id][1]+dp[root.right.id][1];
            dp[root.id][1] = Math.min(dp[root.id][1],Math.min(dp[root.left.id][1],dp[root.right.id][1])+1);
        }else{
            dp[root.id][0] = dp[root.left.id][0] + dp[root.right.id][0];
            dp[root.id][0] = Math.min(Math.min(dp[root.left.id][0],dp[root.right.id][0])+1,dp[root.id][0]);
            dp[root.id][1] = Math.min(dp[root.left.id][1],dp[root.right.id][1]);
            dp[root.id][1] = Math.min(dp[root.left.id][1] + dp[root.right.id][1]+1,dp[root.id][1]);
        }
    }
}

