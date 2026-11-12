package com.sagaproject.productvalidation.core.consumer;



import com.sagaproject.productvalidation.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class ProductValidationConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-success}"
    )
    public void consumeProductValidationSuccess(String payload){

        log.info("Received product validation success event: {}", payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-fail}"
    )
    public void consumeProductValidationFail(String payload){

        log.info("Received rollback product validation fail event: {}", payload);
        var event = jsonUtil.toEvent(payload);
        log.info(event.toString());
    }
}
