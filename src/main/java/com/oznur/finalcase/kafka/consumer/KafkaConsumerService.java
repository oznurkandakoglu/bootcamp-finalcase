package com.oznur.finalcase.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(
            topics = "infoLogs",
            groupId = "infoLogs-for-print"
    )
    public void consumeInfo(String message) {
        log.info(message);
    }
    @KafkaListener(
            topics = "errorLogs",
            groupId = "errorLogs-for-print"
    )
    public void consumeError(String message) {
        log.error(message);
    }
}
