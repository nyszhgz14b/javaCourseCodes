import java.util.concurrent.locks.Lock;

public class MyLockThread implements Runnable{
    private int returnValue =0;
    @Override
    public  void run() {
        LockTest.lock.writeLock().lock();
        try {
            System.out.println("异步线程执行开始**************");
            Thread.sleep(3000);
           returnValue = Homework03.sum();
            System.out.println("异步线程执行完成**************");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            LockTest.lock.writeLock().unlock();
        }
    }
    public int getReturnValue() {
        return returnValue;
    }

}
