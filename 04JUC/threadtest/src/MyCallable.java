import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println("异步线程执行开始**************");
        Thread.sleep(3000);
        System.out.println("异步线程执行完成**************");
        return Homework03.sum();
    }
}
