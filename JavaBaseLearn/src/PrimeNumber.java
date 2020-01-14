import java.util.ArrayList;
import java.util.List;

/*
* 求质数
* */
public class PrimeNumber {
     /*
     * 普通算法一
     * 双重for循环遍历，一个个判断
     * */

     public static void test1(int n){
         for(int i=2;i<=n;i++){
             boolean flage=true;
             for(int j=2;j<i;j++ ){
                 if(i%j==0){
                     flage=false;
                     break;
                 }
             }
             if(flage){
                 System.out.println("素数"+i);
             }
         }
     }
    /*
     * 普通算法二
     * 双重for循环遍历至，一个个判断
     * */
    public static void test2(int n){
        for(int i=2;i<=n;i++){
            boolean flage=true;
            for(int j=2;j<=i/2;j++ ){
                if(i%j==0){
                    flage=false;
                    break;
                }
            }
            if(flage){
                System.out.println("素数"+i);
            }
        }
    }
    /*
     * 普通算法三
     * 双重for循环遍历至Math.sqrt(i)，一个个判断
     * */
    public static void test3(int n){
        for(int i=2;i<=n;i++){
            boolean flage=true;
            for(int j=2;j<=Math.sqrt(i);j++ ){
                if(i%j==0){
                    flage=false;
                    break;
                }
            }
            if(flage){
                System.out.println("素数"+i);
            }
        }
    }
     /**
      * 埃式筛法求质数
      */
     public static void sieve2(int n){
         //定义布尔类型数组用于判断该索引下的数是否为数组
         //默认把所有当做素数
         boolean[] isPrime=new boolean[n+1];
         int cnt=0;
//         String s="asas";
//         int a=s.length();
         for(int i=2;i<=n;i++){
             isPrime[i]=true;
         }
         for(int i=2;i<=Math.sqrt(n);i++){
             if(isPrime[i])//当前下标数为质数，则它的倍数不为质数
             {
                 for(int j=2*i;j<=n;j+=i){
                     if(isPrime[j]==true){
                         isPrime[j]=false;
                         System.out.println("aa"+i+isPrime[j]);
                     }
                 }
             }
         }
         for(int i=2;i<=n;i++){
             if(isPrime[i])
             {
                 System.out.println("素数"+i);
                 cnt++;
             }
         }
         System.out.println("共有"+cnt+"个质数");
     }
     public static void sieve(int n){
         //定义布尔类型数组用于判断该索引下的数是否为数组
         //默认把所有当做素数
         long start = System.currentTimeMillis();
         boolean[] isPrime=new boolean[n+1];
         int cnt=0;
         for(int i=2;i<=n;i++){
//             if(i==0||i==1)
//                 isPrime[i]=false;//1,0为素数
//             else
                 isPrime[i]=true;
         }
         for(int i=2;i<=n;i++){
             if(isPrime[i])//当前下标数为质数，则它的倍数不为质数
             {
                 for(int j=2*i;j<=n;j+=i){
                     if(isPrime[j]==true){
                         isPrime[j]=false;
                     }
                 }
             }
             if(isPrime[i])
             {
                 System.out.println("素数"+i);
                 cnt++;
             }

         }
         System.out.println("共有"+cnt+"个质数");
         long end = System.currentTimeMillis();
         System.out.println("The time cost is " + (end - start));
         System.out.println("");
     }


    public static List sieve4(int n){
        //定义布尔类型数组用于判断该索引下的数是否为数组
        //默认把所有当做素数
        boolean[] isPrime=new boolean[n+1];
        List list=new ArrayList();
        for(int i=2;i<=n;i++){
            isPrime[i]=true;
        }
        for(int i=2;i<=Math.sqrt(n);i++){
            if(isPrime[i])//当前下标数为质数，则它的倍数不为质数
            {
                for(int j=2*i;j<=n;j+=i){
                    if(isPrime[j]==true){
                        isPrime[j]=false;
                    }
                }
            }
        }
        for(int i=2;i<=n;i++){
            if(isPrime[i])
            {
                list.add(i);
            }
        }
        return list;
    }
     /*
     * 给定两个数，求两个数之间的素数
     * */
     public static void sieve3(int m,int n){
         List b=sieve4(n);
         for(int i=0;i<b.size();i++){
             if((int)(b.get(i))>=m){
                 System.out.println("素数"+b.get(i));
             }
         }
     }



    public static void main(String[] args){
        sieve(2);
//        test3(4);
    }

}
