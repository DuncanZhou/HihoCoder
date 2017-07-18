package questionStockPrice;
import java.util.*;

/**
 * Created by duncan on 17-6-6.
 */
class Info implements Comparable{
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    int price;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    int timestamp;
    public int compareTo(Object o){
        Info info = (Info)o;
        if(this.getPrice() == info.getPrice())
            return 0;
        return this.getPrice() > info.getPrice() ? 1 : -1;
    }
}
class Result{
    public int getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(int maxprice) {
        this.maxprice = maxprice;
    }

    public int getMinprice() {
        return minprice;
    }

    public void setMinprice(int minprice) {
        this.minprice = minprice;
    }

    public int getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(int currentprice) {
        this.currentprice = currentprice;
    }

    int maxprice;
    int minprice;
    int currentprice;
}
public class Main {
    public static void main(String[] args) {
        //定义一个列表存储价格
        List<Info> priceList = new ArrayList<>();
        //结果列表
        List<Result> resultsList = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int operationN = in.nextInt();
        String temp = in.nextLine();
        for(int i = 0 ; i < operationN; i++){
            //输入指令,以空格分隔,三种指令P/R/Q
            //p指令,将记录加入列表中
            //r指令,删除列表中记录
            //q指令将查询结果加入到结果列表中
            String operation = in.nextLine();
            String[] strings = operation.split(" ");
            if(strings[0].equals("P")){
                Info tempinfo = new Info();
                tempinfo.timestamp = Integer.parseInt(strings[1]);
                tempinfo.price = Integer.parseInt(strings[2]);
                priceList.add(tempinfo);
            }
            else if(strings[0].equals("R")){
                //删除endgtime之前的记录
                int endtime = Integer.parseInt(strings[1]);
                while(priceList.size() > 0 && priceList.get(0).getTimestamp() <= endtime){
                    priceList.remove(0);
                }
            }
            else{
                Result result = new Result();
                //当前价格在列表中第一个
                result.currentprice = priceList.get(priceList.size() - 1).getPrice();
                result.maxprice = Collections.max(priceList).getPrice();
                result.minprice = Collections.min(priceList).getPrice();
                resultsList.add(result);
            }
        }
        //对结果输出
        for(Result result:resultsList){
            System.out.println(result.getMaxprice() + " " + result.getMinprice() + " " + result.getCurrentprice());
        }
    }
}
