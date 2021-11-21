package io.kimmking.rpcfx.netty;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

public class NettyClient {
    private  int port ;
    private String host  ;
    private String url;
    private String json;
    private CountDownLatch countDownLatch;
    public NettyClient(String host,int port,String url,String json){
        this.host=host;
        this.port = port;
        this.url= url;
        this.json = json;
    }
   public String  start(){
        countDownLatch = new CountDownLatch(1);
       ClientHandler clientHandler = new ClientHandler(url, json, countDownLatch);
       NioEventLoopGroup group = new NioEventLoopGroup();
       Bootstrap bootstrap = new Bootstrap();
       System.out.println(host+" "+port);
       bootstrap.group(group).channel(NioSocketChannel.class)
               .remoteAddress(new InetSocketAddress(host,port))
               .option(ChannelOption.SO_KEEPALIVE,true)
               .handler(new ChannelInitializer<Channel>() {
                   @Override
                   protected void initChannel(Channel channel) throws Exception {
                       channel.pipeline().addLast(new HttpResponseDecoder());
                       channel.pipeline().addLast(new HttpRequestEncoder());
                       channel.pipeline().addLast(clientHandler);
                   }
               });
       try {
           ChannelFuture future = bootstrap.connect().sync();
           future.channel().closeFuture().sync();
           countDownLatch.await();
           return clientHandler.getResult();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }finally {
           group.shutdownGracefully();
       }
       return null;
   }
}
