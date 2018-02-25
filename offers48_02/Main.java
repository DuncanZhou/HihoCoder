package offers48_02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by duncan on 18-2-25.
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int value){
        val = value;
    }
}
public class Main {
    //给定一个中序遍历序列,找到字典顺序最小的先序遍历
    //把最小的作为根结点
    public TreeNode Construct(int[] inorder,int start,int end){
        if(start > end)
            return null;
        int index = Min(inorder,start,end);
        //构造根结点
        TreeNode root = new TreeNode(inorder[index]);
        root.left = Construct(inorder,start,index-1);
        root.right = Construct(inorder,index+1,end);
        return root;
    }
    public int Min(int[] num,int start,int end){
        int min = num[start];
        int res = start;
        for(int i = start; i <= end; i++) {
            if (num[i] < min) {
                min = num[i];
                res = i;
            }
        }
        return res;
    }
    //先序遍历
    public void preorder(TreeNode root, Queue<Integer> queue){
        if(root == null)
            return;
        queue.add(root.val);
        preorder(root.left,queue);
        preorder(root.right,queue);
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] inorder = new int[n];
        for(int i = 0; i < n; i++)
            inorder[i] = in.nextInt();
        TreeNode root = solution.Construct(inorder,0,n-1);
        Queue<Integer> queue = new LinkedList<>();
        solution.preorder(root,queue);
        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
