package com.sagaproject.order.core.service;

import com.sagaproject.order.core.document.Event;
import com.sagaproject.order.core.document.Order;
import com.sagaproject.order.core.dtos.OrderRequest;
import com.sagaproject.order.core.producer.SagaProducer;
import com.sagaproject.order.core.repository.OrderRepository;
import com.sagaproject.order.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    private final EventService eventService;
    private final SagaProducer producer;
    private final JsonUtil jsonUtil;
    private final OrderRepository repository;

    public Order createOrder(OrderRequest orderRequest) {
        var order = Order
                .builder()
                .products(orderRequest.getProducts())
                .createdAt(LocalDateTime.now())
                .transactionId(
                        String.format(TRANSACTION_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID()))
                .build();
        repository.save(order);
        producer.sendEvent(jsonUtil.toJson(createPayload(order)));
        return order;
    }

    private Event createPayload(Order order) {
        var event = Event
                .builder()
                .orderId(order.getId())
                .transactionId(order.getTransactionId())
                .payload(order)
                .createdAt(LocalDateTime.now())
                .build();
        eventService.createEvent(event);
        return event;
    }
}
