package io.github.kimmking.gateway.outbound.netty4;

import io.github.kimmking.gateway.filter.HeaderHttpResponseFilter;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.filter.HttpResponseFilter;
import io.github.kimmking.gateway.inbound.HttpInboundHandler;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class NettyHandler {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private ExecutorService proxyService;
    private List<String> backendUrls;


    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public NettyHandler(List<String> backends){
        backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());

        int core = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;

        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(core,core,keepAliveTime, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);
    }
    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter){
        String bankendUrl = router.route(backendUrls);
        filter.filter(fullRequest, ctx);
       String ip = null;
       int port =80;
        if(bankendUrl.startsWith("http://")){
            ip = bankendUrl.substring(7,bankendUrl.length());
        }
        if(ip.contains(":")){
            port = Integer.valueOf(ip.substring(ip.indexOf(":")+1));
            ip = ip.substring(0,ip.indexOf(":"));
        }
        System.out.println(ip);
        System.out.println(port);
        try {
            int finalPort = port;
            String finalIp = ip;
            proxyService.submit(() -> {
                try {
                    new NettyHttpClient().connect(finalIp, finalPort,fullRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
