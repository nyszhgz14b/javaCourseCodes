1 SerialGC

	单线程的垃圾回收器，在其进行垃圾回收时会暂停工作线程，直至收集完成

	特点：CPU单核使用最高，停顿时间较长

	适用场景:客户端应用

	使用方法：-XX:+UseSerialGC

2 ParallelGC

	采用多线程扫描并压缩堆

	特点：SWT较短，回收效率高，吞吐量高,新生代和老年代都是并行运行

	适用场景：服务端，对吞吐量有要求的

	使用方法：-XX:+UseParallelGC

3 CMSGC

	采用多线程去扫描堆，对未使用对象进行回收

	特点:响应时间快，垃圾回收时间短,存在碎片化问题，在长时间运行的情况下会发生full GC

	适用场景：服务端，对响应时间有要求的

	使用方法：-XX:+UseConcMarkSweepGC

4 G1GC

	在G1中，堆被划分为许多连续小块区域

	特点:支持大堆，高吞吐量，大部分情况可控制垃圾回收时间 -XX:MaxGCPauseMillis

	使用方法：-XX:+UseG1GC

