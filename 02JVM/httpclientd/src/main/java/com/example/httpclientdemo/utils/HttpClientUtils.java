package com.example.httpclientdemo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class HttpClientUtils {
    public static String httpRequest(String url, String requestMethod, Map param) {
        CloseableHttpClient client = HttpClients.createDefault();
        String result = null;
        if ("POST".equals(requestMethod.trim())) {
            result = doPost(client, url, param);
        } else if ("GET".equals(requestMethod.trim())) {
            result =  doGet(client, url, param);
        }
        return result;
    }

    private static String doPost(CloseableHttpClient client, String url, Map param) {
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            ArrayList<NameValuePair> list = new ArrayList<>();
            Iterator iterator = param.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
                post.setEntity(urlEncodedFormEntity);
            }
            HttpResponse response = client.execute(post);
            if(response!=null){
                HttpEntity entity = response.getEntity();
                if(entity!=null){
                    result = EntityUtils.toString(entity,"UTF-8");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String doGet(CloseableHttpClient client, String url, Map param) {
        String result = null;
        Iterator iterator = param.entrySet().iterator();
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String, String>) iterator.next();
            builder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        if(!builder.toString().trim().equals("")){
            url =url+"?"+builder.toString();
            url= url.substring(0,url.length()-1);
        }
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            if(response!=null){
                HttpEntity entity = response.getEntity();
                if(entity!=null){
                    result = EntityUtils.toString(entity,"utf-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
