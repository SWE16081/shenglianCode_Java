import java.util.Random;

/*
* 随机数
* */
public class RandomTest {
    public static void main(String[] args){
        /*
        * 使用java.util.Random
        * */
        Random random=new Random();//
        int i=random.nextInt(10);//随机整数 指定随机数范围
        double d=random.nextDouble();//随机实数
        boolean b=random.nextBoolean();//随机布尔值
        System.out.println(i);
        System.out.println(d);
        System.out.println(b);
        /*
        * 使用Math类的random方法
        * Math.random()默认产生大于等于0.0且小于1.0之间的随机double型随机数
        * 强制转换成int后整数部分赋值给int类型变量，小数点之后的小数部分将会丢失。
        *如果要生成[0,n]的随机整数的话，只需要Math.random()乘以n+1，生成[0,n+1)的浮点数，
        * 再强制类型转换为int类型，只取其整数部分，即可得到[0,n]的整数。
        * */
        //[0，1.0)
        double d2=Math.random();
        System.out.println(d2);
        // 0-1
        int rand1 = (int)(Math.random() *2);
        System.out.println(rand1);
        //1-6
        int rand2 =1 + (int)(Math.random()*6);
        System.out.println(rand2);
        // 1-54
        int rand3 =1 + (int)(Math.random() * 54);
        System.out.println(rand3);

        int a=random.nextInt(200)-100;
        int a2=random.nextInt(200)-100;
        if(a>=a2)
            System.out.println(a);
        else
            System.out.println(a2);
    }
}
