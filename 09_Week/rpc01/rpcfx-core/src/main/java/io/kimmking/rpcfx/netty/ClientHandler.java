package io.kimmking.rpcfx.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private String url=null;
    private String json=null;
    private CountDownLatch countDownLatch;

    public String getResult() {
        return result;
    }

    private String result;
    public ClientHandler(String url, String json,CountDownLatch countDownLatch){
        this.url=url;
        this.json =json;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse)
        {
            HttpResponse response = (HttpResponse) msg;
            System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
        }
        if(msg instanceof HttpContent)
        {
            HttpContent content = (HttpContent)msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(CharsetUtil.UTF_8));
            result = buf.toString(CharsetUtil.UTF_8);
            countDownLatch.countDown();
            buf.release();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(this.url);
        URI uri = new URI(this.url);
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, uri.toASCIIString(), Unpooled.wrappedBuffer(this.json.getBytes(StandardCharsets.UTF_8)));
        request.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=utf-8")
                //开启长连接
                .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                //设置传递请求内容的长度
                .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        ChannelFuture channelFuture = ctx.writeAndFlush(request);
        System.out.println(channelFuture.isSuccess());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
