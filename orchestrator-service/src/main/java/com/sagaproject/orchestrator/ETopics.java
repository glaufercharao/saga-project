package com.sagaproject.orchestrator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETopics {

    START_SAGA("start_saga"),
    BASE_ORCHESTRATOR("base_orchestrator"),
    FINISH_SUCCESS("finish_success"),
    FINISH_FAIL("finish_fail"),
    PRODUCT_VALIDATION_SUCCESS("product_validation_success"),
    PRODUCT_VALIDATION_FAIL("product_validation_fail"),
    PAYMENT_SUCCESS("payment_success"),
    PAYMENT_FAIL("payment_fail"),
    INVENTORY_SUCCESS("inventory_success"),
    INVENTORY_FAIL("inventory_fail"),
    NOTIFY_ENDING("notify_ending");

    private String topic;
}
