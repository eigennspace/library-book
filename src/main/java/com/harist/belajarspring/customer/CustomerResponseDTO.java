package com.harist.belajarspring.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {
    private Long id;
    private String name;
}
