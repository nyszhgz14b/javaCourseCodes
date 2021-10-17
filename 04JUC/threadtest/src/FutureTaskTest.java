import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("主线程执行开始--------------------");
        long begin = System.currentTimeMillis();
        FutureTask<Integer> task = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(task);
        thread.start();
        Object o = task.get();
        System.out.println(o);
        System.out.println("主线程执行完成----------------共计用时:"+(System.currentTimeMillis()-begin));
    }
}
