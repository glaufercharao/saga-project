package com.sagaproject.payment.core.consumer;


import com.sagaproject.payment.core.service.PaymentService;
import com.sagaproject.payment.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class PaymentConsumer {
    private final PaymentService paymentService;
    private final JsonUtil jsonUtil;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.payment-success}"
    )
    public void consumeSuccessEvent(String payload) {
        log.info("Receiving success event {} from payment-success topic", payload);
        var event = jsonUtil.toEvent(payload);
        paymentService.realizePayment(event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.payment-fail}"
    )
    public void consumeFailEvent(String payload) {
        log.info("Receiving rollback event {} from payment-fail topic", payload);
        var event = jsonUtil.toEvent(payload);
        paymentService.realizeRefund(event);
    }
}
