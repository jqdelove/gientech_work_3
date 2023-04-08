package com.ross.consumer;

/**
 * @description: 服务消费者
 * @author: jinqi
 * @create: 2023-04-08 22:14
 **/
import com.ross.discovery.ServiceDiscovery;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class ServiceConsumer {
    public static void main(String[] args) throws Exception {
        // 发现服务
        ServiceDiscovery discovery = new ServiceDiscovery("127.0.0.1:2181");
        String address = discovery.discover();

        // 发起远程调用
        URL url = new URL("http://" + address + "/hello");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        // 处理响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();

        // 关闭服务发现
        discovery.close();
    }
}

