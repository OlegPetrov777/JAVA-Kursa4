package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data // вместо Get\Set
@NoArgsConstructor  // конструктор
public class Person {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long id;

        private  String firstName;
        private  String lasttName;
        private  String city;
        private  String street;

}
