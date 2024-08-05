package com.example.product_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 350)
    private String name;

    private Float price;

    @Column(name = "description")
    private String description;


}
