package com.oznur.finalcase.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {
    // adlarını değiş
    @KafkaListener(
            topics = "infoLogs",
            groupId = "logs-for-print"
    )
    public void consumeInfo(String message) {
        log.info(message);
    }
    @KafkaListener(
            topics = "errorLogs",
            groupId = "logs-for-print"
    )
    public void consumeError(String message) {
        log.error(message);
    }
    @KafkaListener(
            topics = "warnLogs",
            groupId = "logs-for-print"
    )
    public void consumeWarn(String message) {
        log.warn(message);
    }

}
