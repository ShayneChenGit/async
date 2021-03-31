package async.controller;

import async.service.AsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    AsyncTaskService asyncTaskService;

    @GetMapping("blockingRequest")
    public String blockingRequest() {
        logger.info("blockingRequest: start");
        String uuid = asyncTaskService.uuid();
        logger.info("blockingRequest: end");
        return uuid;
    }

    @GetMapping("asyncMethod")
    public String asyncMethod() {
        logger.info("asyncMethod: start");
        String uuid = asyncTaskService.getUuid();
        logger.info("asyncMethod: end");
        return uuid;
    }

    @GetMapping("callable")
    public Callable<String> callable() {
        logger.info("callable: start");

        Callable<String> callable = () -> {
            String uuid = asyncTaskService.uuid();
            logger.info("child task thread");
            return uuid;
        };

        logger.info("callable: end");
        return callable;
    }

    @GetMapping("defferedResultRequest")
    public DeferredResult<String> defferedResultRequest() {
        logger.info("defferedResultRequest: start");

        DeferredResult<String> deferredResult = new DeferredResult<>();

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService.submit(() -> {
//            asyncTaskService.uuid();
//            deferredResult.setResult("watch:complete");
//        });

        new Thread(() -> {
            asyncTaskService.uuid();
            deferredResult.setResult("defferedResultRequest: complete");
        }).start();

        deferredResult.onTimeout(() -> deferredResult.setResult("defferedResultRequest: timeout"));

        deferredResult.onCompletion(() -> deferredResult.setResult("defferedResultRequest: complete"));

        deferredResult.onError(throwable -> deferredResult.setErrorResult("defferedResultRequest: error"));

        logger.info("defferedResultRequest: end");

        return deferredResult;
    }
}
