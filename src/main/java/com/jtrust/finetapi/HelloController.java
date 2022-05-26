package com.jtrust.finetapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jtrust.finetapi.service.QueueSqsService;
import com.jtrust.finetapi.sqs.QueuePayload;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class HelloController {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private QueueSqsService service;

    @GetMapping("/hello")
    @SwaggerPublicApi
    public String hello(@RequestParam(value = "name", defaultValue = "FINET API") String name) {
        log.info("=========hello===========");

        logger.debug("=========DEBUG===========");
        logger.info("=========INFO===========");
        logger.warn("=========WARNING==========");
        logger.error("=========ERROR===========");

        return String.format("Hello %s!", name);
    }

    @GetMapping("/send/message")
    @SwaggerPublicApi
    public QueuePayload sendPayload(@RequestBody QueuePayload payload) {
        log.info("=========sendPayload=========== {} ", payload);
        service.send(payload);
        return payload;
    }



    @SwaggerPublicApi
    @GetMapping("/api/myaccount/balance")
    public Map<String, Object> getMyBalanace() {
        log.info("=========getMyBalanace===========");
        final Map<String, Object> result = new HashMap<>();

        result.put("cif", "000000000");
        result.put("accountName", "JTRB Testing");
        result.put("balance", 134.53d);
        result.put("currency", "USD");
        result.put("status", 1);

        log.info(String.format("result output %s", gson.toJson(result)));
        return result;
    }

    @SwaggerPublicApi
    @PostMapping("/api/myaccount/balance/update")
    public Map<String, Object> updateMyBalanace(String cif, String account, Double amount, String ref) {
        log.info("=========updateMyBalanace===========");
        final Map<String, Object> result = new HashMap<>();

        result.put("cif", cif);
        result.put("accountName", "JTRB Testing");
        result.put("balance", amount);
        result.put("currency", "USD");
        result.put("status", 1);
        result.put("ref", ref);
        log.info(String.format("result output %s", gson.toJson(result)));
        return result;
    }
}
