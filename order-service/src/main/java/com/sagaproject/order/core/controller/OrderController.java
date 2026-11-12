package com.sagaproject.order.core.controller;

import com.sagaproject.order.core.document.Order;
import com.sagaproject.order.core.dtos.OrderRequest;
import com.sagaproject.order.core.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest order) {
        return orderService.createOrder(order);
    }
}
