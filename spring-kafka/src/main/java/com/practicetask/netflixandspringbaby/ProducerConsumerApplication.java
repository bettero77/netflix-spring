package com.practicetask.netflixandspringbaby;//package com.memorynotfound.kafka;

import com.practicetask.netflixandspringbaby.entity.Greeting;
import com.practicetask.netflixandspringbaby.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProducerConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerConsumerApplication.class, args);
    }

    @Autowired
    private Sender sender;

    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${partitioned.topic.name}")
    private String partionedTopicName;

    @Value(value = "${filtered.topic.name}")
    private String filteredTopicName;

    @Value(value = "${greeting.topic.name}")
    private String greetingTopicName;

    @Value("${app.topic.foo}")
    private String topic;

    @Bean
    public String run() {
        String data = "Spring Kafka Producer and Consumer Example";
        sender.sendBasicMessage(topic, data);
        sender.sendGreeting(greetingTopicName, new Greeting("Hi from", "Nik"));
        return null;
    }

}