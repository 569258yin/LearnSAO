package com.kevinyin.learnsao.zookeeper.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;

import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by kevinyin on 2017/7/16.
 */
public class TestZkClient {

    public static void main(String[] args) {
        String serverList = null;
        ZkClient zkClient = new ZkClient(serverList);
        String PATH = "";
        //创建节点
        zkClient.createPersistent(PATH);
        //创建子节点
        zkClient.create(PATH + "/child","child znode", CreateMode.EPHEMERAL);
        //获取子节点
        List<String> children = zkClient.getChildren(PATH);
        //获取子节点的个数
        int childCount = zkClient.countChildren(PATH);
        //判断节点是否存在
        zkClient.exists(PATH);
        //写入数据
        zkClient.writeData(PATH + "/child","hello everyone");
        //读取节点数据
        Object obj = zkClient.readData(PATH + "/child");
        //删除节点
        zkClient.delete(PATH + "/child");

        //订阅子节点变化
        zkClient.subscribeChildChanges(PATH, new IZkChildListener() {
            public void handleChildChange(String s, List<String> list) throws Exception {

            }
        });

        //订阅节点数据发生变化
        zkClient.subscribeDataChanges(PATH, new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {

            }
        });

        //订阅节点连接及状态变化情况的示列代码
        zkClient.subscribeStateChanges(new IZkStateListener() {
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {

            }

            public void handleNewSession() throws Exception {

            }

            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {

            }
        });


    }

}
