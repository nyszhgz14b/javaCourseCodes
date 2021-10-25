import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    public static final ReentrantReadWriteLock lock =  new ReentrantReadWriteLock();
    public static void main(String[] args){
        MyLockThread thread = new MyLockThread();
        Thread thread1 = new Thread(thread);
        thread1.start();
            lock.writeLock().lock();
            System.out.println(thread.getReturnValue());
            lock.writeLock().unlock();

    }
}
