package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(optional=false, cascade=CascadeType.MERGE)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(optional=false, cascade=CascadeType.MERGE)
    @JoinColumn(name="company_id")
    private Company company;

    @OneToMany(mappedBy="model", fetch=FetchType.EAGER)
    @JsonIgnore
    private Collection<Product> products;
}