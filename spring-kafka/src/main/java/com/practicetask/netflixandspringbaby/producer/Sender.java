package com.practicetask.netflixandspringbaby.producer;//package com.memorynotfound.kafka.producer;

import com.practicetask.netflixandspringbaby.entity.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Greeting> kafkaTemplateGreeting;

    public void sendBasicMessage(String topic, String message){
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }

    public void sendGreeting(String topic, Greeting greeting){
        LOG.info("sending greeting='{}' to topic='{}'", greeting, topic);
        kafkaTemplateGreeting.send(topic, greeting);
    }
}
