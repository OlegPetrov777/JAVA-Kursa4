package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName_customer;

    private String lastName_customer;

    private String product_id;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date_of_create;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date_of_ready;

    private String address;

}
