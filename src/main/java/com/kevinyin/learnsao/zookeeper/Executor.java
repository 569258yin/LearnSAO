package com.kevinyin.learnsao.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.*;

/**
 * Created by kevinyin on 2017/7/16.
 */
public class Executor implements Watcher,Runnable,DataMonitorListener{
    String znode;

    DataMonitor dm;

    ZooKeeper zk;

    String filename;

    String exec[];

    Process child;

    public Executor(String hostPort,String zNode,
                    String filename, String[] exec) throws IOException {
        this.filename = filename;
        this.exec = exec;
        this.zk = new ZooKeeper(hostPort,3000,this);
        dm = new DataMonitor(zk,zNode,null,this);
    }

    public static void main(String[] args) {
        if (args.length < 4){
            System.err.println("USAGE: Executor ...");
            System.exit(2);
        }
        String hostName = args[0];
        String znode = args[1];
        String fileName = args[2];
        String[] exec = new String[args.length - 3];
        System.arraycopy(args,3,exec,0,exec.length);

        try {
            new Executor(hostName,znode,fileName,exec).run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exists(byte[] data) {
        if (data == null){
            if (child != null){
                System.out.println("Killing process");
                child.destroy();
                try {
                    child.waitFor();
                }catch (InterruptedException e){

                }
            }
            child = null;
        }else {
            if (child != null){
                System.out.println("Stopoping child");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(filename);
                fos.write(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
            try {
                System.out.println("Starting child");
                child = Runtime.getRuntime().exec(exec);
                new StreamWriter(child.getInputStream(), System.out);
                new StreamWriter(child.getErrorStream(), System.err);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closing(KeeperException.Code rc) {
        synchronized (this){
            notifyAll();
        }
    }

    public void run() {
        try {
            synchronized (this){
                while (!dm.dead){
                    wait();
                }
            }
        }catch (InterruptedException e){

        }
    }
    /***************************************************************************
     * We do process any events ourselves, we just need to forward them on.
     *
     * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.proto.WatcherEvent)
     */
    public void process(WatchedEvent watchedEvent) {
        dm.process(watchedEvent);
    }


    static class StreamWriter extends Thread {
        OutputStream os;

        InputStream is;

        StreamWriter(InputStream is, OutputStream os) {
            this.is = is;
            this.os = os;
            start();
        }

        public void run() {
            byte b[] = new byte[80];
            int rc;
            try {
                while ((rc = is.read(b)) > 0) {
                    os.write(b, 0, rc);
                }
            } catch (IOException e) {
            }

        }
    }
}
