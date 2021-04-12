package com.example.project.entity;

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

    @OneToMany (mappedBy="company", fetch=FetchType.EAGER)
    private Collection<Model> models;
}
