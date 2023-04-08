package com.ross.provider;

/**
 * @description: 服务提供者
 * @author: jinqi
 * @create: 2023-04-08 22:13
 **/
import org.apache.zookeeper.*;

public class ServiceProvider {
    private ZooKeeper zooKeeper;
    private String servicePath = "/services/my-service";

    public ServiceProvider(String zkAddress) throws Exception {
        // 连接ZooKeeper服务器
        this.zooKeeper = new ZooKeeper(zkAddress, 5000, null);

        // 创建服务节点
        if (this.zooKeeper.exists(servicePath, false) == null) {
            this.zooKeeper.create(servicePath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 注册服务
        String address = "127.0.0.1:8080";
        String serviceNode = servicePath + "/" + address;
        String node = this.zooKeeper.create(serviceNode, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Service registered: " + node);
    }

    public void close() throws Exception {
        this.zooKeeper.close();
    }
}

