package com.wuzz.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/5
 * Time: 11:27
 * Description 描述:
 */
public class ConnectionDemo {

    public static void main(String[] args) {
        try {
            final CountDownLatch countDownLatch=new CountDownLatch(1);
            ZooKeeper zooKeeper=
                    new ZooKeeper("192.168.1.101:2181," +
                            "192.168.1.102:2181,192.168.1.103:2181",
                            4000, new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            if(Event.KeeperState.SyncConnected==event.getState()){
                                //如果收到了服务端的响应事件，连接成功
                                countDownLatch.countDown();
                            }
                        }
                    });
            System.out.println(zooKeeper.getState());//CONNECTING
            countDownLatch.await();
            System.out.println(zooKeeper.getState());//CONNECTED

            //添加节点
            zooKeeper.create("/zk-wuzz","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            Thread.sleep(1000);
            Stat stat=new Stat();

            //得到当前节点的值
            byte[] bytes=zooKeeper.getData("/zk-wuzz",null,stat);
            System.out.println(new String(bytes)); // 0

            //修改节点值
            zooKeeper.setData("/zk-wuzz","1".getBytes(),stat.getVersion());

            //得到当前节点的值
            byte[] bytes1=zooKeeper.getData("/zk-wuzz",null,stat);
            System.out.println(new String(bytes1)); // 1

//            zooKeeper.delete("/zk-wuzz",stat.getVersion());

            zooKeeper.close();

            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}


