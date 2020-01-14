import javax.swing.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
/*
* 保留2位小数
* */
public class KeepTowNum {
    public static void main(String[] args){
        double d = 3.2915926;
//第1种方式
        String s = String.format("%.2f",d);
        //第2种方式
//        DecimalFormat df = new java.text.DecimalFormat("#0.00");
//        String s= df.format(d);
//// 第3种 方式
//        NumberFormat ddf1=NumberFormat.getNumberInstance() ;         ddf1.setMaximumFractionDigits(2);
//        String s= ddf1.format(d);
//// 第4种 方式
//        BigDecimal b = new BigDecimal(d);
//        double s = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println(s);

    }
}
