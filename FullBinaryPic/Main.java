package FullBinaryPic;

import java.util.*;

/**
 * Created by duncan on 17-11-21.
 */

//G(h) = 3*2^(h-1)-1(高度为h那一层所需的长度), L_total = 3*2^(h-2)-1, G(L_i) = G(L_i+1)-2
public class Main {
    public boolean check(int[] corner,int x, int y){
        if(x >= corner[0] && x <= corner[2] && y >= corner[1] && y <= corner[3])
            return true;
        return false;
    }
    public HashMap<Integer,List<Integer>> Construct(int height){
        //由下向上将每一层结点的位置保存起来
        HashMap<Integer,List<Integer>> positions = new HashMap<>();
        int length = (int)(3*Math.pow(2,height-1)-1);
        int level =  (int)(3*Math.pow(2,height-2));
        int nodes;
        int step = 3;
        int step1 = 1;
        int lastLevel = level;
        while(length > 1){
            //有结点,需要存储位置
            nodes = (int)(Math.pow(2,height-1));
            if(length == (step+step1+2)/2*nodes-step1){
                ArrayList<Integer> yPositions = new ArrayList<>();
                int right = lastLevel - level;
                for(int i = 0; i < nodes / 2; i++){
                    //左右对称
                    if(i % 2 == 0){
                        right += 1;
                        yPositions.add(right);
                        yPositions.add(-right);
                    }
                    else {
                        right += step + 1;
                        yPositions.add(right);
                        yPositions.add(-right);
                        right += step1;
                    }
                }
                Collections.sort(yPositions);
                int size = yPositions.size();
                if(size >= 4) {
                    int y1 = (yPositions.get(size - 1) + yPositions.get(size - 2)) / 2;
                    int y2 = (yPositions.get(size - 3) + yPositions.get(size - 4)) / 2;
                    step1 = 2*(lastLevel - level)+step+2 ;
                    step =  y1 - y2 - 1;
                }
                //将结点位置加入到HashMap中
                positions.put(level-1,yPositions);
                height--;
            }
            level--;
            length -= 2;
        }
        //最上面一个加入
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(0);
        positions.put(0,temp);
        return positions;
    }
    public int Count(HashMap<Integer,List<Integer>> positions,int[] corner){
        int count = 0;
        //遍历positions
        for(Integer key:positions.keySet()){
            if(key >= corner[0] && key <= corner[2]){
                ArrayList<Integer> yPositions = (ArrayList)positions.get(key);
                for(Integer y:yPositions)
                    count += check(corner,key,y) ? 1 : 0;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in  = new Scanner(System.in);
        int height = in.nextInt();
        //构造
        HashMap<Integer,List<Integer>> positions = solution.Construct(height);
        int examples = in.nextInt();
        for(int i = 0; i < examples; i++){
            int[] corner = new int[4];
            for(int j = 0; j < 4; j++)
                corner[j] = in.nextInt();
            System.out.println(solution.Count(positions,corner));
        }
    }
}
