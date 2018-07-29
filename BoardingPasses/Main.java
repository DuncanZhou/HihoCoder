package BoardingPasses;

import java.util.*;

//寻找出发开始和结束城市
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //保存关系
        HashMap<String,HashSet<String>> rels = new HashMap<>();
        int N = in.nextInt();
        //定义一个二维矩阵存储出入度
        HashMap<String,int[]> nodes = new HashMap<>();
        for(int i = 0; i < N; i++){
            String a = in.next();
            String b = in.next();
            if(!rels.containsKey(a)){
                HashSet<String> temp = new HashSet<>();
                temp.add(b);
                rels.put(a,temp);
            }else rels.get(a).add(b);
            if(!nodes.containsKey(a)){
                int[] temp = new int[2];
                temp[1] = 1;
                nodes.put(a,temp);
            }else nodes.get(a)[1]++;
            if(!nodes.containsKey(b)){
                int[] temp = new int[2];
                temp[0] = 1;
                nodes.put(b,temp);
            }else nodes.get(b)[0]++;
        }
        String start = null,end = null;
        //遍历map，入度为0即为出发城市，出度为0则为结束城市
            for (Map.Entry<String, int[]> entry : nodes.entrySet()) {
                if (entry.getValue()[1] - entry.getValue()[0] == 1) start = entry.getKey();
                if (entry.getValue()[0] - entry.getValue()[1] == 1) end = entry.getKey();
            }
        System.out.println(start + " " + end);
    }
}
