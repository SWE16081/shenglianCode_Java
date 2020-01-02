
public class Main {

    public static void main(String[] args) {
        StopWatch s = new StopWatch();
        long start = s.start();
        System.out.println(start);

        if(s.ifStop()){
            long end = s.stop();
            System.out.println(end);
            System.out.println(s.getElapsedTime(start,end));
        }
    }
}
