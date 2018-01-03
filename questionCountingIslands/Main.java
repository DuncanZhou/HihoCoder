package questionCountingIslands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by duncan on 18-1-3.
 */
class Point{
    int x;
    int y;
    Point(int a,int b){
        x = a;
        y = b;
    }
}
public class Main {
    //深度搜索
    public void dfs(boolean[][] visited, int[][] islands,int row,int col){
        visited[row][col] = true;
        //向上
        if(row > 0 && visited[row-1][col] == false && islands[row-1][col] == 1)
            dfs(visited,islands,row-1,col);
        //向下
        if(row < 1000 && visited[row+1][col] == false && islands[row+1][col] == 1)
            dfs(visited,islands,row+1,col);
        //向左
        if(col > 0 && visited[row][col-1] == false && islands[row][col-1] == 1)
            dfs(visited,islands,row,col-1);
        //向右
        if(col < 1000 && visited[row][col+1] == false && islands[row][col+1] == 1)
            dfs(visited,islands,row,col+1);
    }
    public int solve(int[][] islands,List<Point> lands){
        /*
        k为填的个数
         */
        boolean[][] visited = new boolean[1000][1000];
        for(int i = 0; i < 1000; i++)
            Arrays.fill(visited[i],false);
        int islandsN = 0;
        for(Point land: lands){
            if(visited[land.x][land.y] == false){
                islandsN++;
                dfs(visited,islands,land.x,land.y);
            }
        }
        return islandsN;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        List<Point> lands = new ArrayList<>();
        //初始化一个1000*1000的数组
        int[][] islands = new int[1000][1000];
        for(int i = 0; i < 1000; i++)
            Arrays.fill(islands[i],0);
        Scanner in = new Scanner(System.in);
        int k = 0;
        int weeks = in.nextInt();
        for(int i = 0; i < weeks; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            Point point = new Point(x,y);
            lands.add(point);
            k ++;
            islands[x][y] = 1;
            System.out.println(solution.solve(islands,lands));
        }
    }
}
