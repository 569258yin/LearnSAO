package com.kevinyin.learnsao.zookeeper;

import org.apache.zookeeper.KeeperException;

/**The DataMonitorListener interface,
 * on the other hand, is not part of the the ZooKeeper API.
 * It is a completely custom interface,
 * designed for this sample application.
 * The DataMonitor object uses it to communicate back to its container,
 * which is also the the Executor object.
 * Created by kevinyin on 2017/7/16.
 */
public interface DataMonitorListener {
    /**
     * The existence status of the node has changed.
     */
    void exists(byte data[]);
    /**
     * The ZooKeeper session is no longer valid.
     *
     * @param rc
     * the ZooKeeper reason code
     */
    void closing(KeeperException.Code rc);
}
