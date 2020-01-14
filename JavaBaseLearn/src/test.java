import java.util.Scanner;

public class test {

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        double s=scanner.nextDouble();
        int[]a=new int[6];
        s=s*10;
        int d=(int)s;
        System.out.println("s:"+s);
//        d%=50;
        System.out.println("d:"+d);
        while(d!=0){
            if(d/1000>0){
                a[0]=d/1000;
//                System.out.println("100元"+d/1000);
                d=d%1000;

            }else if(d/100>0){
                a[1]=d/100;
//                System.out.println("十元"+d/100);
                d=d%100;

            }else if(d/50>0){
                a[2]=d/50;
//                System.out.println("5元"+d/50);
                d=d%50;

            }else if(d/10>0){
                a[3]=d/10;
//                System.out.println("1元"+d/10);
                d=d%10;
            }
            else if(d/5>0){
                a[4]=d/5;
//                System.out.println("5角"+d/5);
                d=d%5;
            }else {
                a[5]=d/1;
//                System.out.println("1角"+d/1);
                d=d%1;
            }
        }
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+"张:");
            if(i==0)
                System.out.println("100元");
            else if(i==1){
                System.out.println("10元");
            }
            else if(i==2){
                System.out.println("5元");
            }
            else if(i==3){
                System.out.println("1元");
            }
            else if(i==4){
                System.out.println("5角");
            }
            else{
                System.out.println("1角");
            }
        }
    }
}
