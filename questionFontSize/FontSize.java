package questionFontSize;

import java.util.Scanner;

/**
 * Created by duncan on 17-5-2.
 */
public class FontSize {
    public int MaxFontSize(int[] details,int[] lines){
        int maxFontSize,leftRows = 0,leftC = 0,pageC,totalPages,Weight,Height,curPages = 0;
        totalPages = details[1];
        Weight = details[2];
        Height = details[3];
        for(maxFontSize = Math.min(Weight,Height);maxFontSize > 1; maxFontSize--){
            int rowC = Weight / maxFontSize;
            int colC = Height / maxFontSize;
            pageC = rowC * colC;
            curPages = 0;
            leftRows = 0;
            leftC = 0;
            for(int i = 0; i < lines.length;i++) {
                if (leftRows == 0 || lines[i] > leftC) {
                    int ToAr = lines[i];
                    if(lines[i] > leftC)
                        ToAr = lines[i] - leftRows * rowC;
                    int pages = ToAr / pageC;
                    //计算当前页面剩余容量
                    int last = ToAr - pages * pageC;
                    int accountRows = (int)Math.ceil((double)last / rowC);
                    if(ToAr % pageC != 0)
                        pages++;
                    curPages += pages;
                    //计算剩余容量
                    leftRows = colC - accountRows;
                    leftC = leftRows * rowC;
                }
                else if(lines[i] < leftC){
                    int accountRows = (int)Math.ceil((double) lines[i] / (double)rowC);
                    leftRows -= accountRows;
                    leftC = leftRows * rowC;
                }else if(lines[i] == leftC) {
                    leftRows = 0;
                    leftC = 0;
                }
                if(curPages > totalPages)
                    break;
            }
            //总页面小于要求的则返回
            if(curPages <= totalPages)
                return maxFontSize;
        }
        return 1;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FontSize solution =  new FontSize();
        int caseN,maxFontSize;
        caseN = in.nextInt();
        int[] results = new int[caseN];
        //根据case的数量输入
        //第一行为N,P,W,H(N为段落的数量,P为最多允许的页数,W和H分别为屏幕的宽度和高度)
        //第二行为每一段落的字符数
        for(int i = 0; i < caseN; i++)
        {
            int[] details = new int[4];
            for(int j = 0; j < 4; j++)
                details[j] = in.nextInt();
            //接着输入每一段落的容纳的字符数
            int paras = details[0];
            int [] pChars = new int[paras];
            for(int k = 0; k < paras; k++)
                pChars[k] = in.nextInt();
            maxFontSize = solution.MaxFontSize(details,pChars);
            results[i] = maxFontSize;
        }
        for(int i = 0; i < caseN; i++)
            System.out.println(results[i]);
    }
}
