package async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncTaskService {
    private Logger logger = LoggerFactory.getLogger(AsyncTaskService.class);

    public String uuid() {
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
            logger.info("wording thread");
        } catch (Exception e) {
            logger.error("uuid error: ", e);
        }
        return UUID.randomUUID().toString();
    }

    @Async
    public String getUuid() {
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
            logger.info("wording thread");
        } catch (Exception e) {
            logger.error("uuid error: ", e);
        }
        return UUID.randomUUID().toString();
    }
}
