package com.practicetask.netflixandspringbaby.consumer;

import com.practicetask.netflixandspringbaby.domain.Employee;
import com.practicetask.netflixandspringbaby.service.EmployeeService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;

//@EnableKafka
@Configuration
public class ConsumerConfiguration {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Value("${spring.kafka.bootstrap-servers:localhost:9093}")
    private String bootstrapServers;

    @Value("${spring.kafka.topic:event-stream}")
    private String kafkaTopic;

    @Autowired
    EmployeeService employeeService;

    @Bean
    public IntegrationFlow kafkaReader() {
        return IntegrationFlows
                .from(Kafka.messageDrivenChannelAdapter(consumerFactory(), kafkaTopic))
//                .from(Kafka.inboundChannelAdapter(consumerFactory(), "event-stream"), e -> e.poller(Pollers.fixedDelay(100)))
                .handle(p ->
                        {
                            Employee employee = (Employee) p.getPayload();
                            log.info("receive message: "+ employee);
                            employeeService.addEmployee(employee);
                        })
//                .channel("queureader")
                .get();
    }

//    @ServiceActivator(inputChannel = "queureader")
//    public void Print(Message<?> msg)  {
//        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + msg.getPayload());
//    }

    public ConsumerFactory<String, Employee> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "event-stream");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 5000);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Employee.class));
    }

//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.getContainerProperties().setPollTimeout(3000);
//        return factory;
//    }
}
