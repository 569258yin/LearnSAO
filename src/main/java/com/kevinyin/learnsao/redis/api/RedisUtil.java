package com.kevinyin.learnsao.redis.api;

import redis.clients.jedis.Jedis;

/**
 * Created by kevinyin on 2017/8/1.
 */
public class RedisUtil {

    public static Jedis getConnect(){
        Jedis jedis = new Jedis("192.168.137.155",6379);
        jedis.auth("root");
        System.out.println("connect success");
        System.out.println("服务器正在运行:"+jedis.ping());
        return jedis;
    }

    public static boolean setValue(String key,String value){
        Jedis jedis =getConnect();
        System.out.println(jedis.set(key,value));
        return true;
    }



}
