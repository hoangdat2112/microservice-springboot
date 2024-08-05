package com.example.product_service.dto.response;

import lombok.*;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;

    private String name;

    private Float price;

    private String description;
}
