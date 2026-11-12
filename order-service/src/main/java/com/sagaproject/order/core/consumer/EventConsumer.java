package com.sagaproject.order.core.consumer;

import com.sagaproject.order.core.service.EventService;
import com.sagaproject.order.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class EventConsumer {

    private EventService eventService;
    private final JsonUtil jsonUtil;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.notify-ending}"
    )
    public void consumeNotifyEndingEvent(String payload){

        log.info("Received notify ending event: {}", payload);
        var event = jsonUtil.toEvent(payload);
        eventService.notifyEnding(event);
    }
}
