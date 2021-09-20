package com.example.jvm;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class XlassLoad extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String suffix = ".xlass";
        String path = name.replaceAll("\\.","/");
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(path + suffix);
        try {
            byte[] bytes = new byte[resourceAsStream.available()];
            resourceAsStream.read(bytes);
            byte[] decode = decode(bytes);
            return defineClass(name,decode,0,decode.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name,e);
        }finally {
            close(resourceAsStream);
        }
    }

    /**
     * 解码
     * @param sourceByte
     * @return
     */
    private byte[] decode(byte[] sourceByte){
        byte[] target = new byte[sourceByte.length];
        for (int i = 0; i < sourceByte.length; i++) {
            target[i] = (byte) (255-sourceByte[i]);
        }
        return target;
    }
    private void close(Closeable closeable){
        if(closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
