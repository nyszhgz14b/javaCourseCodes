import java.util.concurrent.CountDownLatch;

public class MyCountDownLatchThread implements Runnable{
    private int returnValue =0;
    private final CountDownLatch countDownLatch;
    @Override
    public void run() {
        try {
            System.out.println("异步线程执行开始**************");
            Thread.sleep(3000);
           returnValue = Homework03.sum();
           countDownLatch.countDown();
            System.out.println("异步线程执行完成**************");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int getReturnValue() {
        return returnValue;
    }
    public MyCountDownLatchThread(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }
}
