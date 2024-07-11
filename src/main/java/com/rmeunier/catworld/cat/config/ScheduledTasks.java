package com.rmeunier.catworld.cat.config;

import com.rmeunier.catworld.cat.service.CatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAsync
@EnableScheduling
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final CatService catService;

    @Autowired
    public ScheduledTasks(CatService catService) {
        this.catService = catService;
    }

    @Async
    // Run every day at 1:01 AM
    @Scheduled(cron = "0 1 1 * * ?")
    public void updateCatsAges() {
        logger.info("Updating cats ages...");
        catService.updateAllCatAges();
        logger.info("Cats ages updated.");
    }
}
