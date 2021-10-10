package io.github.kimmking.gateway.filter;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        if(!fullRequest.uri().contains("/test")){
            String content = "请求地址不正确";
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            System.out.println(response.headers());
            response.headers().setInt("Content-Length", content.getBytes(StandardCharsets.UTF_8).length);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

        }
        fullRequest.headers().set("mao", "soul");
    }
}
