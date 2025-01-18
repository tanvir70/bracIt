package com.tanvir.dems.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String name;

    private String designation;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "date_of_joining")
    private LocalDate joiningDate;

    private BigDecimal salary;

    @Type(JsonType.class)
    @Column(name = "additional_attributes", columnDefinition = "jsonb")
    private Map<String, Object> additionalAttributes;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

