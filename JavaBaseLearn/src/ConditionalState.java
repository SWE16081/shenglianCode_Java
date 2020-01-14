import java.util.Scanner;
/*
* 三目运算符和if
* */
public class ConditionalState {
    public static void  main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int a=scanner.nextInt();
        int b=scanner.nextInt();
        int c=scanner.nextInt();
//        if(a>b){
//            if(a>c){
//                System.out.println(a);
//            }else{
//                System.out.println(c);
//            }
//        }else{
//            if(b>c){
//                System.out.println(b);
//            }else{
//                System.out.println(c);
//            }
//        }
        int s=a>b?a>c?a:c:b>c?b:c;
        System.out.println(s);
    }
}
