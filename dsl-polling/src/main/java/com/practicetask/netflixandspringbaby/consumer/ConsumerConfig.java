package com.practicetask.netflixandspringbaby.consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;

import java.io.File;
import java.util.Scanner;

@Configuration
public class ConsumerConfig {
    static Log log = LogFactory.getLog(ConsumerConfig.class);

    public MessageSource<File> inputChannel() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        File file = new File("C:\\Users\\Nikita_Orlovetc\\Documents\\netflixandspringbaby\\dsl-polling\\src\\main\\resources");
        //can filter here or in flow for file .csv format
        messageSource.setDirectory(file);
        return messageSource;
    }

    @Bean
    IntegrationFlow consumer() {
        return IntegrationFlows.from(inputChannel(), p -> p.poller(Pollers.fixedDelay(500)))
                .handle(p ->
                {
                    File file = (File) p.getPayload();
                    String line;

                    try (Scanner scn = new Scanner(file)) {
                        for (int i = 0; (line = scn.nextLine()) != null;i++) {
                            if (i == 0)
                                continue;
                            String[] country = line.split(";");
                            log.info("Line: " + country[0] + " " + country[1]);
                            //service call for save through repository
                        }
                    } catch (Exception e) {
                        log.error(e + " - " + e.getMessage());
                    }
                })
                .get();
    }
}
