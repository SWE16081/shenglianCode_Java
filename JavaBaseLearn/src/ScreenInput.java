import java.util.Scanner;

/*
* 键盘输入
* */
public class ScreenInput {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int i=scanner.nextInt();//输入整数
        double d=scanner.nextDouble();//输入浮点类型
        String s=scanner.next();//输入字符串
        System.out.println(i);
        System.out.println(d);
        System.out.println(s);

    }
}
