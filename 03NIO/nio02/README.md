# 第三周作业
1.（必做）整合你上次作业的 httpclient/okhttp  
[作业1](https://github.com/nyszhgz14b/javaCourseCodes/tree/main/03NIO/nio02/src/main/java/io/github/kimmking/gateway/outbound/okhttp)  
请求地址：<http://127.0.0.1:8888/test>   
```
 <!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
<html><head>
<title>404 Not Found</title>
</head><body>
<h1>Not Found</h1>
<p>The requested URL /test was not found on this server.</p>
</body></html>

```

3.（必做）实现过滤器   
[作业3](https://github.com/nyszhgz14b/javaCourseCodes/blob/main/03NIO/nio02/src/main/java/io/github/kimmking/gateway/filter/HeaderHttpRequestFilter.java)   
请求地址：<http://127.0.0.1:8888/>   
```
请求地址不正确
```
对请求未包含test的进行过滤
