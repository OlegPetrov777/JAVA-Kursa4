package com.example.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private boolean admin_status;

    @OneToMany (mappedBy="employee", fetch=FetchType.EAGER)
    private Collection<Order> orders;
}
