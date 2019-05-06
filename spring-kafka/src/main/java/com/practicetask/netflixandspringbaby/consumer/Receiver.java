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

    @KafkaListener(topics = "${app.topic.foo}")
    public void listen(@Payload String message) {
        LOG.info("received message='{}'", message);
    }

    @KafkaListener(topics = "${greeting.topic.name}", containerFactory = "kafkaListenerContainerFactoryGreet")
    public void listenGreeting(@Payload Greeting greeting) {
//        LOG.info("received greeting='{}'", greeting);
        LOG.info(greeting.toString());
    }

}
