package com.example.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    private String color;

    private int count;

    @ManyToOne(optional=false, cascade=CascadeType.MERGE)
    @JoinColumn(name="model_id")
    private Model model;

    @OneToMany(mappedBy="product", fetch=FetchType.EAGER)
    private Collection<Order> orders;
}
