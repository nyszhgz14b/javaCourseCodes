public class ThreadJoin {
    public static void main(String[] args){
        System.out.println("主线程执行开始--------------------");
        long begin = System.currentTimeMillis();
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        try {
            thread.join();
            System.out.println(myThread.getReturnValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行完成----------------共计用时:"+(System.currentTimeMillis()-begin));
    }
}
