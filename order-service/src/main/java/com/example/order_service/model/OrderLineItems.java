package com.example.order_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_line_items")
@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String skuCode;
    private  Float price;
    private Integer quantity;

}
