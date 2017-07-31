package com.kevinyin.learnsao.memcache.factory;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by kevinyin on 2017/7/31.
 */
public class MemCacheFactory {
    public static final String HOST = "192.168.137.87";
    public static final int PORT = 11211;

    public static MemcachedClient getMemCacheClient(){
        try {
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(HOST,PORT));
            return mcc;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }


}
