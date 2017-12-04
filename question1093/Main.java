package question1093;

import java.util.*;

/**
 * Created by duncan on 17-12-4.
 */

//带权图,SPFA算法
class Edge{
    int end;
    int len;
    Edge(int b,int val){
        end = b;
        len = val;
    }
}
public class Main {
    public int Solve(HashMap<Integer,List<Edge>> map, int entrance, int exit, int N){
        //初始化一个队列
        Queue<Integer> queue = new LinkedList<>();
        //用一个数组维持extrance到其他点的最短距离
        int[] dist = new int[N+1];
        boolean[] visited = new boolean[N+1];
        for(int i = 1; i <= N; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[entrance] = 0;
        visited[entrance] = true;
        //初始化dist,通过遍历与entrance相关的边
        for(Edge edge:map.get(entrance))
            if(edge.len < dist[edge.end]) {
                dist[edge.end] = edge.len;
                queue.add(edge.end);
                visited[edge.end] = true;
            }
        //遍历queue
        while(!queue.isEmpty()){
            int cur = queue.poll();
            //将与cur相关的边加入
            for(Edge edge:map.get(cur)){
                if(visited[edge.end] == false)
                    queue.add(edge.end);
                //更新距离值
                if(edge.len + dist[cur] < dist[edge.end])
                    dist[edge.end] = edge.len + dist[cur];
            }
        }
        return dist[exit];
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int entrance = in.nextInt();
        int exit = in.nextInt();
        HashMap<Integer,List<Edge>> map = new HashMap<>();
        //初始化map
        for(int i = 1; i <= N; i++)
            map.put(i,new ArrayList<>());
        for(int i = 0; i < M; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int len = in.nextInt();
            //加入边,并建立HashMap
            map.get(a).add(new Edge(b,len));
            map.get(b).add(new Edge(a,len));
        }
        System.out.println(solution.Solve(map,entrance,exit,N));
    }
}
