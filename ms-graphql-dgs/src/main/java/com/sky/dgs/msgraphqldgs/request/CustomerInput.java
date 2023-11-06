package com.sky.dgs.msgraphqldgs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInput {

    private Long contact;
    private String name;
    private String gender;
    private String mail;
    private List<AccountInput> accounts;
}
