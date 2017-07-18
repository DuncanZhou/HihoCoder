package questionAreaGirth;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by duncan on 17-7-18.
 */
class Point{
    Point(int i,int j){
        row = i;
        column = j;
    }
    int row,column;
}
public class Main {
    //递归搜索,matrix从1开始存储
    public HashSet<Point> Search(int rowN,int colN,int srow,int scol,int[][] matrix,HashSet<Point> results,boolean[][] visited){
        //一共分四个方向,上下左右1,2,3,4
        for(int flag = 1; flag <= 4; flag++){
            switch (flag){
                //上搜索
                case 1:
                    if(srow - 1 < 0)
                        break;
                    Point newP1 = new Point(srow - 1,scol);
                    if(matrix[srow - 1][scol] == matrix[srow][scol] && visited[srow - 1][scol] == false)
                    {
                        results.add(newP1);
                        visited[srow - 1][scol] = true;
                        //递归搜索
                        results = Search(rowN,colN,srow - 1,scol,matrix,results,visited);
                    }
                    break;
                //下搜索
                case 2:
                    if(srow + 1 >= rowN)
                        break;
                    Point newP2 = new Point(srow + 1,scol);
                    if(srow + 1 <= rowN && matrix[srow + 1][scol] == matrix[srow][scol] && visited[srow + 1][scol] == false)
                    {
                        results.add(newP2);
                        visited[srow + 1][scol] = true;
                        //递归搜索
                        Search(rowN,colN,srow + 1,scol,matrix,results,visited);
                    }
                    break;
                //左搜索
                case 3:
                    if(scol - 1 < 0)
                        break;
                    Point newP3 = new Point(srow,scol - 1);
                    if(matrix[srow][scol - 1] == matrix[srow][scol] && visited[srow][scol - 1] == false)
                    {
                        results.add(newP3);
                        visited[srow][scol - 1] = true;
                        //递归搜索
                        results = Search(rowN,colN,srow,scol - 1,matrix,results,visited);
                    }
                    break;
                //右搜索
                case 4:
                    if(scol + 1 >= colN)
                        break;
                    Point newP4 = new Point(srow,scol + 1);
                    if(matrix[srow][scol + 1] == matrix[srow][scol] && visited[srow][scol + 1] == false)
                    {
                        results.add(newP4);
                        visited[srow][scol + 1] = true;
                        //递归搜索
                        results = Search(rowN,colN,srow,scol + 1,matrix,results,visited);
                    }
                    break;
            }
        }
        return results;
    }
    //计算有多少与其相邻
    public int Neighbour(HashSet<Point> results,Point target){
        int num = 0;
        for(Point p:results){
            if(p.row == target.row - 1 && p.column == target.column)
                num++;
            if(p.row == target.row + 1 && p.column == target.column)
                num++;
            if(p.column == target.column - 1 && p.row == target.row)
                num++;
            if(p.column == target.column + 1 && p.row == target.row)
                num++;
        }
        return num;
    }
    //计算周长
    public int CalcArea(HashSet<Point> results){
        int girth = 0;
        for(Point p:results){
            girth += 4 - Neighbour(results,p);
        }
        return girth;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rowN,colN,srow,scol;
        rowN = in.nextInt();
        colN = in.nextInt();
        srow = in.nextInt();
        scol = in.nextInt();
        int[][] matrix = new int[rowN][colN];
        //访问标志
        boolean[][] visited = new boolean[rowN][colN];
        //初始化
        for(int i = 0; i < rowN; i++)
            for(int j = 0; j < colN; j++)
                visited[i][j] = false;
        for(int i = 0; i < rowN; i++)
            for(int j = 0; j < colN; j++)
                matrix[i][j] = in.nextInt();
        Main Solution= new Main();
        HashSet<Point> results = new HashSet<>();
        Point start = new Point(srow,scol);
        results.add(start);
        visited[srow][scol] = true;
        System.out.println(Solution.CalcArea(Solution.Search(rowN,colN,srow,scol,matrix,results,visited)));
    }
}
