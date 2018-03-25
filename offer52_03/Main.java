package offer52_03;

import java.util.*;

/**
 * Created by duncan on 18-3-25.
 */

class TreeNode{
    int id;
    double val;
    List<TreeNode> childs = new ArrayList<>();
    TreeNode(int id){
        this.id = id;
    }
}
public class Main {
    //构建树
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] vals = new int[N];
        TreeNode[] nodes = new TreeNode[N+1];
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
            vals[i] = in.nextInt();
            nodes[i+1] = new TreeNode(i+1);
            nodes[i+1].val = (double)vals[i];
            set.add(i+1);
        }
        //构建树
        for(int i = 0; i < N - 1; i++){
            int parent = in.nextInt();
            int child = in.nextInt();
            if(set.contains(child)) set.remove(child);
            nodes[parent].childs.add(nodes[child]);
        }
        //找到根结点
        int root = 1;
        for(int x:set)
            root = x;
        System.out.println(String.format("%.1f",solution.Level(nodes[root])));

    }
    private double Level(TreeNode root){
        //层次遍历
        //flag表示上一结点是否加入
        if(root == null) return 0;
        if(root.childs.size() == 0) return root.val;
        //遍历所有的子结点
        double res1 = root.val;
        double res2 = 0;
        for(TreeNode node: root.childs){
            node.val /= 2.0;
            res1 += Level(node);
            node.val *= 2;
            res2 += Level(node);
        }
        return Math.max(res1,res2);
    }
}
