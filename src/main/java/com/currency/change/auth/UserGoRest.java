package com.currency.change.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGoRest {
    private String id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
