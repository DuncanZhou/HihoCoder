package question1041;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by duncan on 17-10-11.
 */
public class Main {
    public void DFS(int[][] gMatrix,int[] visited,int start,int end,Stack<Integer> path){
        //dfs
        //a stack to store the path from current to end
        for(int i = 1; i < visited.length; i++){
            if(gMatrix[start][i] >= 1 && visited[i] == 0){
                if(!path.isEmpty() && path.peek() == end)
                    return;
                //each edge only can be visited 2 times
                    visited[i] = 1;
                    path.add(i);
                    if(i == end)
                        return;
                    DFS(gMatrix,visited,i,end,path);
                }
            }
            if(!path.isEmpty() && path.peek() == end)
                return;
            path.pop();
    }
    public boolean Search(int[][] gMatrix,int[] toCheckCities){
        int current = 1;
        for(int i = 0; i < toCheckCities.length; i++){
            int end = toCheckCities[i];
            int[] visited = new int[gMatrix.length];
            visited[current] = 1;
            Stack<Integer> path = new Stack<>();
            path.push(current);
            DFS(gMatrix,visited,current,end,path);
            //no path
            if(path.isEmpty())
                return false;
            int b = path.pop();
            if(path.isEmpty())
            {
                gMatrix[b][current] += 1;
                gMatrix[current][b] += 1;
            }
            //no path
            if(b != toCheckCities[i])
                return false;
            //update gMatrix
            while(!path.isEmpty()){
                int a = path.pop();
                gMatrix[a][b] += 1;
                gMatrix[b][a] += 1;
                if(gMatrix[a][b] == 3)
                {
                    gMatrix[a][b] = 0;
                    gMatrix[b][a] = 0;
                }
                b = a;
            }
            current = end;
        }
        return true;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        for(int i = 0; i < number; i++){
            int cities = in.nextInt();
            int[][] gMatrix = new int[cities + 1][cities + 1];
            for(int j = 0; j < cities - 1; j++){
                int a = in.nextInt();
                int b = in.nextInt();
                gMatrix[a][b] = 1;
                gMatrix[b][a] = 1;
            }
            int toCheck = in.nextInt();
            int[] toCheckCities = new int[toCheck];
            for(int j = 0; j < toCheck; j++)
                toCheckCities[j] = in.nextInt();
            if(solution.Search(gMatrix,toCheckCities))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
