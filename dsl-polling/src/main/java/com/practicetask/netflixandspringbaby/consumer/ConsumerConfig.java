package com.practicetask.netflixandspringbaby.consumer;

import com.practicetask.netflixandspringbaby.service.EmployeeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

@Configuration
public class ConsumerConfig {
    static Log log = LogFactory.getLog(ConsumerConfig.class);

    @Autowired
    EmployeeService employeeService;

    public MessageSource<File> inputChannel() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        File file = new File("./dsl-polling/src/main/resources");
        messageSource.setDirectory(file);
        return messageSource;
    }

    @Bean
    IntegrationFlow consumer() {
        return IntegrationFlows.from(inputChannel(), p -> p.poller(Pollers.fixedDelay(500)))
                .filter(file -> ((File) file).getName().endsWith(".csv"))
                .handle(p ->
                {
                    File file = (File) p.getPayload();
                    String line;

                    try (Scanner scn = new Scanner(file)) {
//                        for (int i = 0; (line = scn.nextLine()) != null; i++) {
                        for (int i = 0; scn.hasNext(); i++) {
                            line = scn.nextLine();
                            if (i == 0)
                                continue;
                            String[] country = line.split(";");
                            log.info("!!!!!!!!!!!!!!!! Line: " + country[0] + " " + country[1]);
                            employeeService.changeEmployeeWorkspace(country[0], country[1]);
                        }
                    } catch (Exception e) {
                        log.error(e + " - " + e.getMessage());
                    }
                })
                .get();
    }
}
