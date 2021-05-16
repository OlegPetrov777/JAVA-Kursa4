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

    private Integer amount;

    @ManyToOne(optional=false, cascade=CascadeType.MERGE)
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;
    @Column(insertable = false, updatable = false)
    private Long product_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate_of_create() {
        return date_of_create;
    }

    public void setDate_of_create(LocalDate date_of_create) {
        this.date_of_create = date_of_create;
    }

    public LocalDate getDate_of_ready() {
        return date_of_ready;
    }

    public void setDate_of_ready(LocalDate date_of_ready) {
        this.date_of_ready = date_of_ready;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
}
