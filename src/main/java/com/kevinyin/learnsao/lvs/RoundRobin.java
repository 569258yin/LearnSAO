package com.kevinyin.learnsao.lvs;

import java.util.*;

/**
 *
 * Created by kevinyin on 2017/7/16.
 */
public class RoundRobin {

    private static Integer pos = 0;
    // 1.轮询
    public static String testRoundRobin(){
        //先复制一份，避免由于服务器上下文不一致导致并发问题
        Map<String,Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(TData.serverWeightMap);

        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        String server = null;

        synchronized (pos){
            if (pos >= keySet.size()){
                pos = 0;
            }
            server = keyList.get(pos);
            pos ++;
        }
        return server;
    }
    //2.随机数法
    public static String testRandom(){
        Map<String,Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(TData.serverWeightMap);

        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        Random random = new Random();
        int randomPos = random.nextInt(keyList.size());

        String server = keyList.get(randomPos);
        return server;
    }
    //3.源地址hash 对于同一个客户端，当服务器列表没发生变化时，总是访问相同的服务器
    public static String testConsumerHash(String remoteIp){
        Map<String,Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(TData.serverWeightMap);

        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        int hashCode = remoteIp.hashCode();
        int serverListSize = keyList.size();
        int serverPos = hashCode % serverListSize;

        return keyList.get(serverPos);
    }
    //加权轮询
    public static String testWeightRoundRobin(){
        Map<String,Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(TData.serverWeightMap);

        Set<String> keySet = serverMap.keySet();
        Iterator<String> it = keySet.iterator();
        ArrayList<String> keyList = new ArrayList<String>();
        //加权  依权重放入n份
        while (it.hasNext()){
            String server = it.next();
            Integer weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                keyList.add(server);
            }
        }

        String server = null;

        synchronized (pos){
            if (pos >= keySet.size()){
                pos = 0;
            }
            server = keyList.get(pos);
            pos ++;
        }
        return server;
    }

    //2.随机数法
    public static String testWeightRandom(){
        Map<String,Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(TData.serverWeightMap);

        Set<String> keySet = serverMap.keySet();
        Iterator<String> it = keySet.iterator();
        ArrayList<String> keyList = new ArrayList<String>();
        //加权  依权重放入n份
        while (it.hasNext()){
            String server = it.next();
            Integer weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                keyList.add(server);
            }
        }

        Random random = new Random();
        int randomPos = random.nextInt(keyList.size());

        String server = keyList.get(randomPos);
        return server;
    }
}
