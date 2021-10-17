import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程执行开始--------------------");
        long begin = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        MyCountDownLatchThread thread = new MyCountDownLatchThread(countDownLatch);
        Thread thread1 = new Thread(thread);
        thread1.start();
        countDownLatch.await();
        System.out.println("返回值:"+thread.getReturnValue());
        System.out.println("主线程执行完成----------------共计用时:"+(System.currentTimeMillis()-begin));

    }
}
