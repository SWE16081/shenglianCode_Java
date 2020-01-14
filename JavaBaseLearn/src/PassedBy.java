//public class PassedBy {
//
//    public static void passed(int m,int n){
//        int temp=m;
//        if(m>=n){
//            m=n;
//            n=temp;
//        }
//        while(n%m>0){
//            int b=m;
//            m=n%m;
//            n=b;
//        }
//        System.out.println("最大公因数"+m);
//
//    }
//    public static void main(String[] args){
//        passed(15,40);
//    }
//}
class PassedBy {
    public static int numPrimeArrangements(int n){
        int a=sieve(Math.floorMod(n, (int)Math.pow(10, 9)+7));
        return a;
    }

    public static int sieve(int n){
        boolean[] isPrime=new boolean[n+1];
        int cnt=0;
        for(int i=2;i<=n;i++){
            isPrime[i]=true;
        }
        for(int i=2;i<=Math.sqrt(n);i++){
            if(isPrime[i])
            {
                for(int j=2*i;j<=n;j+=i){
                    if(isPrime[j]==true){
                        isPrime[j]=false;
                        System.out.println("aa"+isPrime[j]);
                    }
                }
            }
        }
        for(int i=2;i<=n;i++){
            if(isPrime[i])
            {
                cnt++;
                System.out.println("质数"+i);
            }
        }
        return cnt;
    }

    public static void main(String[] args){
        int n=numPrimeArrangements(4);
        int sum=1;
        for(int i=1;i<=n;i++){
            sum=sum*i;
        }
        System.out.println(sum*(4-n));
    }
}
