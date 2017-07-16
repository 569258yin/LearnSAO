package com.kevinyin.learnsao.zookeeper.zkClient;

import org.I0Itec.zkclient.ZkClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by kevinyin on 2017/7/16.
 */
public class RegisterService {

    public static void main(String[] args) throws UnknownHostException {
        String serverList = "192.168.136.130:2181";
        String PATH = "/configcenter";
        ZkClient zkClient = new ZkClient(serverList);
        boolean rootExists = zkClient.exists(PATH);
        if (!rootExists){
            zkClient.createPersistent(PATH);
        }
        String serviceName = "service-A";
        boolean serviceExists = zkClient.exists(PATH + "/" + serviceName);
        if (!serviceExists){
            zkClient.createPersistent(PATH + "/" + serviceName);
        }

        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress().toString();

        //创建当前服务器节点
        zkClient.createEphemeral(PATH + "/" + serviceName + "/" +ip);
    }
}
