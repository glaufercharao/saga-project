package com.sagaproject.inventory.core.dto;

import com.sagaproject.inventory.core.enums.ESagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {

    private String source;
    private ESagaStatus status;
    private String message;
    private LocalDateTime createdAt;
}
