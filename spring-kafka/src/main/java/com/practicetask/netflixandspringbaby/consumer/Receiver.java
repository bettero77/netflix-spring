package com.practicetask.netflixandspringbaby.consumer;//package com.memorynotfound.kafka.consumer;

import com.practicetask.netflixandspringbaby.entity.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = "${topic.name.message}")
    public void listen(@Payload String message) {
        LOG.info("received message='{}'", message);
    }

    @KafkaListener(topics = "${topic.name.greeting}", containerFactory = "kafkaListenerContainerFactoryGreet")
    public void listenGreeting(@Payload Greeting greeting) {
        LOG.info("received greeting='{}'", greeting.toString());
    }
}
