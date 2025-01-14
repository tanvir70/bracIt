package com.tanvir.dems.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String designation;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "date_of_joining", nullable = false)
    private LocalDate joiningDate;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(columnDefinition = "jsonb")
    private String attributes;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

