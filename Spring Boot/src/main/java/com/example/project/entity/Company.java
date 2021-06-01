package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@Table(name = "companys")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Company(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="company", fetch=FetchType.EAGER)
    @JsonIgnore
    private Collection<Model> models;
}
