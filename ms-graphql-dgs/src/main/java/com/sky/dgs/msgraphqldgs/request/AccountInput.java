package com.sky.dgs.msgraphqldgs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInput {
    private Long accountNumber;
    private String accountStatus;
    private Double accountBalance;
}
