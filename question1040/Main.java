package question1040;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by duncan on 17-10-10.
 */
/*
第一步，判断四条线段的八个顶点是否两两重合为矩形的四个顶点；

第二步，通过向量判断四条线段是否互相平行或垂直。

如果两个向量u=(x1, y1)，v=(x2, y2)平行，那么x1*y2-x2*y1=0,；如果它们垂直，那么x1*x2+y1*y2=0
 */
class Vector{
    int x;
    int y;
    Vector(int a,int b){
        x = a;
        y = b;
    }
}
public class Main {
    public boolean Check(HashSet<String> points,ArrayList<Vector> vectors){
        //check points
        if(points.size() != 4)
            return false;
        //check each line with other three lines
        for(int i = 1; i < vectors.size(); i++){
            if(!(vectors.get(0).x * vectors.get(i).y - vectors.get(i).x*vectors.get(0).y == 0 || vectors.get(0).x * vectors.get(i).x + vectors.get(0).y * vectors.get(i).y == 0))
                return false;
            }
        return true;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        for(int i = 0; i < number; i++){
            HashSet<String> points = new HashSet<>();
            ArrayList<Vector> vectors = new ArrayList<>();
            for(int j = 0; j < 4; j++){
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();
                points.add(x1 + "-" + y1);
                points.add(x2 + "-" + y2);
                Vector vector = new Vector(x2-x1,y2-y1);
                vectors.add(vector);
            }
            if(solution.Check(points,vectors))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
