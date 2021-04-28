package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date_of_create;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date_of_ready;

//    @ManyToOne(optional=false, cascade=CascadeType.MERGE)
//    @JoinColumn(name="customer_id")
//    private Customer customer;

//    @ManyToOne(optional=false, cascade=CascadeType.MERGE)
//    @JoinColumn(name="employee_id")
//    private Employee employee;

    @ManyToOne(optional=false, cascade=CascadeType.MERGE)
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;
    @Column(insertable = false, updatable = false)
    private Long product_id;
}
