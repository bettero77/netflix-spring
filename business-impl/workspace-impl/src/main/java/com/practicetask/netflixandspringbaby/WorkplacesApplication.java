package com.practicetask.netflixandspringbaby;

import com.practicetask.netflixandspringbaby.domain.OSFamily;
import com.practicetask.netflixandspringbaby.domain.Workspace;
import com.practicetask.netflixandspringbaby.repository.WorkplaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableEurekaClient
public class WorkplacesApplication {
    private static final Logger log = LoggerFactory.getLogger(WorkplacesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WorkplacesApplication.class, args);
    }

//    @Bean
    public String logTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            log.info("New customer successfully registered");
            log.warn("User password will expire in two days");
            log.error("Billing system is not available");

            Thread.sleep(200);
        }
        return null;
    }

//    @Bean
    public String demo(WorkplaceRepository repository) {

        repository.save(new Workspace(5, 5, "888", OSFamily.OSX));

        log.info("!!!!!!!!!!!!!!Customers found with findAll():");
        log.info("!!!!!!!!!!!!!!-------------------------------");
        for (Workspace workspace : repository.findAll()) {
            log.info(workspace.toString());
        }

        repository.findById(18)
                .ifPresent(workspace -> {
                    log.info("!!!!!!!!!!!!!!!!!Customer found with findById(18L):");
                    log.info("!!!!!!!!!!!!!!!!!--------------------------------");
                    log.info(workspace.toString());
                    log.info("");
                });

        return null;
    }
}
