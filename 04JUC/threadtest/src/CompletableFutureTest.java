import java.util.concurrent.*;

public class CompletableFutureTest  {
    public static void main(String[] args){
        System.out.println("主线程执行开始--------------------");
        long begin = System.currentTimeMillis();
        try {
            Integer integer = CompletableFuture.supplyAsync(Homework03::sum).get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行完成----------------共计用时:"+(System.currentTimeMillis()-begin));
    }

}
