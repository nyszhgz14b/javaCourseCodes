package io.kimmking.kmq.queue;

import io.kimmking.kmq.core.KmqMessage;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class MyQueue {
    //容量
    private  AtomicInteger capacity = new AtomicInteger(-1);
    //保存信息
    private KmqMessage[] message;

    private AtomicInteger rigthIndex = new AtomicInteger(0); //最后一位
    private AtomicInteger leftIndex = new AtomicInteger(-1); //消费位置
    public MyQueue(int capacity) {
        super();
        this.capacity = new AtomicInteger(capacity);
        this.message = new KmqMessage[capacity];
    }

    public boolean offer(KmqMessage e) {
        rigthIndex.getAndIncrement();
        //超过长度进行一次扩容
        if(rigthIndex.intValue()>= capacity.intValue()){
            KmqMessage [] temp = new KmqMessage[capacity.intValue()+10];
            temp = Arrays.copyOfRange(message,0,message.length);
            message = temp;
            capacity = new AtomicInteger(capacity.intValue()+10);
        }
        message[rigthIndex.intValue()] = e ;

        return true;
    }

    public KmqMessage poll() {

        return message[leftIndex.incrementAndGet()];
    }

    public KmqMessage poll(long timeout, TimeUnit unit) {
       /* System.out.println(leftIndex.get());
        System.out.println(capacity.get());*/
        if(leftIndex.get()>=(capacity.get()-1)){
            return null;
        }
        return message[leftIndex.incrementAndGet()];
    }
}
