import java.util.Scanner;

public class StopWatch {
    long startTime;
    long endTime;
//    无参构造函数，初始化startTime
    public StopWatch(){
        this.startTime = System.currentTimeMillis();
    }
//    start方法，重设时间
    public long start(){
        this.startTime = System.currentTimeMillis();
        return startTime;
    }
    public long stop(){
        this.endTime = System.currentTimeMillis();
        return endTime;
    }
    public long getElapsedTime(long startTime,long endTime){
        return (endTime - startTime);
    }
    public boolean ifStop(){
        Scanner sc = new Scanner(System.in);
        if(sc.nextBoolean()){
            return true;
        }else {
            return false;
        }
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
