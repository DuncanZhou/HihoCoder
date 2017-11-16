package question1049;

import java.util.Scanner;

/**
 * Created by duncan on 17-11-16.
 */

class TreeNode{
    char value;
    TreeNode left;
    TreeNode right;
    TreeNode(char c){
        value = c;
    }
}
//根据树的中序遍历和前序遍历结果还原树的后序遍历
public class Main {
    public TreeNode solve(String preorder, String inorder){
        if(preorder.length() == 0 || inorder.length() == 0) {
            return null;
        }
        int i;
        TreeNode root = new TreeNode(preorder.charAt(0));
        for(i = 0; i < inorder.length(); i++)
            if(inorder.charAt(i) == preorder.charAt(0))
                break;
        root.left = solve(preorder.substring(1,i+1),inorder.substring(0,i));
        root.right = solve(preorder.substring(i+1,preorder.length()),inorder.substring(i+1,inorder.length()));
        return root;
    }
    public void postorder(TreeNode root){
        if(root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.value);
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        String preorder = in.next();
        String inorder = in.next();
        TreeNode root = solution.solve(preorder,inorder);
        solution.postorder(root);
    }
}
