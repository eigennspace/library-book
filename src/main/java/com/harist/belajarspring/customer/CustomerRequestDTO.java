package com.harist.belajarspring.customer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {
    private Long id;
    private String name;

    public Customer convertToEntity() {
        return Customer.builder().id(this.id).name(this.name).build();
    }
}
