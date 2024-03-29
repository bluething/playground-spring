package io.github.bluething.playground.spring.scheduler.reflectoringscheduler.jobs;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
class ScheduledJobs {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduledJobs.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelay = 2000)
    void reportCurrentTimeFixedDelay() {
        LOG.info("reportCurrentTimeFixedDelay - The time is now {}", sdf.format(new Date()));
    }

    @Scheduled(fixedRate = 3000)
    void reportCurrentTimeFixedRate() {
        LOG.info("reportCurrentTimeFixedRate - The time is now {}", sdf.format(new Date()));
    }

    @Scheduled(fixedRate = 3000)
    void reportCurrentTimeFixedRateAndSleep() throws InterruptedException {
        LOG.info("reportCurrentTimeFixedRateAndSleep - The time is now {}", sdf.format(new Date()));
        Thread.sleep(4000);
    }

    @Scheduled(fixedRate = 3000)
    @Async
    void reportCurrentTimeFixedRateAndSleepWithAsync() throws InterruptedException {
        LOG.info("reportCurrentTimeFixedRateAndSleepWithAsync - The time is now {}", sdf.format(new Date()));
        Thread.sleep(4000);
    }

    @Scheduled(fixedDelay = 2000, initialDelay = 1000)
    @Async
    void reportCurrentTimeFixedDelayWithInitialDelay() {
        LOG.info("reportCurrentTimeFixedDelayWithInitialDelay - The time is now {}", sdf.format(new Date()));
    }

    @Scheduled(fixedRateString = "${interval}", initialDelay = 1000)
    @Async
    void reportCurrentTimeFixedRateStringWithInitialDelayWithIntervalFromProperty() {
        LOG.info("reportCurrentTimeFixedRateStringWithInitialDelayWithIntervalFromProperty - The time is now {}", sdf.format(new Date()));
    }

    @Scheduled(cron = "${interval-in-cron}")
    @Async
    void reportCurrentTimeCronWithIntervalFromProperty() {
        LOG.info("reportCurrentTimeCronWithIntervalFromProperty - The time is now {}", sdf.format(new Date()));
    }

    @Scheduled(fixedRateString = "${interval}")
    @SchedulerLock(name = "ScheduledJobsLock")
    @Async
    void reportCurrentTimeCronWithIntervalFromPropertyLockedByShedLock() {
        LOG.info("reportCurrentTimeCronWithIntervalFromPropertyLockedByShedLock - The time is now {}", sdf.format(new Date()));
    }
}
