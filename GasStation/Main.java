package GasStation;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Station{
    int dis;
    int nums;
    double avg;
    public Station(int a,int b){
        dis = a;
        nums = b;
        avg = dis * 1.0 / (nums + 1);
    }
}
public class Main {
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        // 已有N个加油站
        int N = in.nextInt();
        //高速公路长M km
        int M = in.nextInt();
        //最多还可添加 k个加油站
        int k = in.nextInt();

        //用最大堆维持最大的距离
        PriorityQueue<Station> heap = new PriorityQueue<Station>(new Comparator<Station>() {
            @Override
            public int compare(Station o1, Station o2) {
                if(o1.avg > o2.avg) return -1;
                else if(o1.avg == o2.avg) return 0;
                else return 1;
            }
        });
        //第一个为0
        int pre = in.nextInt();
        //保存每个段的距离
        for(int i = 1; i < N; i++){
            int cur = in.nextInt();
            int temp = cur - pre;
            pre = cur;
            //加入每段
            heap.add(new Station(temp,0));
        }
        System.out.println(solution.helper(heap,k));
    }
    private String helper(PriorityQueue<Station> heap,int k){
        double res = Double.MAX_VALUE;
        //贪心算法
        for(int i = 0; i < k; i++){
            //每次取稀疏度最大的段拿出来加入
            Station temp = heap.poll();
            //更新后放回
            temp.nums++;
            temp.avg = temp.dis * 1.0 / (temp.nums + 1);
            if(temp.avg < res) res = temp.avg;
            heap.add(temp);
        }
        return String.format("%.1f",res);
    }
}
