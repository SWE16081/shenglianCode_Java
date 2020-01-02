public class Main {
    public static void main(String[] args) {
        Knight k = new Knight("JJCai");
        Monster m1 = new Monster("怪一",40,22,8,10);
        Monster m2 = new Monster("怪二",30,15,5,20);
        Monster m3 = new Monster("怪三",50,30,15,40);
        Monster m4 = new Monster("怪四",40,30,18,60);
        k.fight(m1);
        k.fight(m2);
        k.fight(m3);
        k.fight(m4);
        k.show();
        m1.show();
        m2.show();
        m3.show();
        m4.show();
    }
}
