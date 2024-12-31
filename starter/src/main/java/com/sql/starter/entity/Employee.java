package com.sql.starter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    private int id;
    private String name;
    private String phNo;
    private String address;

}
