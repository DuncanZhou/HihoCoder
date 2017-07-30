package questionCheckisTree;

import java.util.Scanner;

/**
 * Created by duncan on 17-7-30.
 */

//本题为判断一个无向图是否是一颗树
    //树为无环的连通图或者边数等于顶点数-1
class Graph{
    //邻接矩阵,从1开始存储
    int[][] graph = new int[501][501];
    //顶点数和边数
    int nodes,edges;
}
public class Main {
    //判断是否是连通图,并且顶点数-1是否是边数
    //判断是否是连通图深度遍历一遍,都遍历到了即为连通图
    public void DFS(int[] visited,Graph graph,int start){
        //如果该顶点没有访问过
        if(visited[start] == 0){
            visited[start] = 1;
            //继续访问与其相连的顶点
            for(int i = 1; i <= graph.nodes; i++){
                if(graph.graph[start][i] == 1)
                    DFS(visited,graph,i);
            }
        }
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for(int i = 0; i < num; i++){
            //输入顶点数和边数
            Graph graph = new Graph();
            graph.nodes = in.nextInt();
            graph.edges = in.nextInt();
            //初始化邻接矩阵
            for(int j = 0; j < graph.edges; j++){
                int a = in.nextInt();
                int b = in.nextInt();
                graph.graph[a][b] = 1;
                graph.graph[b][a] = 1;
            }
            if(graph.edges != graph.nodes - 1)
            {
                System.out.println("NO");
                continue;
            }
            //判断是否连通
            //初始化一个顶点访问数组,从1开始存储
            int[] visited = new int[graph.nodes+1];
            solution.DFS(visited,graph,1);
            //对visited数组判断
            boolean flag = true;
            for(int j = 1; j < graph.nodes; j++) {
                if (visited[j] == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
