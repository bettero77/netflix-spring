package com.practicetask.netflixandspringbaby;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {

    @Value("${kafka.topic:" + "test1" + "}")
    private String topic;

    @Value("${kafka.address:localhost:9093}")
    private String brokerAddress;

    @Value("${zookeeper.address:localhost:2181}")
    private String zookeeperAddress;

    KafkaConfig() {
    }

    public KafkaConfig(String t, String b, String zk) {
        this.topic = t;
        this.brokerAddress = b;
        this.zookeeperAddress = zk;
    }

    public String getTopic() {
        return topic;
    }

    public String getBrokerAddress() {
        return brokerAddress;
    }

    public String getZookeeperAddress() {
        return zookeeperAddress;
    }
}
