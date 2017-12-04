package question1050;

import java.util.*;

/**
 * Created by duncan on 17-12-4.
 */

//求树中最长的路
public class Main {
    public void dfs(int node,int cur_depth,boolean[] visited,int[] depth,HashMap<Integer,List<Integer>> tree){
        depth[node-1] = cur_depth;
        visited[node-1] = true;
        //dfs
        for(int next:tree.get(node)){
            if(!visited[next-1])
                dfs(next,cur_depth+1,visited,depth,tree);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lines = in.nextInt();
        HashMap<Integer,List<Integer>> tree = new HashMap<>();
        for(int i = 0; i < lines - 1; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            if(!tree.containsKey(a))
                tree.put(a,new ArrayList<>());
            if(!tree.containsKey(b))
                tree.put(b,new ArrayList<>());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        int nodes = lines;
        boolean[] visited = new boolean[nodes];
        int[] depth = new int[nodes];
        Main solution = new Main();
        Arrays.fill(visited,false);
        //第一次dfs
        solution.dfs(1,0,visited,depth,tree);
        int ans = 0;
        for(int i = 0; i < nodes; i++)
            if(depth[i] > depth[ans])
                ans = i;
        Arrays.fill(visited,false);
        solution.dfs(ans+1,0,visited,depth,tree);
        ans = 0;
        for(int i = 0; i < nodes; i++)
            if(depth[i] > depth[ans])
                ans = i;
        System.out.println(depth[ans]);
    }
}
