package questionPopularProducts;

import java.util.*;

/**
 * Created by duncan on 17-12-27.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int listN = in.nextInt();
        if(listN <= 0)
            return;
        HashMap<String,List<Double>> res = new HashMap<>();
        //先把第一堆产品加入
        int productsN = in.nextInt();
        in.nextLine();
        for(int i = 0; i < productsN; i++){
            String record = in.nextLine();
            String[] records = record.split(" ");
            String id = records[0];
            Double price = Double.parseDouble(records[2]);
            if(!res.containsKey(id)) {
                List<Double> prices = new ArrayList<>();
                prices.add(price);
                res.put(id, prices);
            }else{
                if(!res.get(id).contains(price))
                {
                    List<Double> temp = res.get(id);
                    temp.add(price);
                    res.put(id,temp);
                }
            }
        }
        for(int i = 1; i < listN; i++){
            productsN = in.nextInt();
            in.nextLine();
            for(int j = 0; j < productsN; j++){
                String record = in.nextLine();
                String[] records = record.split(" ");
                String id = records[0];
                Double price = Double.parseDouble(records[2]);
                if(!(res.containsKey(id) && res.get(id).contains(price)))
                    res.remove(id);
            }
        }
        //有重复的需要输出多次,并且输出按照id的升序
        Set<String> ids = new TreeSet<String>(res.keySet());
        for(String id:ids){
            int times = res.get(id).size();
            for(int i = 0; i < Math.min(2,times); i++)
                System.out.println(id);
        }
    }
}
