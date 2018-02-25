package offers48_01;

import com.sun.deploy.resources.Deployment_it;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by duncan on 18-2-25.
 */
class Point{
    int x;
    int y;
    Point(int a,int b){
        x = a;
        y = b;
    }
    Point(){

    }
}
class Line{
    Point p1;
    Point p2;
    Line(Point a,Point b){
        p1 = a;
        p2 = b;
    }
}
public class Main {
    public double Dis(Point p1,Point p2){
        return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y,2));
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        List<Line> Lines = new ArrayList<>();
        int num = in.nextInt();
        double distance = 0;
        Point old = null;
        List<Point> lines = new ArrayList<>();
        for(int i = 0; i < num; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            Point come = new Point(a,b);
            lines.add(come);
            if(old != null) {
                distance += solution.Dis(old, come);
                Lines.add(new Line(old,come));
            }
            old = come;
        }
        double target = distance / 2;
        double current = 0;
        //找到中点在哪条线段上
        int i = 0;
        while(i < Lines.size()){
            Line line = Lines.get(i++);
            double len = solution.Dis(line.p1,line.p2);
            if(current + len < target)
                current += len;
            else break;
        }
        i--;
        //中点在第i条线段上
        double left = target - current;
        Line curLine = Lines.get(i);
        double len = solution.Dis(curLine.p1,curLine.p2);
        double y = curLine.p1.y + (left / len) * (curLine.p2.y - curLine.p1.y);
        double x = curLine.p1.x + (left / len) * (curLine.p2.x - curLine.p1.x);
        System.out.println(String.format("%.1f",x).toString() + " " + String.format("%.1f",y).toString());
    }
}
