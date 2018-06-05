package BuildingHeap;

import java.util.Scanner;

/**
 * Created by duncan on 18-6-5.
 */
class node{
    int val;
    node left;
    node right;
    node(int a){
        val = a;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] nums = new int[len];
        for(int i = 0; i < len; i++)
            nums[i] = in.nextInt();
        Main solution = new Main();
        node root = solution.Construct(nums,0,len-1);
        solution.Preorder(root);
    }
    //构建树
    private node Construct(int[] nums,int start,int end){
        if(start > end) return null;
        //寻找最小值
        int min = nums[start],index = start;
        for(int i = start + 1; i <= end; i++)
            if(nums[i] < min){
                min = nums[i];
                index = i;
            }
        node root = new node(min);
        root.left = Construct(nums,start,index-1);
        root.right = Construct(nums,index+1,end);
        return root;
    }
    //前序遍历
    private void Preorder(node root){
        if(root == null) return;
        System.out.print(root.val + " ");
        Preorder(root.left);
        Preorder(root.right);
    }
}
