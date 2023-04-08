package com.ross.discovery;

/**
 * @description: 服务发现和注册
 * @author: jinqi
 * @create: 2023-04-08 22:14
 **/
import org.apache.zookeeper.*;

public class ServiceDiscovery {
    private ZooKeeper zooKeeper;
    private String servicePath = "/services/my-service";

    public ServiceDiscovery(String zkAddress) throws Exception {
        // 连接ZooKeeper服务器
        this.zooKeeper = new ZooKeeper(zkAddress, 5000, null);
    }

    public String discover() throws Exception {
        // 获取服务地址
        byte[] data = zooKeeper.getData(servicePath, true, null);
        String address = new String(data);
        System.out.println("Service found: " + address);
        return address;
    }

    public void close() throws Exception {
        this.zooKeeper.close();
    }
}

