package io.github.kimmking.gateway.outbound.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class NettyHttpClientInboundHandler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        System.out.println("channelActive is runing");
        /*URI uri = new URI(request.uri());
        DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, request.method(),
                uri.toASCIIString(),request.content());
        httpRequest.headers().add(request.headers());
        ctx.writeAndFlush(httpRequest).sync();
        ctx.close();*/
        super.channelActive(ctx);

    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("channelRead is runing");
        final FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
        ByteBuf content = fullHttpRequest.content();
        HttpHeaders headers = fullHttpRequest.headers();
        System.out.println(headers);
        System.out.println(content.toString(CharsetUtil.UTF_8));

    }
}