package com.kevinyin.learnsao.memcache.api;

import com.kevinyin.learnsao.memcache.factory.MemCacheFactory;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by kevinyin on 2017/7/31.
 */
public class MemCacheApiUtil {

    public static boolean setValue(String key, int exptime, String value) {
        MemcachedClient mcc = MemCacheFactory.getMemCacheClient();
        try {
            Future f = mcc.set(key, exptime, value);
            System.out.println("set status:" + f.get());
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            mcc.shutdown();
        }
    }

    public static Object getValue(String key) {
        MemcachedClient mcc = MemCacheFactory.getMemCacheClient();
        try {
            Object o = mcc.get(key);
            System.out.println(o.toString());
            return o;
        } catch (Exception e) {
            return null;
        } finally {
            mcc.shutdown();
        }
    }

    public static boolean addValue(String key, int exptime, String value) {
        MemcachedClient mcc = MemCacheFactory.getMemCacheClient();
        try {
            // 添加
            Future fo = mcc.add(key, exptime, value);
            boolean result = (Boolean) fo.get();
            // 打印状态
            System.out.println("add status:" + result);
            return result;
        } catch (Exception e) {
            return false;
        } finally {
            mcc.shutdown();
        }
    }

    public static boolean replaceValue(String key, int exptime, String value) {
        MemcachedClient mcc = MemCacheFactory.getMemCacheClient();
        try {
            // 添加
            Future fo = mcc.replace(key, exptime, value);
            boolean result = (Boolean) fo.get();
            // 打印状态
            System.out.println("add status:" + result);
            return result;
        } catch (Exception e) {
            return false;
        } finally {
            mcc.shutdown();
        }
    }

    public static boolean appendValue(String key, String value) {
        MemcachedClient mcc = MemCacheFactory.getMemCacheClient();
        try {
            // 添加
            Future fo = mcc.append(key, value);
            boolean result = (Boolean) fo.get();
            // 打印状态
            System.out.println("add status:" + result);
            return result;
        } catch (Exception e) {
            return false;
        } finally {
            mcc.shutdown();
        }
    }

    public static boolean prependValue(String key, String value) {
        MemcachedClient mcc = MemCacheFactory.getMemCacheClient();
        try {
            // 添加
            Future fo = mcc.prepend(key, value);
            boolean result = (Boolean) fo.get();
            // 打印状态
            System.out.println("add status:" + result);
            return result;
        } catch (Exception e) {
            return false;
        } finally {
            mcc.shutdown();
        }
    }

    public static CASValue getCasValue(String key) {
        MemcachedClient mcc = MemCacheFactory.getMemCacheClient();
        try {
            // 添加
            CASValue value = mcc.gets(key);
            System.out.println(key + " 's casValue = " + value);
            return value;
        } catch (Exception e) {
            return null;
        } finally {
            mcc.shutdown();
        }
    }

    public static boolean casUpdate(String key,int exptime, String value) {
        MemcachedClient mcc = MemCacheFactory.getMemCacheClient();
        try {
            // 添加
            CASValue casValue = mcc.gets(key);
            System.out.println(key + " 's casValue = " + value);
            CASResponse casResponse = mcc.cas(key,casValue.getCas(),exptime,value);
            return casResponse == CASResponse.OK;
        } catch (Exception e) {
            return false;
        } finally {
            mcc.shutdown();
        }
    }

}
